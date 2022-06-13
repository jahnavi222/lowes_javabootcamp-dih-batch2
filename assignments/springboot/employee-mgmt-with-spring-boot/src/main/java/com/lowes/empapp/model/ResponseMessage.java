package com.lowes.empapp.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResponseMessage {
	String status;
	String message;
	public ResponseMessage() {
		
	}
	public ResponseMessage(String status, String message) {
		super();
		this.status = status;
		this.message = message;
		// TODO Auto-generated constructor stub
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

}
