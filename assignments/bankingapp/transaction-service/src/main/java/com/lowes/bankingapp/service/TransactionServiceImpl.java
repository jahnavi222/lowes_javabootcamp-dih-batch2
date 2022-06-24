package com.lowes.bankingapp.service;

import java.util.ArrayList;
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

import com.lowes.bankingapp.controller.TransactionController;
import com.lowes.bankingapp.exception.TransactionException;
import com.lowes.bankingapp.model.Account;

import com.lowes.bankingapp.model.Transaction;
import com.lowes.bankingapp.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	TransactionRepository repo;
	Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);
	// Map<Integer, Account> accounts = new HashMap<>();

	@Override
	public String create(Transaction transaction) throws TransactionException {
		if (transaction==null) {
			throw new RuntimeException("Transaction Id mandatory");
		}
		repo.save(transaction);
		return "Transaction created";
	}

	@Override
	public List<Transaction> list() throws TransactionException {
		return repo.findAll();
	}

	@Override
	public Transaction get(int id) {
		// return accounts.get(id);
		return repo.findById(id).get();
	}

	@Override
	public Transaction update(int id, Transaction transaction) {
		// accounts.put(id, account);
		// return account;
		return repo.save(transaction);
	}

	@Override
	public void delete(int id) {
		// accounts.remove(id);
		repo.deleteById(id);
	}
	 @Override
	    public Transaction deleteTransById(int id) {
	        Transaction tra = null;
	        Optional optional = repo.findById(id);
	        if (optional.isPresent()) {
	            tra = repo.findById(id).get();
	            repo.deleteById(id);
	        }
	        return tra;
	    }
	public void clear() {
		// productRepo.clear();
		repo.deleteAll();
	}

	@KafkaListener(topics = "TRANS_CREDIT_SUCCESS", groupId = "transaction-service")
	public void listenCreditTransSuccess(ConsumerRecord<?, ?> cr) throws Exception {
		System.out.println("###################transaction order: " + cr.value());
		logger.info("Service-listenCreditTrans method");
		logger.info("STRANS_CREDIT topic" + " " + cr.value());
		String msg = (String) cr.value();
		String[] tokens = msg.split(",");
		String accountID = tokens[0];
		String accntType = tokens[1];
		String amount = tokens[2];
		String description = tokens[3];
		String fundtranferId = tokens[4];
		String transMessage = tokens[5];
		double creditAmount = Double.valueOf(amount);

		Transaction transaction = new Transaction();
		// Logic to check credit limit
		try {

			if ("Successful".equals(transMessage)) {

				transaction.setAccType(accntType);
				transaction.setdescription(description);
				transaction.setAccountId(Integer.parseInt(accountID));
				transaction.setAmount(creditAmount);
				transaction.setFundtransferID(Integer.parseInt(fundtranferId));
				repo.save(transaction);
			}
		} catch (Exception e) {
			logger.error("Unable to create transaction for the debit accountt", e);
			throw new TransactionException("unable to credit the transaction");

		}
	}

	@KafkaListener(topics = "TRANS_CREDIT_FAILED", groupId = "transaction-service")
	public void listenCreditTransFailed(ConsumerRecord<?, ?> cr) throws Exception {
		System.out.println("###################transaction order: " + cr.value());
		logger.info("Service-listenCreditTrans method");
		logger.info("STRANS_CREDIT topic" + " " + cr.value());
		String msg = (String) cr.value();
		String[] tokens = msg.split(",");
		String accountID = tokens[0];
		String accntType = tokens[1];
		String amount = tokens[2];
		String description = tokens[3];
		String fundtranferId = tokens[4];
		String transMessage = tokens[5];
		double creditAmount = Double.valueOf(amount);

		Transaction transaction = null;
		// Logic to check credit limit
		try {
			transaction = repo.findByaccountId(Integer.parseInt(accountID));
			if (transaction != null && "Failed".equals(transMessage)
					&& ((Integer.parseInt(fundtranferId)) == transaction.getFundtransferID())) {

				repo.delete(transaction);
			}
		} catch (Exception e) {
			logger.error("Unable to create transaction for the debit accountt", e);
			throw new TransactionException("unable to credit the transaction");

		}
	}

	@KafkaListener(topics = "TRANS_DEBIT_SUCCESS", groupId = "transaction-service")
	public void listenDebitTransSuccess(ConsumerRecord<?, ?> cr) throws Exception {
		System.out.println("###################transaction order: " + cr.value());
		logger.info("Service-listenCreditTrans method");
		logger.info("STRANS_CREDIT topic" + " " + cr.value());
		String msg = (String) cr.value();
		String[] tokens = msg.split(",");
		String accountID = tokens[0];
		String accntType = tokens[1];
		String amount = tokens[2];
		String description = tokens[3];
		String fundtranferId = tokens[4];
		String transMessage = tokens[5];
		double creditAmount = Double.valueOf(amount);

		Transaction transaction = new Transaction();
		// Logic to check credit limit
		try {

			if ("Successful".equals(transMessage)) {

				transaction.setAccType(accntType);
				transaction.setdescription(description);
				transaction.setAccountId(Integer.parseInt(accountID));
				transaction.setAmount(creditAmount);
				transaction.setFundtransferID(Integer.parseInt(fundtranferId));
				repo.save(transaction);
				
			}
		} catch (Exception e) {
			logger.error("Unable to create transaction for the debit account", e);
			throw new TransactionException("unable to debit account transaction");

		}

	}

	@KafkaListener(topics = "TRANS_DEBIT_FAILED", groupId = "transaction-service")
	public void listenDebitTransFailed(ConsumerRecord<?, ?> cr) throws Exception {
		System.out.println("###################transaction order: " + cr.value());
		logger.info("Service-listenCreditTrans method");
		logger.info("STRANS_CREDIT topic" + " " + cr.value());
		String msg = (String) cr.value();
		String[] tokens = msg.split(",");
		String accountID = tokens[0];
		String accntType = tokens[1];
		String amount = tokens[2];
		String description = tokens[3];
		String fundtranferId = tokens[4];
		String transMessage = tokens[5];
		double creditAmount = Double.valueOf(amount);

		Transaction transaction = null;
		List<Transaction> tlist= new ArrayList<Transaction>();
		// Logic to check credit limit
		try {
			tlist = repo.findAllByfundtransferID(Integer.parseInt(fundtranferId));
			tlist.add(transaction);
		
			if (!tlist.isEmpty()&& "Failed".equals(transMessage)){
					
                for(int i=0;i<tlist.size();i++) {
				repo.delete(transaction);
                }
			}
			
		} catch (Exception e) {
			logger.error("Unable to delete transaction for the debit account", e);
			throw new TransactionException("unable to delete the transaction for failed debit transaction");

		}
	}

}
