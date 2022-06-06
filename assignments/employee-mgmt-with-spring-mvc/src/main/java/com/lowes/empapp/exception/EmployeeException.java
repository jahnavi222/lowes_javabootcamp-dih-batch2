package com.lowes.empapp.exception;

public class EmployeeException extends Exception {
	private String errorMsg;

	public EmployeeException(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}


