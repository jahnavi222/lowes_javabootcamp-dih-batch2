package com.lowes.empapp.util;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lowes.empapp.service.EmployeeServiceJdbcmpl;


public class DBConnUtil {
	private static final Logger logger = LoggerFactory.getLogger(DBConnUtil.class);

	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/jdbctraining";

	// Database credentials
	static final String USER = "training";
	static final String PASS = "training";

	
	public  Connection getDBCOnnection() throws FileNotFoundException, ClassNotFoundException, IOException {
	Connection conn = null;
	
	
	try {
		Class.forName(JDBC_DRIVER);

		// STEP 3: Open a connection
		System.out.println("Connecting to database...");
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		

		System.out.println("Connection estabilished: " + conn);

		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	
	
	

	}
	return conn;
	}
}


