package com.lowes.empapp.service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.lowes.empapp.exception.EmployeeException;
import com.lowes.empapp.exception.EmployeeNotFoundException;
import com.lowes.empapp.model.Employee;
@Component
public interface EmployeeService {
	//create the employee
	public boolean create(Employee emp) throws EmployeeException;
	//view employee record by id
	public Employee viewEmployeeById (int emp_id);
	//view all the records of employee
	public List<Employee>viewAllEmployee () throws EmployeeNotFoundException;
	//update the employee record
	public boolean update(Employee emp) throws EmployeeNotFoundException;
	//delete the employee record
	public boolean delete(int emp_id) throws EmployeeNotFoundException;
	//import employee file using thread
    public void bulkImport() throws EmployeeException;
    //write the employee details to file using thread
    public void bulkExport();
    //get   count of employee age  greater than condition
    public long getEmployeeCountAgeGreaterThan(Predicate<Employee> condition);
  //get employee age greater than condition
    public List<Integer> getEmployeesIdsAgeGreaterThan(int age);
   //get employee count by department
    public Map<String, Long> getEmployeeCountByDepartment();
  //get employee count by department by orderd
   public Map<String, Long> getEmployeeCountByDepartmentOrdered();
   //getemployee age by department
   public Map<String, Double> getAvgEmployeeAgeByDept();
   //get number of employees having more in department
   public List<String> getDepartmentsHaveEmployeesMoreThan(int criteria);
 //getemployee name start with prefix
   public List<String> getEmployeeNamesStartsWith(String prefix);
   //getEmployee service by department
   public Map<String, Double> getAvgEmployeeServiceByDept();
   //validate the employee
   public boolean validate(Employee emp,String msg,Predicate<Employee> condition,Function<String,Boolean> Operation);

}
