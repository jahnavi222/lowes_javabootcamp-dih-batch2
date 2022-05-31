package com.lowes.empapp.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.lowes.empapp.exception.EmployeeException;
import com.lowes.empapp.exception.EmployeeNotFoundException;
import com.lowes.empapp.model.Employee;
import com.lowes.empapp.util.DBConnUtil;

public class EmployeeDaoJdbcImpl implements EmployeeDAo {

	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	DBConnUtil dbUtil = new DBConnUtil();

	@Override
	public boolean create(Employee emp) throws EmployeeException {
		// Insertion with Prepared Statement

		boolean flag = false;
		String insertQueryForPrepareStmt = "INSERT INTO employee (name, age, designation, department, country,doj,createdTime,modifiedTime) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			try {
				conn = dbUtil.getDBCOnnection();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Connect done in create:" + conn);
			pstmt = conn.prepareStatement(insertQueryForPrepareStmt);
			pstmt.setString(1, emp.getName());
			pstmt.setInt(2, emp.getAge());
			pstmt.setString(3, emp.getDesignation());
			pstmt.setString(4, emp.getDepartment());
			pstmt.setString(5, emp.getCountry());
			pstmt.setDate(6, Date.valueOf(emp.getDoj().toString()));
			pstmt.setTimestamp(7, java.sql.Timestamp.valueOf(LocalDateTime.now()));
			pstmt.setTimestamp(8, java.sql.Timestamp.valueOf(LocalDateTime.now()));
			int insertCount = pstmt.executeUpdate();
			conn.commit();

			System.out.println(insertCount + " Employee(s) inserted");
			flag = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new EmployeeException("Unable to create employee");
		} finally {
			if (conn != null && pstmt != null) {
				try {
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new EmployeeException("Unable to close the connection while creating records");
				}
			}
		}
		return flag;

	}

	@Override
	public List<Employee> viewAllEmployee() throws EmployeeNotFoundException {
		Employee emp = null;
		List<Employee> empList = new ArrayList<Employee>();
		String selectQuery = "SELECT id,age,name,designation,department,country,doj,createdTime,modifiedTime FROM employee";
		try {
			try {
				conn = dbUtil.getDBCOnnection();
			} catch (ClassNotFoundException | IOException e) {

				e.printStackTrace();
			}
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(selectQuery);
			// Header
			// System.out.format("\t%s \t%s \t%s \t%s \t%s \t%s\n", "Id", "Age", "Name",
			// "Designation", "Department",
			// "Country");
			while (rs.next()) {
				// Retrieve by column name
				int id = rs.getInt("id");
				int age = rs.getInt("age");
				String name = rs.getString("name");
				String designation = rs.getString("designation");
				String department = rs.getString("department");
				String country = rs.getString("country");
				Date doj = rs.getDate("doj");
				Timestamp createdTime = rs.getTimestamp("createdTime");
				Timestamp modifiedTime = rs.getTimestamp("modifiedTime");

				emp = new Employee(name, age, department, designation, country, doj.toLocalDate(),
						createdTime.toLocalDateTime(), modifiedTime.toLocalDateTime());
				emp.setEmpId(id);

				empList.add(emp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new EmployeeNotFoundException("Unable to retrive the employee records");
		
		} finally {
			if (conn != null && pstmt != null) {
				try {
					rs.close();
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new EmployeeNotFoundException("Unable to view the close the database connection");
					
				}
			}

		}

		return empList;

	}

	public boolean update(Employee emp) throws EmployeeNotFoundException {
		// Updation with Prepared Statement
		boolean flag = false;
		String updateQuery = "UPDATE employee SET designation = ?, department = ?, age = ?, "
				+ " modifiedTime = ?, name = ?, country = ? " + " WHERE id = ?";
		try {
			try {
				conn = dbUtil.getDBCOnnection();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}

			pstmt = conn.prepareStatement(updateQuery);

			pstmt.setString(1, emp.getDesignation());
			pstmt.setString(2, emp.getDepartment());
			pstmt.setInt(3, emp.getAge());
			pstmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
			pstmt.setString(5, emp.getName());
			pstmt.setString(6, emp.getCountry());
			pstmt.setInt(7, emp.getEmpId());

			int updateCount = pstmt.executeUpdate();

			conn.commit();
			flag = true;

			System.out.println(updateCount + " Employee(s) updated");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new EmployeeNotFoundException("Unable to update the employee");
			
		} finally {
			if (conn != null && pstmt != null) {
				try {
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new EmployeeNotFoundException("Unable to update and close the database connection");
				}
			}
		}

		return flag;
	}

	@Override
	public boolean delete(int emp_id) throws EmployeeNotFoundException {
		// Deletion with Prepared Statement
		boolean flag = false;
		String deleteQuery = "DELETE FROM employee WHERE id = ?";
		try {
			try {
				conn = dbUtil.getDBCOnnection();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			pstmt = conn.prepareStatement(deleteQuery);
			pstmt.setInt(1, emp_id);
			int deleteCount = pstmt.executeUpdate();

			// persist the changes
			conn.commit();
			flag = true;

			System.out.println(deleteCount + " Employee(s) deleted");
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new EmployeeNotFoundException("Unable to delete employee");
		} finally {
			if (conn != null && pstmt != null) {
				try {
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new EmployeeNotFoundException("Unable to close the connection while deletion");
				}
			}
		}

		return flag;
	}

	@Override
	public Employee viewEmployeeById(int emp_id) throws EmployeeNotFoundException {
		Employee emp = null;

		String selectQuery = "SELECT id,age,name,designation,department,country,doj,createdTime,modifiedTime "
				+ " FROM employee where id = ?";

		try {
			try {
				conn = dbUtil.getDBCOnnection();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}

			pstmt = conn.prepareStatement(selectQuery);
			pstmt.setInt(1, emp_id);
			rs = pstmt.executeQuery();

			// Header
			// System.out.format("\t%s \t%s \t%s \t%s \t%s \t%s\n", "Id", "Age", "Name",
			// "Designation", "Department",
			// "Country");

			while (rs.next()) {
				// Retrieve by column name
				int id = rs.getInt("id");
				int age = rs.getInt("age");
				String name = rs.getString("name");
				String designation = rs.getString("designation");
				String department = rs.getString("department");
				String country = rs.getString("country");
				Date doj = rs.getDate("doj");
				Timestamp createdTime = rs.getTimestamp("createdTime");
				Timestamp modifiedTime = rs.getTimestamp("modifiedTime");

				emp = new Employee(name, age, department, designation, country, doj.toLocalDate(),
						createdTime.toLocalDateTime(), modifiedTime.toLocalDateTime());
				emp.setEmpId(id);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new EmployeeNotFoundException("Unable to find  the employee");
		} finally {
			if (conn != null && pstmt != null) {
				try {
					rs.close();
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new EmployeeNotFoundException("Unable to view the close the database connection");
				}
			}

		}

		return emp;

	}

}
