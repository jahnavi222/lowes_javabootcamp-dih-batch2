package com.lowes.empapp.service;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowes.empapp.dao.EmployeeDAo;
import com.lowes.empapp.dao.EmployeeDaoJdbcImpl;
import com.lowes.empapp.exception.EmployeeException;
import com.lowes.empapp.exception.EmployeeNotFoundException;
import com.lowes.empapp.model.Employee;
@Service
//this is service class implementation
public class EmployeeServiceJdbcmpl implements EmployeeService, Comparator<Employee> {
	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceJdbcmpl.class);

    @Autowired
    EmployeeDaoJdbcImpl empDaoSer;
    Employee emp = null;
	LocalDateTime nowTime = LocalDateTime.now();
	private static List<Employee> empListStats = null;
	
	public EmployeeDaoJdbcImpl getEmpDaoSer() {
		return empDaoSer;
	}

	public void setEmpDaoSer(EmployeeDaoJdbcImpl empDaoSer) {
		this.empDaoSer = empDaoSer;
	}

	public EmployeeServiceJdbcmpl() {		
	}

	// create the employee record
	@Override
	public boolean create(Employee emp) throws EmployeeException {
		boolean flag = false;
		logger.info("Service-strating of create method");
		try {
		flag = empDaoSer.create(emp);
		logger.info("Service-end of create method");
		}catch(EmployeeException e) {
			logger.error("Service-unabe to create employee", e);
			throw new EmployeeException("Unable to craete employee");
		}
		return flag;
	}

	// view the employee details by id
	@Override
	public Employee viewEmployeeById(int emp_id)  {
		// emp = null;
		logger.info("Service-strating of viewEmployeeById method");
		try {
			emp = empDaoSer.viewEmployeeById(emp_id);
			
		} catch (EmployeeNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("Service-unabe to get employee", e);
			
		}
		logger.info("Service-end of viewEmployeeById method");
		return emp;
	}
	
	public int getLastEmpInsId() throws EmployeeNotFoundException {
		int id=0;
		logger.info("Service-strating of getLastEmpInsId method");
		try {
			id = empDaoSer.getLastEmpInsId();
			logger.info("last record of emp id"+""+id);
		
		} catch (EmployeeNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Service-unabe to get employee id", e);
			throw new EmployeeNotFoundException("Service-unabe to get employee id");
		}
		logger.info("Service-end of getLastEmpInsId method");
		return id;

	}

	//// view all employee details
	@Override
	public List<Employee> viewAllEmployee() throws EmployeeNotFoundException {
		List<Employee> empList = null;
		logger.info("Service-strating of viewAllEmployee method");
		try {
			empList = empDaoSer.viewAllEmployee();
		} catch (EmployeeNotFoundException ex) {
			logger.error("Service-unabe to get employee", ex);
		}
		logger.info("Service-strating of viewAllEmployee method");
		return empList;

	}

	// update the employee
	@Override
	public boolean update(Employee emp) throws EmployeeNotFoundException {
		// emp = new Employee();
		boolean flag = false;
		logger.info("Service-start of updateEmployee method");

		try {
			flag = empDaoSer.update(emp);
		} catch (EmployeeNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Service-unabe to update employee", e);
			throw new EmployeeNotFoundException("Unable to update employee");

		}
       
		logger.info("Service-end of updateEmployee method");
		return flag;
	}

	// delete the employee
	@Override
	public boolean delete(int emp_id) {
		boolean flag = false;
		logger.info("Service-start of updateEmployee method");
		try {
			flag = empDaoSer.delete(emp_id);
		} catch (EmployeeNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		logger.info("Service-end of updateEmployee method");
		return flag;

	}

	@Override // sort the employee based on empid
	public int compare(Employee o1, Employee o2) {

		return o1.getId() - o2.getId();
	}


}
