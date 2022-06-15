package com.lowes.empapp.model;

public class Employee {

	private int emp_id;
	private String name;
	private String address;
	private int salary;
	private String department;
	private String email;

	

	public Employee(int emp_id, String name, String department, String email,
			String address, int salary) {
		this.emp_id = emp_id;
		this.name = name;
		this.department = department;
		this.address = address;
		this.salary = salary;
		this.email = email;
	}

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public int getemp_id() {
		return emp_id;
	}

	public void setemp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// Overriding toString() method
	@Override
	public String toString() {
		return "Employee [emp_id = " + emp_id + ", salary = " + salary + ", name = " + name + ", address = " + address
				+ ", department = " + department + ", email = " + email + "]";
	}

}
