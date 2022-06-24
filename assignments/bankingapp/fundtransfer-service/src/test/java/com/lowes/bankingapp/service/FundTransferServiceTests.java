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

import com.lowes.bankingapp.exception.FundTransferException;
import com.lowes.bankingapp.model.FundTransfer;

import com.lowes.bankingapp.repository.FundTransferRepository;

@SpringBootTest
public class FundTransferServiceTests {

	@Autowired
	 FundTransferService fundService;

	@MockBean
	FundTransferRepository fundRepo;

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
	
	private static List<FundTransfer> fundTralist = new ArrayList<>();
	FundTransfer fund1 = new FundTransfer();
	FundTransfer fund2 = new FundTransfer();
	@BeforeEach
	public void setup() throws FundTransferException {
		// Initialize Test data
		fund1.setId(1);
		fund1.setSrcAccountId(1);
		fund1.setTargerAccid(2);
		fund1.setAmount(200);
		fund1.setDescription("sdsd");
		//accService.create(acc1);
		fundTralist.add(fund1);
		
		fund2.setId(2);
		fund2.setSrcAccountId(1);
		fund2.setTargerAccid(2);
		fund2.setAmount(200);
		fund2.setDescription("sdsd");
		//accService.create(acc2);
		fundTralist.add(fund2);
	}

	@AfterEach
	public void cleanup() {
		fundService.clear();
	fundTralist.clear();
	}

	@Test
	public void shouldCreateAccWhenPassingMandatoryDetails() throws FundTransferException {
		
		
		when(fundRepo.save(any())).thenReturn(fund1);
		fundService.createCreditTransfer(fund1);
	     verify(fundRepo,times(1)).save(any());
	     
	     when(fundRepo.save(any())).thenReturn(fund1);
			fundService.createDebitTransfer(fund1);
		     verify(fundRepo,times(2)).save(any());

	}
	
	@Test
	public void shouldShowErrorWhenNotPassingMandatoryDetails() {
	
	try {
		fundService.createCreditTransfer(fund1);
		} catch (Exception e) {
			assertEquals("Field amount is required", e.getMessage());
		}
	}
//
	/*@Test
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
	}*/
	/*@Test
	public void shouldDeleteProductWhenPassingValidProductId() throws AccountException {
		when(accService.deleteProductById(acc1.getId())).thenReturn(acc1);
		//assertThat(productService.);
		   
		verify(accRepo,times(1)).findById(acc1.getId());
	
		}*/
	

}
