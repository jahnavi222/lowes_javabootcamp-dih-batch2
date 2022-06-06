package com.lowes.empapp.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Component
public class DBConnUtil {
	@Autowired @Lazy
	DataSource dataSource;
	Connection conn = null;
	static PreparedStatement pstmt;

	private String url;
	private String username;
	private String password;
	public Connection conncetion;

	public Connection getConncetion() {
		return conncetion;
	}

	public void setConncetion(Connection conncetion) {
		this.conncetion = conncetion;
	}

	public Connection getDBCOnnection()
			throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {

		//System.out.println("Connecting to database...");

		conn = dataSource.getConnection();
		//System.out.println("Connection estabilished: " + conn);

		return conn;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String geturl() {
		return url;
	}

	public void seturl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void closeConnection() {
		try {
			System.out.println("Connection closing in method");
			conn.close();
			System.out.println("Connection closed in method" + "" + conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initDB() {

		try {
			System.out.println("Connection establishing init method");
			Connection Conn = getDBCOnnection();
			setDataSource(dataSource);
			setConncetion(Conn);
			System.out.println("Connection in init method" + " " + Conn);
		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void CleanUPDBCon() throws SQLException {
		System.out.println("Connection closing in method");

		closeConnection();
		System.out.println("Connection closed in method" + "" + conn);

	}

}
