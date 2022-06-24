package com.lowes.bankingapp.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lowes.bankingapp.exception.TransactionException;
import com.lowes.bankingapp.exception.TransactionNotFoundException;
import com.lowes.bankingapp.exception.DBConnectionException;
import com.lowes.bankingapp.model.Account;
import com.lowes.bankingapp.model.ErrorDetails;
import com.lowes.bankingapp.model.ResponseMessage;
import com.lowes.bankingapp.model.Transaction;
import com.lowes.bankingapp.service.TransactionService;
@RequestMapping(value = { "/", "/transactions"} )
@RestController
public class TransactionController {
	
	Logger logger = LoggerFactory.getLogger(TransactionController.class); 

	@Autowired
	TransactionService accService;
	
	

	// Create Account
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> create(@RequestBody @Valid Transaction transaction, BindingResult errors) throws URISyntaxException,TransactionException,DBConnectionException {
		List<ErrorDetails> listError = new ArrayList<ErrorDetails>();
		logger.info("controller:starting of create account method");
		if(errors.hasErrors()) {
			for(ObjectError error:errors.getAllErrors()) {
				logger.error("Validation Error: {} - {} ", error.getObjectName(), error.getDefaultMessage());
				ErrorDetails details = new ErrorDetails(new Date(),error.getObjectName(),error.getDefaultMessage());
				listError.add(details);
			}
			return new ResponseEntity<>(listError, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		// Logic to add account
		try {
		String accountCreated = accService.create(transaction);		
	
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(transaction.getId()).toUri();
		logger.info("controller:end of create account method");
		return ResponseEntity.created(location).body(this.getResponse(transaction.getId(), "Transaction Created"));
		}catch(TransactionException e) {
			logger.error("Unable to create account",e);
			return new ResponseEntity<>(this.getErrorResponse(transaction.getId(), e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	// List Accounts
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?>getAll() throws TransactionNotFoundException,DBConnectionException {
		logger.info("controller:starting of getall account method");
		try {
		if(accService.list().isEmpty()){
			return new ResponseEntity<>("Transaction List is empty", HttpStatus.NOT_FOUND);
		}
		logger.info("controller:end of getall account method");
		return new ResponseEntity<>(accService.list(), HttpStatus.OK);
		}catch(Exception e) {
			logger.error("Unable to getall account list",e);
			return new	ResponseEntity<>(this.getErrorResponse(1, e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	// Get Account
	@GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> get(@PathVariable int id) throws TransactionNotFoundException,DBConnectionException {
		logger.info("controller:starting of get account method");
		try {
		Transaction account=accService.get(id);
              if(account!=null){
            	  return new ResponseEntity<>(account, HttpStatus.OK);
		 }
              logger.info("controller:end of get account method");
            return new ResponseEntity<>(this.getResponse(id, "Transaction id not found"), HttpStatus.NOT_FOUND);
            	
	
	}catch(NoSuchElementException e) {
		logger.error("Unable to get account ",e);
		return new	ResponseEntity<>(this.getErrorResponse(id, e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
	}
		
	}

	// Update Account
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable int id, @RequestBody @Valid Transaction transaction,BindingResult errors)throws TransactionNotFoundException,DBConnectionException{
		Transaction updatedAcc=null;
		List<ErrorDetails> listError = new ArrayList<ErrorDetails>();
		logger.info("controller:starting of update account method");
		if(errors.hasErrors()) {
			for(ObjectError error:errors.getAllErrors()) {
				logger.error("Validation Error: {} - {} ", error.getObjectName(), error.getDefaultMessage());
				ErrorDetails errdetails = new ErrorDetails(new Date(),error.getObjectName(),error.getDefaultMessage());
				listError.add(errdetails);
			}
			return new ResponseEntity<>("ValidationError"+""+listError, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		try {
			transaction.setId(id);
		 updatedAcc = accService.update(id, transaction);
		 logger.info("controller:end of update account method");
		 return new ResponseEntity<>(this.getResponse(updatedAcc.getId(), "Transaction updated"), HttpStatus.NOT_FOUND);
	}catch(Exception e) {
		logger.error("Unable to update account",e);
		return new	ResponseEntity<>(this.getErrorResponse(updatedAcc.getId(),e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
	

	// Delete Account
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id)throws TransactionNotFoundException,DBConnectionException {
		logger.info("controller:starting of delete account method");
		try {
		accService.delete(id);
		
		 logger.info("controller:end of update account method");
		return new ResponseEntity<>(this.getResponse(id, "Transaction deleted sucessfully"), HttpStatus.OK);
		}catch(Exception e) {
			logger.error("Unable to delete account",e);
			return new	ResponseEntity<>(this.getErrorResponse(id,e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ExceptionHandler(TransactionException.class)
	public ResponseEntity<ResponseMessage>  handleErrors(TransactionException ex) {
		ResponseMessage response = new ResponseMessage();
		response.setId(-1);
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
		response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setMessage(ex.getMessage());
		return ResponseEntity.internalServerError().body(response);
	}	
	
	@ExceptionHandler(TransactionNotFoundException.class)
	public ResponseEntity<ResponseMessage>  handleErrors1(TransactionNotFoundException ex) {
		ResponseMessage response = new ResponseMessage();
		response.setId(-1);
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
		response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setMessage(ex.getMessage());
		return ResponseEntity.internalServerError().body(response);
	}
	
	@ExceptionHandler(DBConnectionException.class)
	public ResponseEntity<ResponseMessage>  handleErrors2(DBConnectionException ex) {
		ResponseMessage response = new ResponseMessage();
		response.setId(-1);
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
		response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setMessage(ex.getMessage());
		return ResponseEntity.internalServerError().body(response);
	}
	
	private ResponseMessage getResponse(Integer id, String message) {
		ResponseMessage response = new ResponseMessage();
		response.setId(id);
		response.setStatus(HttpStatus.OK.name());
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage(message);
		return response;
	}

	private ResponseMessage getErrorResponse(Integer id, String message) {
		ResponseMessage response = new ResponseMessage();
		response.setId(id);
		response.setStatus(HttpStatus.BAD_REQUEST.name());
		response.setStatusCode(HttpStatus.BAD_REQUEST.value());
		response.setMessage(message);
		return response;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	private ResponseEntity<ResponseMessage> handleValidationException(MethodArgumentNotValidException ex) {
		FieldError error = ex.getBindingResult().getFieldError("name");
		System.out.println("Error Message: " + error.getCode() + " - " + error.getDefaultMessage());
		return ResponseEntity.badRequest().body(this.getErrorResponse(-1, error.getDefaultMessage()));
	}

}
