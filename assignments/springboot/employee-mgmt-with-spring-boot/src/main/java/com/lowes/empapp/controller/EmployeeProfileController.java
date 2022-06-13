package com.lowes.empapp.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping(value = { "/", "/empprofile" }, method = RequestMethod.GET)
@RestController
public class EmployeeProfileController {

	@Value("${app.message}")
	private String welcomeMessage;
	
	@GetMapping("/empprofile")
	public String getDataBaseConnectionDetails() {
		return welcomeMessage;
	}
}
