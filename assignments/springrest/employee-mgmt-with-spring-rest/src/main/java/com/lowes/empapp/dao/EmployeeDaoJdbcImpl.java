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
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lowes.empapp.exception.EmployeeException;
import com.lowes.empapp.exception.EmployeeNotFoundException;
import com.lowes.empapp.model.Employee;
import com.lowes.empapp.util.DBConnUtil;

@Repository
public class EmployeeDaoJdbcImpl implements EmployeeDAo {
	private static final Logger logger = LoggerFactory.getLogger(EmployeeDaoJdbcImpl.class);
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	@Autowired
	DBConnUtil dbUtil;
	String val;

	@Override
	public boolean create(Employee emp) throws EmployeeException {
		// Insertion with Prepared Statement
		logger.info("DAO-Start method of create method");
		boolean flag = false;
		String insertQueryForPrepareStmt = "INSERT INTO employee_mvc (name, age,gender,contractor,skills,designation,department,address,country,doj,createdTime,modifiedTime) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?)";
		try {
			try {
				conn = dbUtil.getDBCOnnection();
			} catch (ClassNotFoundException | IOException e) {

				// e.printStackTrace();
				logger.error("db connection error", e);
			}
			System.out.println("Connect done in create:" + conn);
			pstmt = conn.prepareStatement(insertQueryForPrepareStmt);
			pstmt.setString(1, emp.getName());
			pstmt.setInt(2, emp.getAge());
			pstmt.setString(3, emp.getGender());
			pstmt.setBoolean(4, emp.isContractor());
			if (emp.getSkills().size() > 1) {
				val = String.join(", ", emp.getSkills());
			} else {
				val = emp.getSkills().toString();
			}
			if (val != null) {
				val = val.replaceAll("\\[", "").replaceAll("\\]", "");
			}
			pstmt.setString(5, val);
			pstmt.setString(6, emp.getDesignation());
			pstmt.setString(7, emp.getDepartment());
			pstmt.setString(8, emp.getAddress());
			pstmt.setString(9, emp.getCountry());
			pstmt.setDate(10, Date.valueOf(emp.getDoj().toString()));
			pstmt.setTimestamp(11, java.sql.Timestamp.valueOf(LocalDateTime.now()));
			pstmt.setTimestamp(12, java.sql.Timestamp.valueOf(LocalDateTime.now()));
			int insertCount = pstmt.executeUpdate();
			logger.info(insertCount + " Employee(s) inserted");

			flag = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			logger.error("creation employee error", e);
			throw new EmployeeException("Unable to create employee");
		} finally {
			if (conn != null && pstmt != null) {
				try {
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					logger.error("unable to close the connection", e);
					throw new EmployeeException("Unable to close the connection while creating records");
				}
			}
		}
		logger.info("DAO-end of method  create ");
		return flag;

	}

	@Override
	public List<Employee> viewAllEmployee() throws EmployeeNotFoundException {
		logger.info("DAO-Start method of viewAllEmployee() method");
		Employee emp = null;
		List<String> list;
		List<Employee> empList = new ArrayList<Employee>();
		String selectQuery = "SELECT id,name,age,gender,contractor,skills,designation,department,address,country,doj,createdTime,modifiedTime FROM employee_mvc";
		try {
			try {
				conn = dbUtil.getDBCOnnection();
			} catch (ClassNotFoundException | IOException e) {
				logger.error("connection error", e);
				// e.printStackTrace();

			}
			stmt = conn.createStatement();
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
				String gender = rs.getString("gender");
				boolean contractor = rs.getBoolean("contractor");
				String skills = rs.getString("skills");

				String designation = rs.getString("designation");
				String department = rs.getString("department");
				String address = rs.getString("address");
				String country = rs.getString("country");
				Date doj = rs.getDate("doj");
				Timestamp createdTime = rs.getTimestamp("createdTime");
				Timestamp modifiedTime = rs.getTimestamp("modifiedTime");
				if (skills.contains(",")) {
					list = new ArrayList<String>(Arrays.asList(skills.split(" , ")));
				} else {
					list = new ArrayList<String>(Arrays.asList(skills));
				}

				emp = new Employee(name, age, gender, contractor, list, department, designation, address, country,
						doj.toLocalDate(), createdTime.toLocalDateTime(), modifiedTime.toLocalDateTime());
				emp.setId(id);

				empList.add(emp);
				
			}

		} catch (SQLException e) {
			// e.printStackTrace();
			logger.error("viewall employee error", e);
			throw new EmployeeNotFoundException("Unable to retrive the employee records");

		} finally {
			if (conn != null && stmt != null) {
				try {
					rs.close();
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					// e.printStackTrace();
					logger.error("Database close connection error", e);
					throw new EmployeeNotFoundException("Unable to close the database connection");

				}
			}

		}
		logger.info("DAO-end of viewAllEmployee() method");
		return empList;

	}

	public boolean update(Employee emp) throws EmployeeNotFoundException {
		// Updation with Prepared Statement
		boolean flag = false;
		logger.info("DAO-Start method of update method");
		String updateQuery = "UPDATE employee_mvc SET name = ?, age = ?, gender = ?, contractor = ?, skills = ?, designation = ?, department = ?, address = ?, country = ?,  "
				+ " modifiedTime = ?  " + " WHERE id = ?";
		try {
			try {
				conn = dbUtil.getDBCOnnection();
			} catch (ClassNotFoundException | IOException e) {
				// e.printStackTrace();
				logger.error("connection error", e);
			}

			pstmt = conn.prepareStatement(updateQuery);

			pstmt.setString(1, emp.getName());
			pstmt.setInt(2, emp.getAge());
			pstmt.setString(3, emp.getGender());
			pstmt.setBoolean(4, emp.isContractor());
			if (emp.getSkills().size() > 1) {
				val = String.join(", ", emp.getSkills());
			} else {
				val = emp.getSkills().toString();
			}
			if (val != null) {
				val = val.replaceAll("\\[", "").replaceAll("\\]", "");
			}
			pstmt.setString(5, val);
			pstmt.setString(6, emp.getDesignation());
			pstmt.setString(7, emp.getDepartment());
			pstmt.setString(8, emp.getAddress());
			pstmt.setString(9, emp.getCountry());
			pstmt.setTimestamp(10, java.sql.Timestamp.valueOf(LocalDateTime.now()));
			pstmt.setInt(11, emp.getId());

			int updateCount = pstmt.executeUpdate();

			flag = true;

			System.out.println(updateCount + " Employee(s) updated");
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("upadte employee error", e);
			throw new EmployeeNotFoundException("Unable to update the employee");

		} finally {
			if (conn != null && pstmt != null) {
				try {
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					logger.error("Database close connection error", e);
					throw new EmployeeNotFoundException("Unable to update and close the database connection");
				}
			}
		}
		logger.info("DAO-Start method of update method");
		return flag;
	}

	@Override
	public boolean delete(int emp_id) throws EmployeeNotFoundException {
		// Deletion with Prepared Statement
		boolean flag = false;

		logger.info("DAO-Start method of delete method");
		String deleteQuery = "DELETE FROM employee_mvc WHERE id = ?";
		try {
			try {
				conn = dbUtil.getDBCOnnection();
			} catch (ClassNotFoundException | IOException e) {
				logger.error("connection error", e);
				// e.printStackTrace();
			}
			pstmt = conn.prepareStatement(deleteQuery);
			pstmt.setInt(1, emp_id);
			int deleteCount = pstmt.executeUpdate();

			// persist the changes
			// conn.commit();
			flag = true;

			System.out.println(deleteCount + " Employee(s) deleted");
		} catch (SQLException e) {

			// e.printStackTrace();
			logger.error("delete employee error", e);
			throw new EmployeeNotFoundException("Unable to delete employee");
		} finally {
			if (conn != null && pstmt != null) {
				try {
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error("connection close error", e);
					throw new EmployeeNotFoundException("Unable to close the connection while deletion");
				}
			}
		}
		logger.info("DAO-end of delete employee method");
		return flag;
	}

	@Override
	public Employee viewEmployeeById(int emp_id) throws EmployeeNotFoundException {
		Employee emp = null;
		logger.info("DAO-Start method of viewEmployeeById method");
		String selectQuery = "SELECT id,age,name,gender,contractor,skills,designation,department,address,country,doj,createdTime,modifiedTime "
				+ " FROM employee_mvc where id = ?";

		try {

			try {
				conn = dbUtil.getDBCOnnection();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
				logger.error("connection establishing error", e);
			}

			pstmt = conn.prepareStatement(selectQuery);
			pstmt.setInt(1, emp_id);
			rs = pstmt.executeQuery();

			// Header
			// System.out.format("\t%s \t%s \t%s \t%s \t%s \t%s\n", "Id", "Age", "Name",
			// "Designation", "Department",
			// "Country");
			List<String> list;
			while (rs.next()) {
				// Retrieve by column name
				int id = rs.getInt("id");
				int age = rs.getInt("age");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				boolean contractor = rs.getBoolean("contractor");
				String skills = rs.getString("skills");

				String designation = rs.getString("designation");
				String department = rs.getString("department");
				String address = rs.getString("address");
				String country = rs.getString("country");
				Date doj = rs.getDate("doj");
				Timestamp createdTime = rs.getTimestamp("createdTime");
				Timestamp modifiedTime = rs.getTimestamp("modifiedTime");
				if (skills.contains(",")) {
					list = new ArrayList<String>(Arrays.asList(skills.split(" , ")));
				} else {
					list = new ArrayList<String>(Arrays.asList(skills));
				}

				emp = new Employee(name, age, gender, contractor, list, department, designation, address, country,
						doj.toLocalDate(), createdTime.toLocalDateTime(), modifiedTime.toLocalDateTime());
				emp.setId(id);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("viewbyemployeeid employee error", e);
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
					logger.error("connection closing error", e);
					throw new EmployeeNotFoundException("Unable to view the close the database connection");
				}
			}

		}
		logger.info("DAO-end of viewEmployeeById method");
		return emp;

	}

	// get last employee inserted record
	@SuppressWarnings("finally")
	public int getLastEmpInsId() throws EmployeeNotFoundException {

		logger.info("DAO-Start method of getLastEmpInsId method");
		String selectQuery = "SELECT id from employee_mvc";
		int id = -1;

		try {

			conn = dbUtil.getDBCOnnection();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(selectQuery);
			rs.last();
			id = rs.getInt(1);

		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
			logger.error("unable to get lastinserted record", e);
			throw new EmployeeNotFoundException("Unable to get last inserted record");
		} finally {
			if (conn != null && stmt != null) {
				try {
					rs.close();
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error("connection closing error", e);
					throw new EmployeeNotFoundException("Unable to view the close the database connection");
				}
			}
			logger.info("DAO-end of getLastEmpInsId method");
			return id;

		}

	}

}
