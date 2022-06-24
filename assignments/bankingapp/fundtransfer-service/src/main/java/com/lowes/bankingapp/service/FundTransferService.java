package com.lowes.bankingapp.service;

import java.util.List;

import org.springframework.stereotype.Service;


import com.lowes.bankingapp.exception.FundTransferException;
import com.lowes.bankingapp.model.FundTransfer;


public interface FundTransferService {
	
	public int createCreditTransfer(FundTransfer account) throws FundTransferException;
	public int createDebitTransfer(FundTransfer account) throws FundTransferException;
	public List<FundTransfer> list() throws FundTransferException;
	public FundTransfer get(int id);
	public FundTransfer update(int id, FundTransfer account);
	public void delete(int id);
	public void clear();

}
