package com.lowes.empapp.exception;

public class EmployeeNotFoundException extends Exception{
	private String errorMsg;

	public EmployeeNotFoundException(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}



