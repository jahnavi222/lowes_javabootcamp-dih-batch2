package com.labs.spring.rest;



import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public  class GreetingsController {
@RequestMapping(path = "/greetings", method = RequestMethod.GET, produces = {MediaType.TEXT_PLAIN_VALUE})
	public String getString() {
	
	return "Hello Spring test";
	}
}
