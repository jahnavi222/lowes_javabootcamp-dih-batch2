package com.lowes.empapp.service;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.lowes.empapp.model.Employee;

//this is service class implementation
public class EmployeeServiceColImpl implements EmployeeService, Comparator<Employee> {
	public static final String IMPORT_FILE_NAME = "C:/Training/java-data/BulkImportFile-1.txt";
	Map<Integer, Employee> employeeMap = new HashMap<Integer, Employee>();
	Employee emp = null;
	LocalDateTime nowTime = LocalDateTime.now();

	// Employee emp4=null;
	public EmployeeServiceColImpl() {
		// employeeMap = new HashMap<Employee, Integer>();
		employeeMap.put(101, new Employee(101, "John", 35, "Dept 1", "Desig 1", "India", LocalDate.parse("2021-04-10"),
				nowTime.minusYears(3).minusMonths(1), nowTime.minusYears(1)));
		employeeMap.put(102, new Employee(102, "Adam", 25, "Dept 2", "Desig 2", "USA", LocalDate.parse("2020-03-10"),
				nowTime.minusYears(3).minusMonths(2), nowTime.minusYears(3)));
		employeeMap.put(103, new Employee(103, "Bob", 30, "Dept 3", "Desig 3", "India", LocalDate.parse("2019-02-10"),
				nowTime.minusYears(3).minusMonths(3), nowTime.minusYears(3)));
		employeeMap.put(104, new Employee(104, "Anna", 35, "Dept 4", "Desig 4", "France", LocalDate.parse("2018-01-10"),
				nowTime.minusYears(4).minusMonths(4), nowTime.minusYears(4)));
		employeeMap.put(105, new Employee(105, "Alice", 35, "Dept 3", "Desig 5", "UK", LocalDate.parse("2021-04-10"),
				nowTime.minusYears(1).minusMonths(1), nowTime.minusYears(1)));
		employeeMap.put(106, new Employee(106, "Greg", 25, "Dept 3", "Desig 6", "USA", LocalDate.parse("2020-03-10"),
				nowTime.minusYears(2).minusMonths(2), nowTime.minusYears(2)));
		employeeMap.put(107, new Employee(107, "Employee 7", 30, "Dept 4", "Desig 7", "India",
				LocalDate.parse("2019-02-10"), nowTime.minusYears(3).minusMonths(3), nowTime.minusYears(3)));
		employeeMap.put(108, new Employee(108, "Employee 8", 38, "Dept 4", "Desig 8", "France",
				LocalDate.parse("2018-01-10"), nowTime.minusYears(4).minusMonths(4), nowTime.minusYears(4)));

	}

	// create the employee record
	@Override
	public boolean create(Employee emp) {

		employeeMap.put(employeeMap.size() + 1, emp);
		System.out.println(Arrays.toString(employeeMap.entrySet().toArray()));
		return true;
	}

	// view the employee details by id
	@Override
	public Employee viewEmployeeById(int emp_id) {
		emp = new Employee();
		if (employeeMap != null) {
			for (Employee emp1 : employeeMap.values()) {
				// System.out.println(emp1);
				// System.out.println(employeeMap.get(emp1));
				if (emp1.getEmpId() == emp_id) {
					emp = emp1;
				}

			}

		}
		return emp;
	}

	//// view all employee details
	@Override
	public List<Employee> viewAllEmployee() {
		List<Employee> empList = new ArrayList<Employee>();
		if (employeeMap != null) {
			for (Employee emp : employeeMap.values()) {
				empList.add(emp);
			}
		}
		return empList;

	}

	// update the employee
	@Override
	public boolean update(Employee emp) {
		 
		boolean flag = false;
		if (employeeMap != null) {
			for (Employee empMap : employeeMap.values()) {
				//System.out.println(empMap.getEmpId() == emp.getEmpId());
				if (empMap != null && empMap.getEmpId() == emp.getEmpId()) {
					empMap.setEmpId(empMap.getEmpId());
					empMap.setAge(emp.getAge());
					empMap.setName(emp.getName());
					empMap.setDepartment(emp.getDepartment());
					empMap.setDesignation(emp.getDesignation());
					empMap.setCountry(emp.getCountry());
/*					empMap.setDoj(emp.getDoj());
					empMap.setCreatedTime(emp.getCreatedTime());*/
					empMap.setModifiedTime(nowTime);
					Employee val = employeeMap.get(emp.getEmpId());
					employeeMap.put(empMap.getEmpId(), val);
					flag = true;
				}
				// System.out.println(empMap);
				// System.out.println(employeeMap.get(empMap.getEmpId()));
				//System.out.println(employeeMap);
			}

		}

		return flag;
	}

	// delete the employee
	@Override
	public boolean delete(int emp_id) {
		boolean flag = false;
		Employee emp = null;
		
		if (employeeMap != null) {
			for (Employee emp2 : employeeMap.values()) {
				//System.out.println(emp2.getEmpId() == emp_id);
				if (emp2 != null && emp2.getEmpId() == emp_id) {
					flag = true;
					emp = emp2;
					break;
				}
			}
			if(flag) {
				employeeMap.remove(emp.getEmpId());

				//System.out.println(employeeMap);
			}
		}

		return flag;

	}

	@Override // sort the employee based on empid
	public int compare(Employee o1, Employee o2) {

		return o1.getEmpId() - o2.getEmpId();
	}
	
	

	// get count of employee age greater than condition
	public List<Integer> getEmployeesIdsAgeGreaterThan(int age) {
		List<Integer> empIdList = null;

		empIdList = employeeMap.values().stream().filter(e -> e.getAge() > age).map(Employee::getEmpId)
				.collect(Collectors.toList());

		return empIdList;
	}

	// get count of employee age greater than condition
	public long getEmployeeCountAgeGreaterThan(Predicate<Employee> condition) {

		long count = employeeMap.entrySet().stream().filter(e -> condition.test(e.getValue())).count();

		return count;
	}

	// get employee age greater than condition
	public Map<String, Long> getEmployeeCountByDepartment() {

		Map<String, Long> empDepCount = null;

		empDepCount = employeeMap.values().stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));

		return empDepCount;
	}

	// get employee count by department
	public Map<String, Long> getEmployeeCountByDepartmentOrdered() {

		Map<String, Long> empDepCount = null;

		empDepCount = employeeMap.values().stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, TreeMap::new, Collectors.counting()));

		return empDepCount;
	}

	// get employee age count by department by orderd
	public Map<String, Double> getAvgEmployeeAgeByDept() {

		Map<String, Double> empDepCount = null;

		empDepCount = employeeMap.values().stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getAge)));

		return empDepCount;
	}
  //get avg service of employee by department
	public Map<String, Double> getAvgEmployeeServiceByDept() {

		Map<String, Double> empDepCount = null;

		System.out.println("Today's Local Date: " + LocalDate.now());

		empDepCount = employeeMap.values().stream().collect(Collectors.groupingBy(Employee::getDepartment,
				Collectors.averagingDouble(e -> Period.between(e.getDoj(), LocalDate.now()).getYears())));

		return empDepCount;
	}

	// get number of employeess having more in department
	public List<String> getDepartmentsHaveEmployeesMoreThan(int criteria) {

		List<String> deptEmpList = null;

		deptEmpList = employeeMap.values().stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting())).entrySet().stream()
				.filter(e -> e.getValue() > criteria).map(Map.Entry::getKey).collect(Collectors.toList());

		return deptEmpList;
	}

	// getemployeess name start with prefix
	public List<String> getEmployeeNamesStartsWith(String prefix) {

		List<String> empNamePrefixList = null;

		empNamePrefixList = employeeMap.values().stream().filter(e -> e.getName().startsWith(prefix))
				.map(Employee::getName).collect(Collectors.toList());

		return empNamePrefixList;
	}

	// bulkimport for importing employee details via file
	public void bulkImport() {

		Callable<List<Employee>> callable = new ImportCallable();
		ExecutorService e = Executors.newFixedThreadPool(1);

		Future<List<Employee>> f = e.submit(callable);

		List<Employee> empList = null;

		while (!f.isDone()) {
			System.out.println(Thread.currentThread().getName() + " --> Waiting for file reading to complete..");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}

		try {
			empList = f.get();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			e1.printStackTrace();
		}

		e.shutdown();

		System.out.println(
				Thread.currentThread().getName() + " --> Successfully finished Importing from file in the thread..");

		if (employeeMap == null) {
			employeeMap = new HashMap<>();
		}

		if (empList != null) {
			for (Employee emp : empList) {
				employeeMap.put(emp.getEmpId(), emp);
			}
		}

		System.out.println(employeeMap);
	}

	// export the data from hashmap to file
	public void bulkExport() {
		List<Employee> employeeList = null;
		String fileName = null;

		if (employeeMap != null) {
			employeeList = new ArrayList<>(employeeMap.values());

			Callable<String> callable = new ExportCallable(employeeList);
			ExecutorService e = Executors.newFixedThreadPool(1);

			Future<String> f = e.submit(callable);

			while (!f.isDone()) {
				System.out.println(Thread.currentThread().getName() + " --> Waiting for file writing to complete..");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}

			try {
				fileName = f.get();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			} catch (ExecutionException e1) {
				e1.printStackTrace();
			}

			e.shutdown();

			System.out.println(
					Thread.currentThread().getName() + " --> Successfully finished Exporting to file -> " + fileName);

		}
	}

	@Override
	public boolean validate(Employee emp, String msg, Predicate<Employee> condition,
			Function<String, Boolean> operation) {
	    
		boolean valid = condition.test(emp);

        if(valid) {
            valid = operation.apply(emp.getDesignation());
        } else {
            System.out.println("Validation Failed!! "+msg);
        }

        return valid;
	}

	public static void handleError(String errorMessage, Consumer<String> consumer) {
		consumer.accept(errorMessage);
	}

	// read lines from the file
	private List<String> readLinesFromFile(String filePathName) {

		Path filePath = Paths.get(filePathName);
		Stream<String> fileLines = null;

		try {
			fileLines = (Files.newBufferedReader(filePath)).lines();
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<String> csvLines = fileLines.collect(Collectors.toList());

		return csvLines;
	}

	// class to implement import file from thread
	class ImportCallable implements Callable<List<Employee>> {
		public static final String IMPORT_FILE_NAME = "C:/Training/java-data/BulkImportFile-1.txt";

		@Override
		public List<Employee> call() throws Exception {
			System.out.println(Thread.currentThread().getName() + " --> Running thread for processing bulkImport..");
			List<String> csvLines = null;

			try {
				System.out.println(
						Thread.currentThread().getName() + " --> Starting to read from file.. -> " + IMPORT_FILE_NAME);
				csvLines = readLinesFromFile(IMPORT_FILE_NAME);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " --> Finished reading from file..");

			String[] strTok = null;
			Employee emp = null;

			List<Employee> employeesList = new ArrayList<>();

			for (String line : csvLines) {
				strTok = line.split(",");
				emp = getEmployeeFromTokens(strTok);
				employeesList.add(emp);
			}

			System.out.println(
					Thread.currentThread().getName() + " --> Finished creating list of Employee objects from thread..");

			return employeesList;
		}

		// get employee details from the file
		private Employee getEmployeeFromTokens(String[] strTok) {

			Employee emp = new Employee();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

			emp.setEmpId(Integer.parseInt(strTok[0]));
			emp.setName(strTok[1]);
			emp.setAge(Integer.parseInt(strTok[2]));
			emp.setDepartment(strTok[3]);
			emp.setDesignation(strTok[4]);
			emp.setCountry(strTok[5]);
			emp.setDoj(LocalDate.parse(strTok[6]));
			emp.setCreatedTime(LocalDateTime.parse(strTok[7], formatter));
			emp.setModifiedTime(LocalDateTime.parse(strTok[8], formatter));

			return emp;
		}
	}

	// method to export file from thread
	class ExportCallable implements Callable<String> {

		private List<Employee> employeeList;
		public static final String EXPORT_FILE_NAME = "C:/Training/java-data/BulkExportFile-1.txt";

		public ExportCallable(List<Employee> empList) {
			this.employeeList = empList;
		}

		@Override
		public String call() throws Exception {
			System.out.println(Thread.currentThread().getName() + " --> Running thread for processing bulkExport..");

			try {
				System.out.println(
						Thread.currentThread().getName() + " --> Starting to write to file.. -> " + EXPORT_FILE_NAME);

				for (Employee emp : employeeList) {
					writeToFile(emp);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " --> Finished reading from file..");

			System.out.println(
					Thread.currentThread().getName() + " --> Finished creating list of Employee objects from thread..");

			return EXPORT_FILE_NAME;
		}

		private void writeToFile(Employee emp) {
			try {
				PrintWriter pw = new PrintWriter(new FileWriter(EXPORT_FILE_NAME, true));
				pw.println(emp.toString());
				pw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
