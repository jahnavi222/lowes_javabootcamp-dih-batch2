package com.labs.day10;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DBConnUtil {
	
	public static Connection getDBCOnnection() {
	Connection conn = null;
	Statement stmt = null;
	
	// STEP 2: Create Datasource instance
	MysqlDataSource dataSource = new MysqlDataSource();
	
	// Loading configuration from properties file
	Properties prop = new Properties();
	try {
		prop.load(new FileInputStream("jdbc.properties"));
		String dbServerName = prop.getProperty("dbServerName");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		
		dataSource.setDatabaseName("jdbctraining");
		dataSource.setServerName(dbServerName);
		dataSource.setUser(username);
		dataSource.setPassword(password);


		// STEP 3: Open a connection
		System.out.println("Connecting to database...");
		conn = dataSource.getConnection();
		conn.setAutoCommit(false); // enable transaction

		System.out.println("Connection estabilished: " + conn);

		// STEP 4: Execute a query
		System.out.println("Creating statement...");
		stmt = conn.createStatement();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	return conn;
	}

}
