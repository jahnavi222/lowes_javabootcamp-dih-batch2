package com.lowes.bankingapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lowes.bankingapp.exception.TransactionException;
import com.lowes.bankingapp.model.Account;
import com.lowes.bankingapp.model.Transaction;


public interface TransactionService {
	
	public String create(Transaction transaction) throws TransactionException;
	public List<Transaction> list() throws TransactionException;
	public Transaction get(int id);
	public Transaction update(int id, Transaction transaction);
	public void delete(int id);

	public void clear();
	Transaction deleteTransById(int id);

}
