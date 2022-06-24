package com.lowes.bankingapp.exception;

public class TransactionNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TransactionNotFoundException() {
		super();
	}

	public TransactionNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public TransactionNotFoundException(String message) {
		super(message);
	}

}
