package com.lowes.empapp.config;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.lowes.empapp.dao.EmployeeDAo;
import com.lowes.empapp.dao.EmployeeDaoJdbcImpl;
import com.lowes.empapp.model.Employee;
import com.lowes.empapp.service.EmployeeService;
import com.lowes.empapp.service.EmployeeServiceJdbcmpl;
import com.lowes.empapp.util.DBConnUtil;
import com.mysql.cj.jdbc.MysqlDataSource;

@Configuration
@ComponentScan(basePackages = "com.lowes.empapp")
public class EmployeeConfig {
	 @Bean
		public EmployeeService employeeServiceJdbcmpl() {
		 EmployeeDAo empDao = employeeDaoJdbcImpl();
		 EmployeeService employeeServiceImpl = new EmployeeServiceJdbcmpl(empDao);		
			return employeeServiceImpl;
		}
	
	@Bean
	public EmployeeDAo employeeDaoJdbcImpl() {
		DBConnUtil dbUtil = getDBUtil();
		EmployeeDAo daoImpl = new EmployeeDaoJdbcImpl(dbUtil);
		//daoImpl.setDataSource(dataConnection());
		return daoImpl;
	}

   
    
	
	@Bean(autowire=Autowire.BY_TYPE,initMethod="initDB",destroyMethod="CleanUPDBCon")
	  public DBConnUtil getDBUtil(){
		DBConnUtil dbUtil = new DBConnUtil();
		dbUtil.setDataSource(dataConnection());
	    return dbUtil;
	  }
	
	
	@Bean(autowireCandidate=true)
	  public MysqlDataSource dataConnection() {
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setDatabaseName("jdbctraining");
		dataSource.setUser("training");
		dataSource.setPassword("training");
		dataSource.setServerName("localhost");	
		return dataSource;
		
	  }
	@Bean
	public Employee empBean() {
		Employee emp = new Employee();
		
		return emp;
	}
}
