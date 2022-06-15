package com.lowes.empapp.main;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.lowes.empapp.exception.EmployeeException;
import com.lowes.empapp.exception.EmployeeNotFoundException;
import com.lowes.empapp.model.Employee;

import com.lowes.empapp.service.EmployeeServiceArrImpl;

public class EmployeeMain {
	// menu options
	public static void ShowMenuOptions() {
		System.out.println("1.Add Employee");
		System.out.println("2.viewAllEmployees");
		System.out.println("3.ViewEmployeeByID");
		System.out.println("4.DeleteEmployee");
		System.out.println("5.UpdateEmployee");
		System.out.println("6.Exit");
		System.out.print("Enter Your Choice (1-6): ");

	}

	static EmployeeServiceArrImpl empImpl = new EmployeeServiceArrImpl();
	static Employee emp = null;

	// capture the employee related information from console
	public static Employee addEmployee(Scanner sc, int size) {
		System.out.println("Enter Employee details:");

		for (int i = 0; i < size; i++) {
			emp = new Employee();
			System.out.println("Enter " + (i + 1) + " Employee id:");
			int empid = sc.nextInt();
			emp.setemp_id(empid);
			System.out.println("Enter " + (i + 1) + " Employee name:");
			String name = sc.next();
			emp.setName(name);
			System.out.println("Enter " + (i + 1) + " Employee address:");
			String address = sc.next();
			emp.setAddress(address);
			System.out.println("Enter " + (i + 1) + " Employee department:");
			String department = sc.next();
			emp.setDepartment(department);
			System.out.println("Enter " + (i + 1) + " Employee email:");
			String email = sc.next();
			emp.setEmail(email);
			System.out.println("Enter " + (i + 1) + " Employee Salary:");
			int salary = sc.nextInt();
			emp.setSalary(salary);
			empImpl.create(emp);
		}
		return emp;
	}

	// capture the employee related information from console
	public static Employee updateEmployee(Scanner sc) {
		System.out.println("Enter Employee details to update:");
		System.out.println("Enter Employee id:");
		int empid = sc.nextInt();
		emp.setemp_id(empid);
		System.out.println("Enter  Employee name:");
		String name = sc.next();
		emp.setName(name);
		System.out.println("Enter  Employee address:");
		String address = sc.next();
		emp.setAddress(address);
		System.out.println("Enter  Employee department:");
		String department = sc.next();
		emp.setDepartment(department);
		System.out.println("Enter Employee email:");
		String email = sc.next();
		emp.setEmail(email);
		System.out.println("Enter  Employee Salary:");
		int salary = sc.nextInt();
		emp.setSalary(salary);

		return emp;
	}

	public static void main(String[] args) throws EmployeeException, EmployeeNotFoundException {
		Scanner sc1 = new Scanner(System.in);

		while (true) {
			ShowMenuOptions();
			int choice = 0;
			int size1 = 0;
			try {
				choice = sc1.nextInt();
				switch (choice) {
				// add employee
				case 1:
					System.out.println("Enter the number of Employees");
					if (sc1.hasNextInt()) {
						size1 = sc1.nextInt();
					}

					empImpl.EmployeeSize(size1);
					try {
						addEmployee(sc1, size1);
					} catch (Exception e) {
						System.out.println(e.getStackTrace());
						throw new EmployeeException("Unable to create employee");
					}

					break;

				case 2:
					// view all employee details
					Employee[] dispall = null;
					try {
						dispall = empImpl.viewAllEmployee();
						for (int i = 0; i < dispall.length; i++) {
							if (emp != null) {
								System.out.println("empid: " + emp.getemp_id() + "\t" + "empaddr : " + emp.getAddress()
										+ "\t" + "empname: " + emp.getName() + "\t" + "empdepartment: "
										+ emp.getDepartment() + "\t" + "empemail: " + emp.getEmail() + "\t" + "empsal: "
										+ emp.getSalary());
							}

						}

					} catch (Exception e) {
						System.out.println(e.getStackTrace());
						throw new EmployeeNotFoundException("Employee is not found");

					}
					break;
				case 3:
					// view all employee by empid
					System.out.println("Enter the employee id:");
					Employee emp1 = empImpl.viewEmployeeById(sc1.nextInt());
					if (emp1 != null) {
						System.out.println("empid: " + emp1.getemp_id() + "\t" + "empaddr : " + emp1.getAddress() + "\t"
								+ "empname: " + emp1.getName() + "\t" + "empdepartment: " + emp1.getDepartment() + "\t"
								+ "empemail: " + emp1.getEmail() + "\t" + "empsal: " + emp1.getSalary());
					} else {
						throw new EmployeeNotFoundException("Employee is not found");
					}

					break;
				case 4:

					// delete employee
					try {
						System.out.println("Enter the employee id:");
						empImpl.delete(sc1.nextInt());
					} catch (Exception e) {
						System.out.println(e.getMessage());
						throw new EmployeeException("Unable to delete employee");

					}
					break;

				case 5:
					// update employee
					try {
						Employee emprec = updateEmployee(sc1);
						empImpl.update(emprec);

					} catch (Exception e) {
						System.out.println(e.getMessage());
						throw new EmployeeNotFoundException("unable to update employee");

					}
					break;
				case 6:
					System.out.println("\n Thanks you for using the employee management App");

					return;
				default:
					System.out.println("\nInvalid choice!");
					break;

				}

			} catch (InputMismatchException | ArrayIndexOutOfBoundsException ex) {
				System.out.println("Exception Occured " + " " + ex);

			} /*
				 * finally { //if (sc1!= null ) // sc1.close(); }
				 */
		}

	}

}
