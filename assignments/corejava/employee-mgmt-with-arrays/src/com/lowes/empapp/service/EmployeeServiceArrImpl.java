package com.lowes.empapp.service;

import java.util.Arrays;

import com.lowes.empapp.model.Employee;

//this is service class implementation
public class EmployeeServiceArrImpl implements EmployeeService {
	private Employee[] employees;

	private int index;

	// get the employee size
	public void EmployeeSize(int size) {
		employees = new Employee[size];
	}

	public EmployeeServiceArrImpl() {
		// TODO Auto-generated constructor stub
	}

	// create the employee record
	@Override
	public boolean create(Employee emp) {
		boolean flag = false;
		employees[index++] = emp;
		System.out.println("Employee record created: " + Arrays.toString(employees));
		flag = true;
		return flag;
	}

	// view the employee details by id
	@Override
	public Employee viewEmployeeById(int emp_id) {
		Employee employee = null;
		for (int i = 0; i < employees.length; i++) {
			if (employees[i] != null && employees[i].getemp_id() == emp_id) {
				employee = employees[i];
			}
		}
		return employee;
	}

	@Override
	public Employee[] viewAllEmployee() {
		Employee anotherArray[] = new Employee[employees.length];
		// System.out.println("Array operation: " + Arrays.toString(employees));
		for (int i = 0; i < employees.length; i++) {
			if (employees[i] != null) {
				anotherArray[i] = employees[i];
				
			}
			
		}
		return anotherArray;
	}

	// update the employee
	@Override
	public boolean update(Employee emp) {
		boolean flag = false;
		for (int i = 0; i < employees.length; i++) {
			System.out.println(employees[i].getemp_id() == emp.getemp_id());
			if (employees[i] != null && employees[i].getemp_id() == emp.getemp_id()) {
				employees[i].setAddress(emp.getAddress());
				employees[i].setDepartment(emp.getDepartment());
				employees[i].setName(emp.getName());
				employees[i].setSalary(emp.getSalary());
				employees[i].setEmail(emp.getEmail());
				flag = true;
			}
		}
		System.out.println("Employee record updated: " + Arrays.toString(employees));
		return flag;
	}

	// delete the employee
	@Override
	public boolean delete(int emp_id) {
		System.out.println("Before operation: " + Arrays.toString(employees));
		int i, j, count = 0;
		boolean flag = false;
		int n = employees.length;

		for (i = 0; i < n; i++) {
			if (employees[i].getemp_id() == emp_id) {
				for (j = i; j < (employees.length - 1); j++)
					employees[j] = employees[j + 1];
				n--;
				count++;
				flag = true;
			}

		}
		if (count == 0)
			System.out.println("\nElement not found!");
		else if (count == 1) {
			System.out.println("\nRemoved the element successfully!");
			System.out.println("\nThe new array is: ");
			for (i = 0; i < n; i++)
				System.out.print(employees[i] + " ");
		} else {
			System.out.println("\nRemoved all " + i + " from the array.");
			System.out.println("\nThe new array is: ");
			for (i = 0; i < n; i++)
				System.out.print(employees[i] + " ");
		}
		return flag;
	}

}
