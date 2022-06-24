package com.lowes.bankingapp.exception;

public class FundTransferException extends Exception {

	public FundTransferException() {
		super();
	}

	public FundTransferException(String message, Throwable cause) {
		super(message, cause);
	}

	public FundTransferException(String message) {
		super(message);
	}

}
