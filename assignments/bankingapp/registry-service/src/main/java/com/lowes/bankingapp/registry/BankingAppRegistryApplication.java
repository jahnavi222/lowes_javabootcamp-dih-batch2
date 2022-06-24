package com.lowes.bankingapp.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BankingAppRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingAppRegistryApplication.class, args);
	}

}
