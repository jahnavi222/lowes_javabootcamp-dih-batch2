package com.lowes.bankingapp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.lowes.bankingapp.exception.FundTransferException;

import com.lowes.bankingapp.model.FundTransfer;

import com.lowes.bankingapp.repository.FundTransferRepository;

@Service
public class FundTransferServiceImpl implements FundTransferService {

	@Autowired
	FundTransferRepository repo;
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	Logger logger = LoggerFactory.getLogger(FundTransferServiceImpl.class);

	// Map<Integer, Account> accounts = new HashMap<>();


	@Override
	public int createCreditTransfer(FundTransfer fundTransfer) throws FundTransferException {
		fundTransfer.setStatus("IN_PROGRESS");
		repo.save(fundTransfer);

		String msg = fundTransfer.getId() + "," + fundTransfer.getSrcAccountId() + "," + fundTransfer.getTargerAccid()
				+ "," + fundTransfer.getAmount() + "," + fundTransfer.getDescription();

		kafkaTemplate.send("TRANS_CREDIT", msg);
		

		return fundTransfer.getId();
	}

	@Override
	public int createDebitTransfer(FundTransfer fundTransfer) throws FundTransferException {
		fundTransfer.setStatus("IN_PROGRESS");
		repo.save(fundTransfer);

		String msg = fundTransfer.getId() + "," + fundTransfer.getSrcAccountId() + "," + fundTransfer.getTargerAccid()
				+ "," + fundTransfer.getAmount() + "," + fundTransfer.getDescription();
		;

		kafkaTemplate.send("TRANS_DEBIT", msg);

		return fundTransfer.getId();
	}

	@Override
	public List<FundTransfer> list() throws FundTransferException {
		//
		return repo.findAll();
	}

	@Override
	public FundTransfer get(int id) {
		//
		return repo.findById(id).get();
	}

	@Override
	public FundTransfer update(int id, FundTransfer account) {
		// accounts.put(id, account);
		// return account;
		return repo.save(account);
	}

	@Override
	public void delete(int id) {
		// accounts.remove(id);
		repo.deleteById(id);
	}

	public void clear() {
		// productRepo.clear();
		repo.deleteAll();
	}

	@KafkaListener(topics = "TRANS_CREDIT_SUCCESS", groupId = "fundtransfer-service")
	public void listenCreditTransSuccess(ConsumerRecord<?, ?> cr) throws Exception {
		System.out.println("###################transaction order: " + cr.value());
		logger.info("Service-listenCreditTrans method");
		logger.info("STRANS_CREDIT topic" + " " + cr.value());
		String msg = (String) cr.value();
		String[] tokens = msg.split(",");
		String accintID = tokens[0];
		String accntType = tokens[1];
		String amount = tokens[2];
		String description = tokens[3];
		String fundtranferId = tokens[4];
		String transMessage = tokens[5];
		double creditAmount = Double.valueOf(amount);
		FundTransfer fundtransfer = null;
		// Logic to check credit limit
		try {
			fundtransfer = repo.findById(Integer.valueOf(fundtranferId)).get();
			if (fundtransfer != null && "Successful".equals(transMessage)) {
				fundtransfer.setId(fundtransfer.getId());
				fundtransfer.setStatus("ACCOUNT_CREDITED");

				repo.save(fundtransfer);
			}
		} catch (Exception e) {

			logger.error("Unable to change the status of theaccount", e);
			throw new FundTransferException("unable to change the status of the credit account");
		}
	}

	@KafkaListener(topics = "TRANS_CREDIT_FAILED", groupId = "fundtransfer-service")
	public void listenCreditTransFailed(ConsumerRecord<?, ?> cr) throws Exception {
		System.out.println("###################transaction order: " + cr.value());
		logger.info("Service-listenCreditTrans method");
		logger.info("STRANS_CREDIT topic" + " " + cr.value());
		String msg = (String) cr.value();
		String[] tokens = msg.split(",");
		String accintID = tokens[0];
		String accntType = tokens[1];
		String amount = tokens[2];
		String description = tokens[3];
		String fundtranferId = tokens[4];
		String transMessage = tokens[5];
		double creditAmount = Double.valueOf(amount);
		String message = null;
		FundTransfer fundtransfer = null;
		// Logic to check credit limit
		try {
			fundtransfer = repo.findById(Integer.valueOf(fundtranferId)).get();
			if (fundtransfer != null && "Failed".equals(transMessage)) {
				fundtransfer.setId(fundtransfer.getId());

				repo.deleteById(fundtransfer.getId());
				
			}
		} catch (Exception e) {

			logger.error("unable to delete the  fundtransfer entity", e);
			throw new FundTransferException("unable to delete the  fundtransfer entity");
		}
	}

	@KafkaListener(topics = "TRANS_DEBIT_SUCCESS", groupId = "fundtransfer-service")
	public void listenDebitTransSuccess(ConsumerRecord<?, ?> cr) throws Exception {
		System.out.println("###################transaction order: " + cr.value());
		logger.info("Service-listenCreditTrans method");
		logger.info("STRANS_CREDIT topic" + " " + cr.value());
		String msg = (String) cr.value();
		String[] tokens = msg.split(",");
		String accintID = tokens[0];
		String accntType = tokens[1];
		String amount = tokens[2];
		String description = tokens[3];
		String fundtranferId = tokens[4];
		String transMessage = tokens[5];
		double creditAmount = Double.valueOf(amount);
		String message = null;
		FundTransfer fundtransfer = null;
		// Logic to check credit limit
		try {
			fundtransfer = repo.findBysrcAccountId(Integer.valueOf(accintID));
			if (fundtransfer != null && "Successful".equals(transMessage)) {
				fundtransfer.setId(fundtransfer.getId());
				fundtransfer.setStatus("ACCOUNT_DEBITED");
				repo.save(fundtransfer);
			}
		} catch (Exception e) {
			logger.error("Unable to change the status of the account", e);
			throw new FundTransferException("unable to change the status of the debit account");

		}
	}

	@KafkaListener(topics = "TRANS_DEBIT_FAILED", groupId = "fundtransfer-service")
	public void listenDebitTransFailed(ConsumerRecord<?, ?> cr) throws Exception {
		System.out.println("###################transaction order: " + cr.value());
		logger.info("Service-listenCreditTrans method");
		logger.info("TRANS_DEBIT_FAILED" + " " + cr.value());
		String msg = (String) cr.value();
		String[] tokens = msg.split(",");
		String accintID = tokens[0];
		String accntType = tokens[1];
		String amount = tokens[2];
		String description = tokens[3];
		String fundtranferId = tokens[4];
		String transMessage = tokens[5];
		double creditAmount = Double.valueOf(amount);
		String message = null;
		FundTransfer fundtransfer = null;
		// Logic to check credit limit
		try {
			fundtransfer = repo.findBysrcAccountId(Integer.valueOf(accintID));
			if (fundtransfer != null && "Failed".equals(transMessage)) {
				fundtransfer.setId(fundtransfer.getId());

				repo.deleteById(fundtransfer.getId());
			}
		} catch (Exception e) {
			logger.error("Unable to change the status of the account", e);
			throw new FundTransferException("unable to change the status of the debit account");

		}
	}

}
