package com.labs.springboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {
	
	@GetMapping("/greetings")
	public String greetings() {
		return "hello world boot spring vvv";
	}
	

}
