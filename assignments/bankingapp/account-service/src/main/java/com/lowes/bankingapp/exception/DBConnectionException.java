package com.lowes.bankingapp.exception;

public class DBConnectionException extends Exception {

	public DBConnectionException() {
		super();
	}

	public DBConnectionException(String message, Throwable cause) {
		super(message, cause);
	}

	public DBConnectionException(String message) {
		super(message);
	}

}
