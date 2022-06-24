package com.lowes.bankingapp;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class AccountServiceMainTest {
	@Autowired
	ApplicationContext ctx;

	@Test
	void contextLoads() {
	}
	
	@Test
	public void testApplicationContext() {
		System.out.println("Context: " + ctx);
		Assertions.assertThat(ctx).isNotNull();
	}
}
