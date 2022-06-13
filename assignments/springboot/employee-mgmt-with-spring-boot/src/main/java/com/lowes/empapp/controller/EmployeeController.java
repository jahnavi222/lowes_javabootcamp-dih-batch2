package com.lowes.empapp.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import com.lowes.empapp.exception.EmployeeException;
import com.lowes.empapp.exception.EmployeeExceptionHandler;
import com.lowes.empapp.exception.EmployeeNotFoundException;
import com.lowes.empapp.exception.ValidationException;
import com.lowes.empapp.model.Employee1;
import com.lowes.empapp.model.ResponseMessage;
import com.lowes.empapp.service.EmployeeServiceJdbcmpl;
import com.lowes.empapp.validator.EmployeeValidator;


/**
 * Handles requests for the employee management.
 */

@RequestMapping(value = { "/", "/employee" }, method = RequestMethod.GET)
@RestController
public class EmployeeController {

	Logger logger = LoggerFactory.getLogger(EmployeeController.class); 

	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public String greeting() {
		return "Welcome to RESTful Employee Management App";
	}

	@Autowired
   EmployeeServiceJdbcmpl empService;
	@Autowired
	private EmployeeValidator validator;
	@Autowired
	EmployeeExceptionHandler exceHandler;

	@InitBinder
	private void initBinder(WebDataBinder binder) {

		binder.setValidator(validator);
	}

	// add employee method
	@PostMapping(value = "/employee",consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces = {
			MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?> addEmployee(@RequestBody  @Validated Employee1 employee,BindingResult errors)
			throws EmployeeException, EmployeeNotFoundException {
		logger.info("controller-strating of addemployee method");
	
		try {
			int id=-1;
		
			if(errors.hasErrors()) {
				for(ObjectError error:errors.getAllErrors()) {
					logger.error("Validation Error: {} - {} ", error.getObjectName(), error.getDefaultMessage());
					return new ResponseEntity<>("error:"+error.getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
					
	
				}
			
				
			}
			
			empService.create(employee);
			if(employee!=null) {
			id = empService.getLastEmpInsId();
			}
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
			ResponseMessage response = new ResponseMessage("Success", "employee created sucessfully");
			logger.info("controller-End of addemployee method");
			return ResponseEntity.created(location).body(response);
		}	catch(Exception ex) {
			//ResponseMessage response = new ResponseMessage("Failure", ex.getMessage());
			ResponseMessage response1 = new ResponseMessage("Failure","unable to  add employee");
			return new ResponseEntity<>(response1, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
			
			//return ResponseEntity.internalServerError().body(response);
		
	}

	// view all employess method
	@GetMapping(value = "/employee",produces = { MediaType.APPLICATION_JSON_VALUE ,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?> getEmployees() throws EmployeeNotFoundException {
		logger.info("controller-strating of getEmployees method");
		try {
			if((empService.viewAllEmployee().isEmpty()))
					{
				ResponseMessage response1 = new ResponseMessage("Success","employee list is empty");
				return new ResponseEntity<>(response1, HttpStatus.NOT_FOUND);
					}
			return new ResponseEntity<>(empService.viewAllEmployee(), HttpStatus.OK);
		} catch (Exception e) {
			exceHandler.handleEmpGenErrors(e);
			//ResponseMessage response = new ResponseMessage("Failure", e.getMessage());
			ResponseMessage response1 = new ResponseMessage("Failure","employee not found");
			logger.error("Controller-Unable to get employee list", e);
			return new ResponseEntity<>(response1, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// view employee by id method
	@GetMapping(value ="/employee/test/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?> getEmployeeById(@PathVariable("id") int id)throws EmployeeNotFoundException {
		logger.info("controller-strating of getEmployees method");
		try {
			// check if employee exist in database
			Employee1 empObj = empService.viewEmployeeById(id);
					
			if (empObj != null) {
				return new ResponseEntity<>(empObj, HttpStatus.OK);
			}
			logger.info("controller-end of getEmployees method");
			ResponseMessage response = new ResponseMessage("Failure","employee not found");
			
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			exceHandler.handleEmpGenErrors(e);
			logger.error("Controller-Unable to get employee list", e);
			ResponseMessage response = new ResponseMessage("Failure", e.getMessage());
			response.setMessage("Unable to get employee details due to exception");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			// return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// update employee method
	@PutMapping(path = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseMessage> updateEmployee(@PathVariable("id") String id,
			@RequestBody @Valid Employee1 emp) throws EmployeeNotFoundException {
		logger.info("controller-strating of update method");
		try {
			emp.setId(Integer.parseInt(id));
			 empService.update(emp);
			logger.info("controller-end of update method");
			ResponseMessage response = new ResponseMessage("Success", "Employee update sucessfully");
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			exceHandler.handleEmpGenErrors(e);
			logger.error("Controller-Unable to update employee ", e);
			ResponseMessage response = new ResponseMessage("Failure", e.getMessage());
			response.setMessage("Unable to get employee details due to exception");
			 return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			//return ResponseEntity.internalServerError().body(response);
		}

	}

	// delete employee method
	@DeleteMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseMessage> deleteEmployee(@PathVariable("id") int id) throws EmployeeNotFoundException {
		logger.info("controller-strating of delete method");
		try {
			empService.delete(id);
			ResponseMessage response = new ResponseMessage("Success", "Employee deleted sucessfully");
			logger.info("controller-end of delete method");
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			exceHandler.handleEmpGenErrors(e);
			logger.error("Controller-Unable to delete employee list", e);
			ResponseMessage response = new ResponseMessage("Failure", e.getMessage());

			 return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			//return ResponseEntity.internalServerError().body(response);
		}
	}

}
