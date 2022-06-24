package com.lowes.bankingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lowes.bankingapp.model.FundTransfer;

@Repository
public interface FundTransferRepository extends JpaRepository<FundTransfer, Integer>{
	
	FundTransfer findBysrcAccountId(int accountID);

}
