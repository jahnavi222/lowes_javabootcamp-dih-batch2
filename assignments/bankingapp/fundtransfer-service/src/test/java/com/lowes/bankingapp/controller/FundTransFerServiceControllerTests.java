package com.lowes.bankingapp.controller;



import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import java.util.List;

import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.Assert;

import com.lowes.bankingapp.exception.FundTransferException;
import com.lowes.bankingapp.model.FundTransfer;
import com.lowes.bankingapp.service.FundTransferService;



// API Test / Integration test
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(
  locations = "classpath:application.properties")
@ActiveProfiles("test")
public class FundTransFerServiceControllerTests {

    @Autowired
    TestRestTemplate restTemp;
    
    @LocalServerPort
    int randomServerPort;

//  @Autowired    
    @MockBean
	 FundTransferService fundService;

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
//        productService.clear();
    	fundTralist.clear();
    }

 /*   @Test
    public void shouldCreateFundTransfer() throws URISyntaxException, FundTransferException
    {
        // POST /products

    
        Mockito.when(fundService.createCreditTransfer(new FundTransfer())).thenReturn(1);
        Mockito.when(fundService.createDebitTransfer(new FundTransfer())).thenReturn(1);
        String reqBody = "{\"id\":\"1\",\"SrcAccountId\":\"1\",\"targerAccid\":\"2\",\"Amount\":\"200\",\"description\":\"school fees\"}";


        // Step 1: Create Request
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RequestEntity request = new RequestEntity(reqBody, headers, HttpMethod.POST, new URI("/fundtransfer"));

        // Step 2: Send Request to Endpoint
        // Step 3: Receive the Response

        ResponseEntity<String> response = restTemp.exchange(request, String.class);

        System.out.println("Response: " + response.getBody());

        // Step 4: Validate the Response
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }*/


   /* @Test
    public void shouldReturnAllAccounts() throws AccountException {

        Mockito.when(accService.list()).thenReturn(accounts);

        // REST Template
        // Step 1: Create Request
        // Step 2: Send Request to Endpoint
        // Step 3: Receive the Response
        ResponseEntity<Object> response = restTemp.getForEntity("/accounts", Object.class);

        List<Account> products = (List) response.getBody();
        

        // Step 4: Validate the Response
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(products.size()).isEqualTo(2);
      
    }
    @Test
    public void shouldReturnSingleAccount() throws AccountException {

        Mockito.when(accService.get(1)).thenReturn(accounts.get(0));

        // REST Template
        // Step 1: Create Request
        // Step 2: Send Request to Endpoint
        // Step 3: Receive the Response
        ResponseEntity<Object> response = restTemp.getForEntity("/accounts/1", Object.class);

        //List<Account> products = (List) response.getBody();

        System.out.println("Response: " + accounts);

        // Step 4: Validate the Response
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
      
    }
    @Test
    public void shouldUpdateAccount() throws URISyntaxException, AccountException
    {
        // POST /products

    	acc2.setId(2);
    	acc2.setName("test2");
		acc2.setType("Current");
		acc2.setStatus("Active");		
		acc2.setBalance(500);
		
        URI uri = new URI("/accounts/2");
      
         
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");      
        headers.set("Accept", "application/json");  
        HttpEntity<Account> request = new HttpEntity<>(acc2, headers);
         
        ResponseEntity<String> result = this.restTemp.postForEntity(uri, request, String.class);
         
        //Verify request succeed
        Assertions.assertThat(HttpStatus.OK).isEqualTo(HttpStatus.OK);
    }
    
    @Test
    public void shouldDeleteAccount() throws AccountException {

        Mockito.when(accService.deleteProductById(1)).thenReturn(accounts.get(0));

        // REST Template
        // Step 1: Create Request
        // Step 2: Send Request to Endpoint
        // Step 3: Receive the Response
        ResponseEntity<Object> response = restTemp.getForEntity("/accounts/1", Object.class);

        //List<Account> products = (List) response.getBody();

        System.out.println("Response: " + accounts);

        // Step 4: Validate the Response
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
      
    }*/
}
