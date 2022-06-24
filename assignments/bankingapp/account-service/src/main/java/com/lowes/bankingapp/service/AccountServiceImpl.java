package com.lowes.bankingapp.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;

import com.lowes.bankingapp.exception.AccountException;
import com.lowes.bankingapp.model.Account;
import com.lowes.bankingapp.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository repo;
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;

	// Map<Integer, Account> accounts = new HashMap<>();
	Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

	@Override
	public String create(Account account) throws AccountException {
		if (account==null) {
			throw new RuntimeException("Account Id mandatory");
		}
		repo.save(account);
		return "Account";
	}
	public Account create1(Account account) throws AccountException {
		if (account==null) {
			throw new RuntimeException("Account Id mandatory");
		}
		repo.save(account);
		return account;
	}

	@Override
	public Collection<Account> list() throws AccountException {

		return repo.findAll();
	}

	@Override
	public Account get(int id) {

		return repo.findById(id).get();
	}

	@Override
	public Account update(int id, Account account) {

		return repo.save(account);
	}

	@Override
	public void delete(int id) {

		repo.deleteById(id);
	}
	 @Override
	    public Account deleteProductById(int id) {
	        Account acc = null;
	        Optional optional = repo.findById(id);
	        if (optional.isPresent()) {
	            acc = repo.findById(id).get();
	            repo.deleteById(id);
	        }
	        return acc;
	    }

	@Override
	public List<Account> search(String type) {

		return repo.findByAccountType(type);
	}

	public void clear() {

		repo.deleteAll();
	}

	@KafkaListener(topics = "TRANS_CREDIT", groupId = "account-service")
	public void listenCreditTrans(ConsumerRecord<?, ?> cr) throws Exception {
		System.out.println("###################transaction order: " + cr.value());
		logger.info("Service-listenCreditTrans method");
		logger.info("STRANS_CREDIT topic" + " " + cr.value());
		String msg = (String) cr.value();
		String[] tokens = msg.split(",");
		String fundTrnsferId = tokens[0];
		String srcAccountId = tokens[1];
		String targetAccid = tokens[2];
		String amount = tokens[3];
		String description = tokens[4];

		double creditAmount = Double.valueOf(amount);
		String message = null;
		Account targetAcc = null;
		// Logic to check credit limit
		try {
			targetAcc = repo.findById(Integer.valueOf(targetAccid)).get();
			if(targetAcc!=null) {
			double availableBalance = targetAcc.getBalance();
			if (availableBalance > 0 && targetAcc.getStatus().equalsIgnoreCase("Active")) {
				double totalBalance = availableBalance + creditAmount;
				targetAcc.setBalance(totalBalance);
				//targetAcc.setType("credit");
				// System.out.println("ORDER APPROVED :: " + orderId);
				repo.save(targetAcc);
				message = targetAcc.getId() + "," + "credit" + "," + creditAmount + "," + description + ","
						+ fundTrnsferId + "," + "Successful";
				logger.info("TRANS_CREDIT_SUCCESS" + "" + message);
				kafkaTemplate.send("TRANS_CREDIT_SUCCESS", message);
			}
			}
		} catch (Exception e) {
			message = targetAcc.getId() + "," + targetAcc.getType() + "," + creditAmount + "," + description + ","
					+ fundTrnsferId + "," + "Failed";
			kafkaTemplate.send("TRANS_CREDIT_FAILED", message);
			logger.error("Unable to credit the amount", e);
			throw new AccountException("Unable to credit the amount");
		}
	}

	@KafkaListener(topics = "TRANS_DEBIT", groupId = "account-service")
	public void listenDebitTransaction(ConsumerRecord<?, ?> cr) throws Exception {
		System.out.println("###################transaction order: " + cr.value());
		logger.info("Service-listenCreditTrans method");
		logger.info("TRANS_DEBIT topic" + " " + cr.value());
		String msg = (String) cr.value();
		String[] tokens = msg.split(",");
		String fundTrnsferId = tokens[0];
		String srcAccountId = tokens[1];
		String targetAccid = tokens[2];
		String amount = tokens[3];
		String description = tokens[4];
		Account targetAcc = null;
		double debitAmount = Double.valueOf(amount);
		String message = null;
		try {
			// Logic to check credit limit
			targetAcc = repo.findById(Integer.valueOf(srcAccountId)).get();
			if(targetAcc!=null) {
			double availableBalance = targetAcc.getBalance();
			if (availableBalance < debitAmount && targetAcc.getStatus().equalsIgnoreCase("Active")) {
				message = targetAcc.getId() + "," + targetAcc.getType() + "," + debitAmount + "," + description + ","
						+ fundTrnsferId + "," + "Failed";
                kafkaTemplate.send("TRANS_DEBIT_FAILED", message);
				kafkaTemplate.send("TRANS_CREDIT_FAILED", message);
				throw new AccountException("No Sufficient Balance Amount");
			} else {
				double totalBalance = availableBalance - debitAmount;
				targetAcc.setBalance(totalBalance);
				//targetAcc.setType("debit");
				repo.save(targetAcc);
				message = targetAcc.getId() + "," + "debit" + "," + debitAmount + "," + description + ","
						+ fundTrnsferId + "," + "Successful";
				kafkaTemplate.send("TRANS_DEBIT_SUCCESS", message);
			}
			}

		} catch (Exception e) {
			message = targetAcc.getId() + "," + targetAcc.getType() + "," + debitAmount + "," + description + ","
					+ fundTrnsferId + "," + "Failed";
			kafkaTemplate.send("TRANS_DEBIT_FAILED", message);
			logger.error("Unable to debit the amount", e);
			throw new AccountException("Unable to debit the amount");
		}
	}

	

}
