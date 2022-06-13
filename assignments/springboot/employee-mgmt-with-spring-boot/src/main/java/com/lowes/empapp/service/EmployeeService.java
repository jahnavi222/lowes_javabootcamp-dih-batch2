package com.lowes.empapp.service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import com.lowes.empapp.exception.EmployeeException;
import com.lowes.empapp.exception.EmployeeNotFoundException;
import com.lowes.empapp.model.Employee1;
@Service
public interface EmployeeService {
	//create the employee
	public Employee1 create(Employee1 emp) throws EmployeeException;
	//view employee record by id
	public Employee1 viewEmployeeById (int emp_id) throws EmployeeNotFoundException;
	//view all the records of employee
	public List<Employee1>viewAllEmployee () throws EmployeeNotFoundException;
	//update the employee record
	public Employee1 update(Employee1 emp) throws EmployeeNotFoundException;
	//delete the employee record
	public void delete(int emp_id) throws EmployeeNotFoundException;
	
}
