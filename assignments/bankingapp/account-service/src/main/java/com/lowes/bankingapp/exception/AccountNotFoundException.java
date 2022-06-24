package com.lowes.bankingapp.exception;

public class AccountNotFoundException extends Exception {

	public AccountNotFoundException() {
		super();
	}

	public AccountNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public AccountNotFoundException(String message) {
		super(message);
	}

}
