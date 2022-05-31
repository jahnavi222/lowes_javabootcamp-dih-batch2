package com.lowes.empapp.main;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.lowes.empapp.exception.EmployeeException;
import com.lowes.empapp.exception.EmployeeNotFoundException;
import com.lowes.empapp.model.Employee;
import com.lowes.empapp.service.EmployeeService;
import com.lowes.empapp.service.EmployeeServiceColImpl;

public class EmployeeMain {
	// menu options
	public static void ShowMenuOptions() {
		System.out.println("1.Add Employee");
		System.out.println("2.viewAllEmployees");
		System.out.println("3.ViewEmployeeByID");
		System.out.println("4.DeleteEmployee");
		System.out.println("5.UpdateEmployee");
		System.out.println("6.ImportFile");
		System.out.println("7.ExportFile");
		System.out.println("8.EmployeeStas");
		System.out.println("9.Exit");
		System.out.print("Enter Your Choice (1-9): ");

	}

	static EmployeeService empImpl = new EmployeeServiceColImpl();
	static Employee emp = null;
	static LocalDateTime nowTime = LocalDateTime.now();

	// capture the employee related information from console
	public static Employee addEmployee(Scanner sc, int size) {
		System.out.println("Enter Employee details:");

		for (int i = 0; i < size; i++) {
			emp = new Employee();
			System.out.println((i + 1) + " Enter" + " Employee id:");
			int empid = sc.nextInt();

			System.out.println((i + 1) + "  Enter" + " Employee name:");
			String name = sc.next();

			System.out.println((i + 1) + " Enter" + " Employee age:");
			int age = sc.nextInt();

			System.out.println((i + 1) + "  Enter" + " Employee department:");
			String department = sc.next();

			System.out.println((i + 1) + " Enter " + " Employee designation:");
			String designation = sc.next();

			System.out.println((i + 1) + "  Enter " + " Employee country:");
			String country = sc.next();

			System.out.println((i + 1) + " Enter " + " Employee date of joining in date format" + " yyyy-mm-dd");
			String dojStr = sc.next();
			
			emp = new Employee(); emp.setAge(age); emp.setDesignation(dojStr);
			
			boolean valid = empImpl.validate(emp,"Invalid Input.",
					 (e -> e.getAge() > 21 && e.getAge() < 60),
             (str -> isValidDate(dojStr)));
			
			if(!valid) { 
				System.out.println("Please re-enter again"); break;
				}
			
			LocalDate doj = LocalDate.parse(dojStr);

			emp = new Employee(empid, name, age, department, designation, country, doj, nowTime, nowTime);

			/*
			 * valid = empImpl.validate(emp,"Invalid Designation",(e -> e.getAge() > 21 &&
			 * e.getAge() < 60), (str -> !str.toUpperCase().equalsIgnoreCase("ARCHITECT")));
			 * 
			 * valid = empImpl.validate(emp,"Invalid Designation",(e -> e.getAge() > 21 &&
			 * e.getAge() < 60), (str -> !str.toUpperCase().equalsIgnoreCase("ENGINEER")));
			 * 
			 * if(!valid && (department.equalsIgnoreCase("ACCOUNTING") ||
			 * department.equalsIgnoreCase("MARKETING") )) { System.out.
			 * println("Input parameters not valid because of Invalid combination of Department & Designation. Please Re-enter the inputs correctly again..!"
			 * ); }
			 */
			 
			empImpl.create(emp);

		}
		return emp;
	}
	
	private static boolean isValidDate(String inputDate) {
		boolean valid = true;
		
		final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		try {
			if(inputDate == null) {
				valid = false;
			}
			
			LocalDate dt = LocalDate.parse(inputDate, dtf);
		} catch(Exception e) {
			valid = false;
		}
		
		return valid;
	}

	// capture the employee related information from console
	public static Employee updateEmployee(Scanner sc) {
		emp = new Employee();
		System.out.println("Enter Employee details to update:");
		System.out.println("Enter Employee id:");
		int empid = sc.nextInt();
		emp.setEmpId(empid);
		System.out.println("Enter  Employee name:");
		String name = sc.next();
		emp.setName(name);
		System.out.println("Enter  Employee age:");
		int age = sc.nextInt();
		emp.setAge(age);
		System.out.println("Enter  Employee department:");
		String department = sc.next();
		emp.setDepartment(department);
		System.out.println("Enter  Employee designation:");
		String designation = sc.next();
		emp.setDesignation(designation);
		System.out.println("Enter  Employee country:");
		String country = sc.next();
		emp.setCountry(country);
		emp.setModifiedTime(LocalDateTime.now());

		return emp;
	}

	// print employee details
	public static void printEmployees(List<Employee> asList, Employee emp1) {
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%5s %10s %10s %8s %20s %17s %8s %15s %15s", "EMPID", "NAME", "AGE", "DEPARTMENT",
				"DESIGNATION", "COUNTRY", "DOJ", "CREATED TIME", "MODIFIED TIME");
		System.out.println();
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------------------------------");
		if (asList != null)
			for (Employee emp2 : asList) {
				{
					System.out.format("%5s %10s %10s %8s %20s %17s %8s %15s %15s", emp2.getEmpId(), emp2.getName(),
							emp2.getAge(), emp2.getDepartment(), emp2.getDesignation(), emp2.getCountry(),
							emp2.getDoj(), emp2.getCreatedTime(), emp2.getModifiedTime());
					System.out.println();
				}
			}
		else {
			System.out.format("%5s %10s %10s %8s %20s %17s %8s %15s %15s", emp1.getEmpId(), emp1.getName(),
					emp1.getAge(), emp1.getDepartment(), emp1.getDesignation(), emp1.getCountry(), emp1.getDoj(),
					emp1.getCreatedTime(), emp1.getModifiedTime());
			System.out.println();
		}
		System.out.println(
				"-----------------------------------------------------------------------------------------------------------------------------------------------");
	}

	// get employee stastics details
	public static void getEmployeeStas(EmployeeService empImpl) {

		long count = empImpl.getEmployeeCountAgeGreaterThan((Employee e) -> e.getAge() > 30);
		System.out.println(" get employee age Count > 30 : = " + count);

		List<Integer> empIdList = empImpl.getEmployeesIdsAgeGreaterThan(30);
		System.out.println("get employee ids whose age  > 30 List := " + empIdList);

		Map<String, Double> empDepCount = empImpl.getAvgEmployeeAgeByDept();
		System.out.println("get employee age by department :" + empDepCount);

		List<String> empDepEmpList = empImpl.getDepartmentsHaveEmployeesMoreThan(2);
		System.out.println("get employees department having more than :" + empDepEmpList);

		List<String> empNamePrefixList = empImpl.getEmployeeNamesStartsWith("A");
		System.out.println("get employees name startswith prefix :" + empNamePrefixList);

		Map<String, Double> empSvcAvg = empImpl.getAvgEmployeeServiceByDept();
		System.out.println("get employees avg service by department :" + empSvcAvg);

		Map<String, Long> empDepCountbyAge = empImpl.getEmployeeCountByDepartment();
		System.out.println("get employees count by department :" + empDepCountbyAge);

		Map<String, Long> empDepCountDepByOrder = empImpl.getEmployeeCountByDepartmentOrdered();

		System.out.println("get employees count by department orderwise :" + empDepCountDepByOrder);
	}

	public static void main(String[] args) throws EmployeeException, EmployeeNotFoundException {
		Scanner sc1 = new Scanner(System.in);

		while (true) {
			ShowMenuOptions();
			int choice;
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

					// empImpl.EmployeeSize(size1);

					try {

						addEmployee(sc1, size1);
						System.out.println("Employee created sucessfully");

					} catch (Exception e) {
						System.out.println(e.getStackTrace());
						throw new EmployeeException("Unable to create employee");
					}

					break;

				case 2:
					// view all employee details
					try {
						List<Employee> asList = empImpl.viewAllEmployee();

						if (asList != null) {
							printEmployees(asList, emp);
						}
					} catch (Exception e) {
						System.out.println(e.getMessage());
						throw new EmployeeNotFoundException("Employee is not found");

					}
					break;
				case 3:
					// view all employee by empid
					System.out.println("Enter the employee id:");
					List<Employee> asList = null;
					Employee emp1 = empImpl.viewEmployeeById(sc1.nextInt());

					printEmployees(asList, emp1);
					if (emp1 != null) {
						printEmployees(asList, emp1);
					} else {
						throw new EmployeeNotFoundException("Employee is not found");
					}

					break;
				case 4:

					// delete employee
					try {

						System.out.println("Enter the employee id:");

						empImpl.delete(sc1.nextInt());
						System.out.println("Employee deleted sucessfully");
						// }else {
						// System.out.println("Employees deletion failed");
						// }
					} catch (Exception e) {
						e.printStackTrace();
						throw new EmployeeException("Unable to delete employee");

					}
					break;

				case 5:
					// update employee
					try {

						Employee emprec = updateEmployee(sc1);
						empImpl.update(emprec);
						System.out.println("Employee record updated sucessfully");

					} catch (Exception e) {
						System.out.println(e.getStackTrace());
						System.out.println(e.initCause(e));
						throw new EmployeeNotFoundException("unable to update employee");

					}
					break;
				case 6:
					try {
						System.out.println("\n Bulk Import from the file");
						empImpl.bulkImport();
					} catch (Exception e) {
						e.printStackTrace();
						// System.out.println(e.getStackTrace());
					}
					break;
				case 7:
					try {
						System.out.println("\n Bulk Export from the file");
						empImpl.bulkExport();
					} catch (Exception e) {
						System.out.println(e.getStackTrace());
					}
					break;
				case 8:
					try {
						getEmployeeStas(empImpl);
					} catch (Exception e) {
					}
					break;
				case 9:
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
