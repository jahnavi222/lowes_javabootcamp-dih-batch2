package com.lowes.bankingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import com.lowes.bankingapp.model.Transaction;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>{
	
	List<Transaction> findAllByfundtransferID(int fundtranferId);
	
	Transaction findByaccountId(int accountID);
	

}
