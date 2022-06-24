package com.lowes.bankingapp.service;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.lowes.bankingapp.exception.AccountException;
import com.lowes.bankingapp.model.Account;

@Service
public interface AccountService {
	
	public String create(Account account) throws AccountException;
	public Collection<Account> list() throws AccountException;
	public Account get(int id);
	public Account update(int id, Account account);
	public void delete(int id);
	public List<Account> search(String type);
	public void clear();
	 public Account deleteProductById(int id);
	 public Account create1(Account account) throws AccountException;

}
