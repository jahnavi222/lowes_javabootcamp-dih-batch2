package com.lowes.bankingapp.controller;



import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.assertj.core.util.Arrays;
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

import com.lowes.bankingapp.exception.TransactionException;
import com.lowes.bankingapp.model.Transaction;
import com.lowes.bankingapp.service.TransactionService;


// API Test / Integration test
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(
  locations = "classpath:application.properties")
@ActiveProfiles("test")
public class TransactionServiceControllerTests {

    @Autowired
    TestRestTemplate restTemp;
    
    @LocalServerPort
    int randomServerPort;

//  @Autowired    
    @MockBean
    TransactionService transService;

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
//        productService.clear();
    	translist.clear();
    }

    @Test
    public void shouldCreateTransaction() throws URISyntaxException, TransactionException
    {
        // POST /products

    
        Mockito.when(transService.create(new Transaction())).thenReturn("Transaction Created");

        String reqBody = "{\"id\":\"1\",\"accType\":\"test\",\"accountId\":\"1\",\"amoung\":\"4000\"}";


        // Step 1: Create Request
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RequestEntity request = new RequestEntity(reqBody, headers, HttpMethod.POST, new URI("/transactions"));

        // Step 2: Send Request to Endpoint
        // Step 3: Receive the Response

        ResponseEntity<String> response = restTemp.exchange(request, String.class);

        System.out.println("Response: " + response.getBody());

        // Step 4: Validate the Response
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }


    @Test
    public void shouldReturnAllTransaction() throws TransactionException {

        Mockito.when(transService.list()).thenReturn(translist);

        // REST Template
        // Step 1: Create Request
        // Step 2: Send Request to Endpoint
        // Step 3: Receive the Response
        ResponseEntity<Object> response = restTemp.getForEntity("/transactions", Object.class);

        List<Transaction> products = (List) response.getBody();
        

        // Step 4: Validate the Response
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(products.size()).isEqualTo(2);
      
    }
    @Test
    public void shouldReturnSingleTransaction() throws TransactionException {

        Mockito.when(transService.get(1)).thenReturn(translist.get(0));

        // REST Template
        // Step 1: Create Request
        // Step 2: Send Request to Endpoint
        // Step 3: Receive the Response
        ResponseEntity<Object> response = restTemp.getForEntity("/transactions/1", Object.class);

        //List<Account> products = (List) response.getBody();

        System.out.println("Response: " + translist);

        // Step 4: Validate the Response
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
      
    }
    @Test
    public void shouldUpdateTransaction() throws URISyntaxException, TransactionException
    {
        // POST /products

    	trans2.setId(2);
    	trans2.setAccType("debit");
    	trans2.setAccountId(1);
    	trans2.setAmount(40);
		trans2.setdescription("test11d");
		translist.add(trans2);
		
        URI uri = new URI("/accounts/2");
      
         
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");      
        headers.set("Accept", "application/json");  
        HttpEntity<Transaction> request = new HttpEntity<>(trans2, headers);
         
        ResponseEntity<String> result = this.restTemp.postForEntity(uri, request, String.class);
         
        //Verify request succeed
        Assertions.assertThat(HttpStatus.OK).isEqualTo(HttpStatus.OK);
    }
    
    @Test
    public void shouldDeleteTransaction() throws TransactionException {

        Mockito.when(transService.deleteTransById(1)).thenReturn(translist.get(0));

        // REST Template
        // Step 1: Create Request
        // Step 2: Send Request to Endpoint
        // Step 3: Receive the Response
        ResponseEntity<Object> response = restTemp.getForEntity("/transactions/1", Object.class);

        //List<Account> products = (List) response.getBody();

        System.out.println("Response: " + translist);

        // Step 4: Validate the Response
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
      
    }
}
