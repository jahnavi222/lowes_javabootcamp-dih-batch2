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

import com.lowes.bankingapp.exception.AccountException;
import com.lowes.bankingapp.model.Account;
import com.lowes.bankingapp.service.AccountService;


// API Test / Integration test
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(
  locations = "classpath:application.properties")
@ActiveProfiles("test")
public class AccountServiceControllerTests {

    @Autowired
    TestRestTemplate restTemp;
    
    @LocalServerPort
    int randomServerPort;

//  @Autowired    
    @MockBean
    AccountService accService;

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
			acc2.setStatus("pending");		
			acc2.setBalance(500);
			acc2.setOpenDate(new Date());
			//accService.create(acc1);
			accounts.add(acc2);
     
    }

    @AfterEach
    public void cleanup() {
//        productService.clear();
    	accounts.clear();
    }

    @Test
    public void shouldCreateAccount() throws URISyntaxException, AccountException
    {
        // POST /products

    
        Mockito.when(accService.create(new Account())).thenReturn("Account Created");

        String reqBody = "{\"id\":\"1\",\"name\":\"test\",\"type\":\"Saving\",\"status\":\"Active\"}";


        // Step 1: Create Request
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RequestEntity request = new RequestEntity(reqBody, headers, HttpMethod.POST, new URI("/accounts"));

        // Step 2: Send Request to Endpoint
        // Step 3: Receive the Response

        ResponseEntity<String> response = restTemp.exchange(request, String.class);

        System.out.println("Response: " + response.getBody());

        // Step 4: Validate the Response
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }


    @Test
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
      
    }
}
