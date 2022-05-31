package com.lowes.empapp.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.Properties;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DBConnUtil {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/jdbctraining";

	// Database credentials
	static final String USER = "training";
	static final String PASS = "training";

	
	public  Connection getDBCOnnection() throws FileNotFoundException, ClassNotFoundException, IOException {
	Connection conn = null;
	
	
	// STEP 2: Create Datasource instance
//	MysqlDataSource dataSource = new MysqlDataSource();
	
	// Loading configuration from properties file
//	Properties prop = new Properties();
	try {
		Class.forName(JDBC_DRIVER);

		// STEP 3: Open a connection
		System.out.println("Connecting to database...");
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		conn.setAutoCommit(false); // enable transaction

		System.out.println("Connection estabilished: " + conn);
		

		System.out.println("Connection estabilished: " + conn);

		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	
	
	

	}
	return conn;
	}
}


