package com.lowes.empapp.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.lowes.empapp.exception.EmployeeException;
import com.lowes.empapp.exception.EmployeeNotFoundException;
import com.lowes.empapp.model.ResponseMessage;


@ControllerAdvice
public class EmployeeExceptionHandler {
	

	@ExceptionHandler(EmployeeException.class)
    public ResponseEntity<ResponseMessage> handleEmpException(EmployeeException e){
		
		ResponseMessage response= new ResponseMessage("Failure",e.getErrorMsg());
		return ResponseEntity.internalServerError().body(response);
		
	}
	@ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ResponseMessage> handleEmpNotFound(EmployeeNotFoundException ex){
		
		ResponseMessage response= new ResponseMessage("Failure",ex.getErrorMsg());
		return ResponseEntity.internalServerError().body(response);
		
	}
	@ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseMessage> handleEmpGenErrors(Exception ex){
		
		ResponseMessage response= new ResponseMessage("Failure",ex.getMessage());
		return ResponseEntity.internalServerError().body(response);
		
	}
	
	/*@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
	    return ex.getBindingResult()
	        .getAllErrors().stream()
	        .map(ObjectError::getDefaultMessage)
	        .collect(Collectors.toList());
	}*/
}
