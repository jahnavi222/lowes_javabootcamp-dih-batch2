package com.lowes.empapp.service;

import com.lowes.empapp.model.Employee;

public interface EmployeeService {
	//create the employee
	public boolean create(Employee emp);
	//employee size for the arrayimplementation
	public void EmployeeSize(int size);
	//view employee record by id
	public Employee viewEmployeeById (int emp_id);
	//view all the records of employee
	public Employee[] viewAllEmployee ();
	//update the employee record
	public boolean update(Employee emp);
	//delete the employee record
	public boolean delete(int emp_id);

}
