package com.lowes.bankingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lowes.bankingapp.model.Account;
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{
	
	public List<Account> findByType(String type);
	
	
	@Query("Select a FROM Account a WHERE type=:accType")
	public List<Account> findByAccountType(String accType);
	
	@Query("Select a FROM Account a WHERE type=:accType AND balance > :balance")
	public List<Account> findByAccountTypeAndBalance(String accType, double balance);


	

}
