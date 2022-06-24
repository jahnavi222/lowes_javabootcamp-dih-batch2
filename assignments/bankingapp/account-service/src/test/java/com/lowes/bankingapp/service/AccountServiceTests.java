package com.lowes.bankingapp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.lowes.bankingapp.exception.AccountException;
import com.lowes.bankingapp.model.Account;

import com.lowes.bankingapp.repository.AccountRepository;

@SpringBootTest
public class AccountServiceTests {

	@Autowired
	AccountService accService;

	@MockBean
	AccountRepository accRepo;

	@BeforeAll
	public static void init() {
		// Logic to initialize test data goes here
		System.out.println("Test data initialization at class level..");
	}

	@AfterAll
	public static void tearDown() {
		// Logic to clean up test data goes here
		System.out.println("Test data clean up at class level..");
	}
	
	private static List<Account> accounts = new ArrayList<>();
	Account acc1 = new Account();
	Account acc2 = new Account();
	@BeforeEach
	public void setup() throws AccountException {
		// Initialize Test data
		acc1.setId(1);
		acc1.setName("test");
		acc1.setType("Saving");
		acc1.setStatus("pending");		
		acc1.setBalance(500);
		acc1.setOpenDate(new Date());
		//accService.create(acc1);
		accounts.add(acc1);
		
		acc2.setId(2);
		acc2.setName("test");
		acc2.setType("Saving");
		acc2.setStatus("Active");		
		acc2.setBalance(5000);
		acc2.setOpenDate(new Date());
		//accService.create(acc2);
		accounts.add(acc2);
	}

	@AfterEach
	public void cleanup() {
	accService.clear();
		accounts.clear();
	}

	@Test
	public void shouldCreateAccWhenPassingMandatoryDetails() throws AccountException {
		
		
		when(accRepo.save(any())).thenReturn(acc1);
		accService.create(acc1);
	     verify(accRepo,times(1)).save(any());

	   /* assertNotNull(accService.get(3).getId());
	    System.out.println(accService.list().get(2));
		assertEquals(3, accService.list().get(0))*/
	}
	
	@Test
	public void shouldShowErrorWhenNotPassingMandatoryDetails() {
		Account acc = new Account();
	try {
			accService.create(acc);
		} catch (Exception e) {
			assertEquals("Field type is required", e.getMessage());
		}
	}
//
	@Test
	public void shouldUpdateAccForGivenAccId() {
		
		acc1.setName("test");
		acc1.setType("Saving");
		acc1.setStatus("close");

		accService.update(1, acc1);

		when(accRepo.save(any())).thenReturn(acc1);
	}
//


	@Test
	public void shouldReturnAccountForGivenAccountId() throws AccountException {
		 Mockito.when(accRepo.findById(1)).thenReturn(Optional.ofNullable(acc1));
		   assertThat(accService.get(acc1.getId())).isEqualTo(acc1);
		
	}

	@Test
	public void shouldReturnAllProductsWhenDontSpecifyProductId() throws AccountException {
		Mockito.when(accRepo.findAll()).thenReturn(accounts);
		
		assertEquals(2, accService.list().size());
	}
	/*@Test
	public void shouldDeleteProductWhenPassingValidProductId() throws AccountException {
		when(accService.deleteProductById(acc1.getId())).thenReturn(acc1);
		//assertThat(productService.);
		   
		verify(accRepo,times(1)).findById(acc1.getId());
	
		}*/
	

}
