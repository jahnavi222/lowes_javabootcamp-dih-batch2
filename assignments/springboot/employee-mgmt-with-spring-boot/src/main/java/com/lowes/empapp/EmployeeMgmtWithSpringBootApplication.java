package com.lowes.empapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.lowes.empapp", "com.lowes.empapp.controller"})
public class EmployeeMgmtWithSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeMgmtWithSpringBootApplication.class, args);
	}

}
