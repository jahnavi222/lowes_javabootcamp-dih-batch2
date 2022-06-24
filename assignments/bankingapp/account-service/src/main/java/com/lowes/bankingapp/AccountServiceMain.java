package com.lowes.bankingapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//@EnableDiscoveryClient
@RestController
public class AccountServiceMain {

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceMain.class, args);
	}

}
