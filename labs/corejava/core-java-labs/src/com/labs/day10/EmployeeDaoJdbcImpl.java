package com.labs.day10;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;



public class EmployeeDaoJdbcImpl implements EmployeeDAo {

	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	@Override
	public boolean create(Employee emp) {
		// Insertion with Prepared Statement
		boolean flag=false;
		String insertQueryForPrepareStmt = "INSERT INTO employee (name, age, designation, department, country) VALUES (?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(insertQueryForPrepareStmt);
			pstmt.setString(1, "Mathew");
			pstmt.setInt(2, 30);
			pstmt.setString(3, "Lead");
			pstmt.setString(4, "IT");
			pstmt.setString(5, "India");
			int insertCount = pstmt.executeUpdate();
			pstmt.close();
			System.out.println(insertCount + " Employee(s) inserted");
			flag=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
		
	}

	@Override
	public Employee viewEmployeeById(int emp_id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Employee> viewAllEmployee() {
		String selectQuery = "SELECT * FROM employee";
		try {
			rs = stmt.executeQuery(selectQuery);
			// Header
			System.out.format("\t%s \t%s \t%s \t%s \t%s \t%s\n", "Id", "Age", "Name", "Designation", "Department",
					"Country");
			while (rs.next()) {
				// Retrieve by column name
				int id = rs.getInt("id");
				int age = rs.getInt("age");
				String name = rs.getString("name");
				String designation = rs.getString("designation");
				String department = rs.getString("department");
				String country = rs.getString("country");

				// Display values
				System.out.format("\t%d \t%d \t%s \t%s \t%s \t%s\n", id, age, name, designation, department, country);
				}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		
		return null;
	
	}

	@Override
	public boolean update(Employee emp) {
		//Updation with Prepared Statement
		boolean flag=false;
		String updateQuery = "UPDATE employee SET designation = ? WHERE id = ?";
		try {
			pstmt = conn.prepareStatement(updateQuery);
			pstmt.setString(1, "Software Engineer");
			pstmt.setInt(2, 1);
			int updateCount = pstmt.executeUpdate();
			flag=false;
			pstmt.close();
			System.out.println(updateCount + " Employee(s) updated");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}

	@Override
	public boolean delete(int emp_id) {
		// Deletion with Prepared Statement
					String deleteQuery = "DELETE FROM employee WHERE id = ?";
					try {
						pstmt = conn.prepareStatement(deleteQuery);
						pstmt.setInt(1, 2);
						int deleteCount = pstmt.executeUpdate();
						pstmt.close();
						System.out.println(deleteCount + " Employee(s) updated");

						// persist the changes
						conn.commit();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
		return false;
	}
	
	

}
