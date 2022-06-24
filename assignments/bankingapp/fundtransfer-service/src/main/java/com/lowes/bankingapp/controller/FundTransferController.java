package com.lowes.bankingapp.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lowes.bankingapp.exception.DBConnectionException;
import com.lowes.bankingapp.exception.FundTransferException;
import com.lowes.bankingapp.model.Account;
import com.lowes.bankingapp.model.ErrorDetails;
import com.lowes.bankingapp.model.FundTransfer;
import com.lowes.bankingapp.model.ResponseMessage;

import com.lowes.bankingapp.service.FundTransferService;

@RequestMapping(value = { "/", "/fundtransfer" })
@PropertySource("classpath:application.properties")
@RestController
public class FundTransferController {
	@Autowired
	private Environment env;
	Logger logger = LoggerFactory.getLogger(FundTransferController.class);

	@Autowired
	FundTransferService accService;
	@Autowired
	private RestTemplateBuilder restTemplateBuilder;;

	// Create credit transfer
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> createCredit(@Valid @RequestBody FundTransfer fundtransfer, BindingResult errors)
			throws URISyntaxException, FundTransferException, DBConnectionException {
		List<ErrorDetails> listError = new ArrayList<ErrorDetails>();
		logger.info("controller:starting of  fundtransfer method");
		if (errors.hasErrors()) {
			for (ObjectError error : errors.getAllErrors()) {
				logger.error("Validation Error: {} - {} ", error.getObjectName(), error.getDefaultMessage());
				ErrorDetails details = new ErrorDetails(new Date(), error.getObjectName(), error.getDefaultMessage());
				listError.add(details);
			}
			return new ResponseEntity<>(listError, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		Account accountl = getBalanceDetails(env, fundtransfer);

		logger.info("entetd");
		if (accountl != null && accountl.getBalance() < fundtransfer.getAmount()) {
			ErrorDetails details = new ErrorDetails(new Date(), "error", "No Sufficient Balance in the account");
			return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
		}

		int credit = 0;
		int debit = 0;
		// Logic to add account
		try {
			credit = accService.createCreditTransfer(fundtransfer);
			debit = accService.createDebitTransfer(fundtransfer);

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/credit}").buildAndExpand(credit)
					.toUri();
			logger.info("controller:end of fund transfer method");
			return ResponseEntity.created(location).body(this.getResponse(credit, "Fund Transferred Sucessfully"));
		} catch (FundTransferException e) {
			logger.error("Unable to transfer account", e);
			return new ResponseEntity<>(this.getErrorResponse(credit, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	
	// List Accounts
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getAll() throws FundTransferException, DBConnectionException {
		logger.info("controller:starting of getall account method");
		try {
			if (accService.list().isEmpty()) {
				return new ResponseEntity<>("Account List is empty", HttpStatus.NOT_FOUND);
			}
			logger.info("controller:end of getall account method");
			return new ResponseEntity<>(accService.list(), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Unable to getall account list", e);
			return new ResponseEntity<>(this.getErrorResponse(1, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// Get Account
	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> get(@PathVariable int id) throws FundTransferException, DBConnectionException {
		logger.info("controller:starting of get account method");
		try {
			FundTransfer account = accService.get(id);
			if (account != null) {
				return new ResponseEntity<>(account, HttpStatus.OK);
			}
			logger.info("controller:end of get account method");
			return new ResponseEntity<>(this.getResponse(id, "accound id not found"), HttpStatus.NOT_FOUND);

		} catch (NoSuchElementException e) {
			logger.error("Unable to get account ", e);
			return new ResponseEntity<>(this.getErrorResponse(id, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// Update Account
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable int id, @RequestBody @Valid FundTransfer account,
			BindingResult errors) throws FundTransferException, DBConnectionException {
		FundTransfer updatedAcc = null;
		List<ErrorDetails> listError = new ArrayList<ErrorDetails>();
		logger.info("controller:starting of update account method");
		/*
		 * if(errors.hasErrors()) { for(ObjectError error:errors.getAllErrors()) {
		 * logger.error("Validation Error: {} - {} ", error.getObjectName(),
		 * error.getDefaultMessage()); ErrorDetails errdetails = new ErrorDetails(new
		 * Date(),error.getObjectName(),error.getDefaultMessage());
		 * listError.add(errdetails); } return new
		 * ResponseEntity<>("ValidationError"+""+listError,
		 * HttpStatus.INTERNAL_SERVER_ERROR); }
		 */
		try {
			account.setId(id);
			updatedAcc = accService.update(id, account);
			logger.info("controller:end of update account method");
			return new ResponseEntity<>(this.getResponse(updatedAcc.getId(), "accound updated"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			logger.error("Unable to update account", e);
			return new ResponseEntity<>(this.getErrorResponse(updatedAcc.getId(), e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Delete Account
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) throws FundTransferException, DBConnectionException {
		logger.info("controller:starting of delete account method");
		try {
			accService.delete(id);

			logger.info("controller:end of update account method");
			return new ResponseEntity<>(this.getResponse(id, "Account deleted sucessfully"), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Unable to delete account", e);
			return new ResponseEntity<>(this.getErrorResponse(id, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ExceptionHandler(FundTransferException.class)
	public ResponseEntity<ResponseMessage> handleErrors(FundTransferException ex) {
		ResponseMessage response = new ResponseMessage();
		response.setId(-1);
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
		response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setMessage(ex.getMessage());
		return ResponseEntity.internalServerError().body(response);
	}

	@ExceptionHandler(DBConnectionException.class)
	public ResponseEntity<ResponseMessage> handleErrors2(DBConnectionException ex) {
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

	@ExceptionHandler(Exception.class)
	private ResponseEntity<ResponseMessage> handleGenericException(Exception ex) {
		System.out.println("Error Message: " + ex.getMessage());

		ResponseMessage response = new ResponseMessage();
		response.setId(-1);
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
		response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setMessage(ex.getMessage());
		return ResponseEntity.badRequest().body(response);

	}

	private Account getBalanceDetails(Environment env, FundTransfer fundtransfer) {
		String path = env.getProperty("gateway.url");

		String[] values = path.replaceAll("}", "").split("/");
		String url = values[0] + "//" + values[2] + "/bankingapp/api/accounts" + "/" + fundtransfer.getSrcAccountId();
		System.out.println(url);
		Account ll = restTemplateBuilder.build().getForObject(url, Account.class);

		return ll;

	}

}
