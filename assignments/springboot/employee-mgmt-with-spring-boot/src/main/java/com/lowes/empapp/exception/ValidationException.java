package com.lowes.empapp.exception;

public class ValidationException extends Exception {
	private String errorMsg;

	public ValidationException(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}


