package com.lowes.bankingapp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.lowes.bankingapp.exception.TransactionException;

import com.lowes.bankingapp.model.Transaction;
import com.lowes.bankingapp.repository.TransactionRepository;

@SpringBootTest
public class TransactionServiceTests {

	@Autowired
	TransactionService transService;

	@MockBean
	TransactionRepository transRepo;

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
	
	private static List<Transaction> translist = new ArrayList<>();
    Transaction trans1 = new Transaction();
    Transaction trans2 = new Transaction();
	@BeforeEach
	public void setup() throws TransactionException {
		// Initialize Test data
		trans1.setId(1);
    	trans1.setAccType("credit");
    	trans1.setAccountId(1);
    	trans1.setAmount(400);
		trans1.setdescription("test11");
		translist.add(trans1);
		
		  
		trans2.setId(2);
    	trans2.setAccType("debit");
    	trans2.setAccountId(1);
    	trans2.setAmount(400);
		trans2.setdescription("test11");
		translist.add(trans2);
     
	}

	@AfterEach
	public void cleanup() {
		transService.clear();
		translist.clear();
	}

	@Test
	public void shouldCreateTransactionWhenPassingMandatoryDetails() throws TransactionException {
		
		
		when(transRepo.save(any())).thenReturn(trans1);
		transService.create(trans1);
	     verify(transRepo,times(1)).save(any());

	  
	}
	
	@Test
	public void shouldShowErrorWhenNotPassingMandatoryDetails() {
		Transaction acc = new Transaction();
	try {
		transService.create(acc);
		} catch (Exception e) {
			assertEquals("Field type is required", e.getMessage());
		}
	}
//
	@Test
	public void shouldUpdateTransForGivenId() {
		
		trans1.setId(2);
    	trans1.setAccType("debit");
    	trans1.setAccountId(1);
    	trans1.setAmount(40);
		trans1.setdescription("testff11d");
		translist.add(trans1);

		transService.update(1, trans1);

		when(transRepo.save(any())).thenReturn(trans1);
	}
//


	@Test
	public void shouldReturnTransForGivenTranstId() throws TransactionException {
		 Mockito.when(transRepo.findById(1)).thenReturn(Optional.ofNullable(trans1));
		   assertThat(transService.get(trans1.getId())).isEqualTo(trans1);
		
	}

	@Test
	public void shouldReturnAllTransactionsWhenDontSpecifyTransId() throws TransactionException {
		Mockito.when(transRepo.findAll()).thenReturn(translist);
		
		assertEquals(2, transService.list().size());
	}
	/*@Test
	public void shouldDeleteTransWhenPassingValidTransactId() throws TransactionException {
		when(transService.deleteTransById(trans1.getId())).thenReturn((trans1)).thenReturn(null);
		//assertThat(productService.);
		   
		verify(transService,times(1)).delete(0);
		assertEquals(1, transService.list().get(0));
		}*/
	

}
