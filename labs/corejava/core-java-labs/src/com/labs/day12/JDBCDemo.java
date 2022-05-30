package com.labs.day12;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con=null;
		Statement smt=null;
		ResultSet rs=null;
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded");
			
			 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbctraining","training","training");
			System.out.println(con);
			System.out.println("Connected to DB sucessfully");
			smt=con.createStatement();
			String query="select * from EMPLOYEE";
			rs=smt.executeQuery(query);
			System.out.println("Query executed Sucessfully"+""+query);
			System.out.println("Result"+""+rs);
			while(rs.next()) {
				int id=rs.getInt("id");
				String name =rs.getString("name");
				int age=rs.getInt("age");
				String designation=rs.getString("designation");
				String  department=rs.getString("department");
				String country=rs.getString("country");
				System.out.println(id + name+age+designation+country);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Driver error"+""+e.getMessage());
			e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Connection error"+""+e.getMessage());
			e.printStackTrace();
		}
	}
	

}
