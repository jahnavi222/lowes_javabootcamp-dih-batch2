package com.labs.day10;

import java.util.List;

public interface EmployeeDAo {
public boolean create(Employee emp);
//view employee record by id
public Employee viewEmployeeById (int emp_id);
//view all the records of employee
public List<Employee>viewAllEmployee ();
//update the employee record
public boolean update(Employee emp);
//delete the employee record
public boolean delete(int emp_id);
}
