package com.lowes.empapp.service;



import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowes.empapp.controller.EmployeeController;
import com.lowes.empapp.exception.EmployeeException;
import com.lowes.empapp.exception.EmployeeNotFoundException;
import com.lowes.empapp.model.Employee1;
import com.lowes.empapp.repository.EmployeeRepository;
@Service
//this is service class implementation
public class EmployeeServiceJdbcmpl implements EmployeeService, Comparator<Employee1> {
	
	Logger logger = LoggerFactory.getLogger(EmployeeServiceJdbcmpl.class); 
    @Autowired
    EmployeeRepository empRep;
    Employee1 emp = null;
	LocalDateTime nowTime = LocalDateTime.now();
	private static List<Employee1> empListStats = null;
	
	

	public EmployeeServiceJdbcmpl() {		
	}

	// create the employee record
	@Override
	public Employee1 create(Employee1 emp) throws EmployeeException {
		Employee1 emp1=null;
		logger.info("Service-strating of create method");
		try {
		emp1= empRep.save(emp);
		}catch(Exception e) {
			logger.error("Service-unable to craete employee" ,e);	
		}
        logger.info("Service-end of create method");
		return emp1;
	}

	// view the employee details by id
	@Override
	public Employee1 viewEmployeeById(int emp_id) throws EmployeeNotFoundException  {
		// emp = null;
		logger.info("Service-strating of viewEmployeeById method");
		try {
		emp = empRep.findById(emp_id).get();
		}catch(Exception e) {
			logger.error("Service-unable to get employee by id" ,e);	
		}
		logger.info("Service-end of viewEmployeeById method");
		return emp;
	}
	
	public int getLastEmpInsId() throws EmployeeNotFoundException {
		int id=0;
		logger.info("Service-strating of getLastEmpInsId method");
		try {
		  id = empRep.findByLastRecordId();
		}catch(Exception e) {
			logger.error("Service-unable to get last record of id" ,e);	
		}
		//logger.info("last record of emp id"+""+id);
		logger.info("Service-end of getLastEmpInsId method");
		return id;

	}

	//// view all employee details
	@Override
	public List<Employee1> viewAllEmployee() throws EmployeeNotFoundException {
		logger.info("Service-strating of viewAllEmployee method");
		try {
		empListStats = empRep.findAll();
	 }catch(Exception e) {
		logger.error("Service-unable to getall employee" ,e);	
	}
		logger.info("Service-strating of viewAllEmployee method");
		return empListStats;

	}

	// update the employee
	@Override
	public Employee1 update(Employee1 emp) throws EmployeeNotFoundException {
		Employee1 emp2=null;
		logger.info("Service-start of updateEmployee method");
        try {
		emp2 = empRep.save(emp);
        }catch(Exception e) {
    		logger.error("Service-unable to update employee" ,e);	
    	}
		logger.info("Service-end of updateEmployee method");
		return emp2;
	}

	// delete the employee
	@Override
	public void delete(int emp_id) throws EmployeeNotFoundException {
		
		logger.info("Service-start of updateEmployee method");
		try {
		empRep.deleteById(emp_id);
		}catch(Exception e) {
    		logger.error("Service-unable to delete employee" ,e);	
    	}
		logger.info("Service-end of updateEmployee method");
		

	}

	@Override // sort the employee based on empid
	public int compare(Employee1 o1, Employee1 o2) {

		return o1.getId() - o2.getId();
	}


}
