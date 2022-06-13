package com.lowes.empapp.controller;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
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

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lowes.empapp.exception.EmployeeException;
import com.lowes.empapp.exception.EmployeeExceptionHandler;
import com.lowes.empapp.exception.EmployeeNotFoundException;
import com.lowes.empapp.model.Employee;
import com.lowes.empapp.model.ResponseMessage;
import com.lowes.empapp.service.EmployeeServiceJdbcmpl;
import com.lowes.empapp.validator.EmployeeValidator;

/**
 * Handles requests for the employee management.
 */
@RequestMapping(value = { "/", "/employee" }, method = RequestMethod.GET)
@RestController
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String greeting() {
		return "Welcome to RESTful services training :)";
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
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseMessage> addEmployee(@RequestBody @Validated Employee employee)
			throws EmployeeException, EmployeeNotFoundException {
		logger.info("controller-strating of addemployee method");

		try {

			empService.create(employee);
			int id = empService.getLastEmpInsId();
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
			ResponseMessage response = new ResponseMessage("Success", "employee created sucessfully");
			logger.info("controller-End of addemployee method");
			return ResponseEntity.created(location).body(response);
		} catch (Exception ex) {
			logger.error("Controller-Unable to create employee", ex);
			//exceHandler.handleEmpGenErrors(ex);
			ResponseMessage response = new ResponseMessage("Failure", ex.getMessage());

			// return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			return ResponseEntity.internalServerError().body(response);
		}
	}

	// view all employess method
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Employee>> getEmployees() throws EmployeeNotFoundException {
		logger.info("controller-strating of getEmployees method");
		try {
			return new ResponseEntity<>(empService.viewAllEmployee(), HttpStatus.OK);
		} catch (Exception e) {
			exceHandler.handleEmpGenErrors(e);
			logger.error("Controller-Unable to get employee list", e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// view employee by id method
	@GetMapping(path = "/test/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id) {
		logger.info("controller-strating of getEmployees method");
		try {
			// check if employee exist in database
			Employee empObj = empService.viewEmployeeById(id);

			if (empObj != null) {
				return new ResponseEntity<>(empObj, HttpStatus.OK);
			}
			logger.info("controller-end of getEmployees method");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			exceHandler.handleEmpGenErrors(e);
			logger.error("Controller-Unable to get employee list", e);
			ResponseMessage response = new ResponseMessage("Failure", e.getMessage());

			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			// return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// update employee method
	@PutMapping(path = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseMessage> updateEmployee(@PathVariable("id") String id,
			@RequestBody @Validated Employee emp) throws EmployeeNotFoundException {
		logger.info("controller-strating of update method");
		try {
			emp.setId(Integer.parseInt(id));
			boolean empUpdate = empService.update(emp);
			logger.info("controller-end of update method");
			ResponseMessage response = new ResponseMessage("Success", "Employee update sucessfully");
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			exceHandler.handleEmpGenErrors(e);
			logger.error("Controller-Unable to update employee ", e);
			ResponseMessage response = new ResponseMessage("Failure", e.getMessage());

			// return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			return ResponseEntity.internalServerError().body(response);
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

			// return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			return ResponseEntity.internalServerError().body(response);
		}
	}

}
