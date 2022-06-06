package com.lowes.empapp.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.lowes.empapp.exception.EmployeeException;
import com.lowes.empapp.exception.EmployeeNotFoundException;
import com.lowes.empapp.model.Employee;

public interface EmployeeDAo {
public boolean create(Employee emp) throws EmployeeException;
//view employee record by id
public Employee viewEmployeeById (int emp_id) throws EmployeeNotFoundException;
//view all the records of employee
public List<Employee>viewAllEmployee () throws EmployeeNotFoundException;
//update the employee record
public boolean update(Employee emp) throws EmployeeNotFoundException;
//delete the employee record
public boolean delete(int emp_id) throws EmployeeNotFoundException;
}
