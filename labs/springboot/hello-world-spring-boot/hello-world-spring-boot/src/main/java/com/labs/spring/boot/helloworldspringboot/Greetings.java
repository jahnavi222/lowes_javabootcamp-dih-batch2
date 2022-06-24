package com.labs.spring.boot.helloworldspringboot;

import org.springframework.stereotype.Component;

@Component
public class Greetings {
	private String message;
	
	// default constructor
	public Greetings()
	{
		
	}
	
	// creates Greetings object with message property
	public Greetings(String message)
	{
		this.message = message;
	}
	
	// creates Greetings object with message property
	public Greetings(String message1, String message2)
	{
		this.message = message1 + " " + message2;
	}	
	
	public Greetings(String message1, double message2)
	{
		this.message = message1 + " " + message2;
	}	


	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	public void init()
	{
		System.out.println("Bean getting initialized..");
	}
	
	public void cleanup()
	{
		System.out.println("Bean getting destroyed");
	}
}