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

import com.lowes.empapp.dao.EmployeeDAo;
import com.lowes.empapp.dao.EmployeeDaoJdbcImpl;
import com.lowes.empapp.exception.EmployeeException;
import com.lowes.empapp.exception.EmployeeNotFoundException;
import com.lowes.empapp.model.Employee;

//this is service class implementation
public class EmployeeServiceJdbcmpl implements EmployeeService, Comparator<Employee> {
	EmployeeDAo empDaoSer = new EmployeeDaoJdbcImpl();

	Map<Integer, Employee> employeeMap = new HashMap<Integer, Employee>();
	// static Map<Integer, Employee> employeeMap1 = new HashMap<Integer,
	// Employee>();
	Employee emp = null;
	LocalDateTime nowTime = LocalDateTime.now();
	private static List<Employee> empListStats = null;

	// Employee emp4=null;
	public EmployeeServiceJdbcmpl() {
		// employeeMap = new HashMap<Employee, Integer>();
		employeeMap.put(101, new Employee("John", 35, "Dept 1", "Desig 1", "India", LocalDate.parse("2021-04-10"),
				nowTime.minusYears(3).minusMonths(1), nowTime.minusYears(1)));
		employeeMap.put(102, new Employee("Adam", 25, "Dept 2", "Desig 2", "USA", LocalDate.parse("2020-03-10"),
				nowTime.minusYears(3).minusMonths(2), nowTime.minusYears(3)));
		employeeMap.put(103, new Employee("Bob", 30, "Dept 3", "Desig 3", "India", LocalDate.parse("2019-02-10"),
				nowTime.minusYears(3).minusMonths(3), nowTime.minusYears(3)));
		employeeMap.put(104, new Employee("Anna", 35, "Dept 4", "Desig 4", "France", LocalDate.parse("2018-01-10"),
				nowTime.minusYears(4).minusMonths(4), nowTime.minusYears(4)));
		employeeMap.put(105, new Employee("Alice", 35, "Dept 3", "Desig 5", "UK", LocalDate.parse("2021-04-10"),
				nowTime.minusYears(1).minusMonths(1), nowTime.minusYears(1)));
		employeeMap.put(106, new Employee("Greg", 25, "Dept 3", "Desig 6", "USA", LocalDate.parse("2020-03-10"),
				nowTime.minusYears(2).minusMonths(2), nowTime.minusYears(2)));
		employeeMap.put(107, new Employee("Employee 7", 30, "Dept 4", "Desig 7", "India", LocalDate.parse("2019-02-10"),
				nowTime.minusYears(3).minusMonths(3), nowTime.minusYears(3)));
		employeeMap.put(108, new Employee("Employee 8", 38, "Dept 4", "Desig 8", "France",
				LocalDate.parse("2018-01-10"), nowTime.minusYears(4).minusMonths(4), nowTime.minusYears(4)));

	}

	// create the employee record
	@Override
	public boolean create(Employee emp) throws EmployeeException {
		boolean flag = false;
		flag = empDaoSer.create(emp);

		return flag;
	}

	// view the employee details by id
	@Override
	public Employee viewEmployeeById(int emp_id) {
		// emp = null;

		try {
			emp = empDaoSer.viewEmployeeById(emp_id);
		} catch (EmployeeNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return emp;
	}

	//// view all employee details
	@Override
	public List<Employee> viewAllEmployee() throws EmployeeNotFoundException {
		List<Employee> empList = null;

		empList = empDaoSer.viewAllEmployee();

		return empList;

	}

	// update the employee
	@Override
	public boolean update(Employee emp) throws EmployeeNotFoundException {
		// emp = new Employee();
		boolean flag = false;

		try {
			flag = empDaoSer.update(emp);
		} catch (EmployeeNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new EmployeeNotFoundException("Unable to update employee");

		}

		return flag;
	}

	// delete the employee
	@Override
	public boolean delete(int emp_id) {
		boolean flag = false;
		try {
			flag = empDaoSer.delete(emp_id);
		} catch (EmployeeNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

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
		// empListStats=getEmployeeStaticData();
		empIdList = empListStats.stream().filter(e -> e.getAge() > age).map(Employee::getEmpId)
				.collect(Collectors.toList());

		return empIdList;
	}

	// get count of employee age greater than condition
	public long getEmployeeCountAgeGreaterThan(Predicate<Employee> condition) {
		if(empListStats == null) {
			try {
				empListStats=getEmployeeStaticData();
			} catch (EmployeeNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		// 
		// long count = empListStats.stream().filter(e ->
		// condition.test(e.getAge().count());
		long count = empListStats.stream().filter(e -> condition.test(e)).count();
		return count;
	}

	// get employee age greater than condition
	public Map<String, Long> getEmployeeCountByDepartment() {
		// empListStats=getEmployeeStaticData();
		Map<String, Long> empDepCount = null;

		empDepCount = empListStats.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));

		return empDepCount;
	}

	// get employee count by department
	public Map<String, Long> getEmployeeCountByDepartmentOrdered() {
		// empListStats=getEmployeeStaticData();
		Map<String, Long> empDepCount = null;

		empDepCount = empListStats.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, TreeMap::new, Collectors.counting()));

		return empDepCount;
	}

	// get employee age count by department by orderd
	public Map<String, Double> getAvgEmployeeAgeByDept() {
		// empListStats=getEmployeeStaticData();
		Map<String, Double> empDepCount = null;

		empDepCount = empListStats.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getAge)));

		return empDepCount;
	}
    //// get employee  avereage age by department
	public Map<String, Double> getAvgEmployeeServiceByDept() {

		// empListStats=getEmployeeStaticData();
		Map<String, Double> empDepCount = null;

		System.out.println("Today's Local Date: " + LocalDate.now());

		empDepCount = empListStats.stream().collect(Collectors.groupingBy(Employee::getDepartment,
				Collectors.averagingDouble(e -> Period.between(e.getDoj(), LocalDate.now()).getYears())));

		return empDepCount;
	}

	// get number of employeess having more in department
	public List<String> getDepartmentsHaveEmployeesMoreThan(int criteria) {
		// empListStats=getEmployeeStaticData();
		List<String> deptEmpList = null;

		deptEmpList = empListStats.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting())).entrySet().stream()
				.filter(e -> e.getValue() > criteria).map(Map.Entry::getKey).collect(Collectors.toList());

		return deptEmpList;
	}

	// getemployeess name start with prefix
	public List<String> getEmployeeNamesStartsWith(String prefix) {
		// empListStats=getEmployeeStaticData();
		List<String> empNamePrefixList = null;

		empNamePrefixList = empListStats.stream().filter(e -> e.getName().startsWith(prefix)).map(Employee::getName)
				.collect(Collectors.toList());

		return empNamePrefixList;
	}

	// get the employee data from db to get stastics
	List<Employee> getEmployeeStaticData() throws EmployeeNotFoundException {
		//empListStats = new ArrayList<Employee>();

		empListStats = viewAllEmployee();

		/*for (Employee e : empListStats) {
			empListStats.add(e);

		}*/
		//System.out.println(empListStats);
		return empListStats;

	}

	// export the data from hashmap to file
	public void bulkExport() {
		List<Employee> employeeList1 = null;
		String fileName = null;
		List<Employee> employeeList = null;
		try {
			employeeList = viewAllEmployee();
		} catch (EmployeeNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		if (employeeList != null) {
			employeeList1 = new ArrayList<>(employeeList);

			Callable<String> callable = new ExportCallable(employeeList1);
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

	// export data from database to file
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

	// class to import file and real emp details create employees in database
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

		private Employee getEmployeeFromTokens(String[] strTok) {

			Employee emp = new Employee();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

			//emp.setEmpId(Integer.parseInt(strTok[0]));
			emp.setName(strTok[0]);
			emp.setAge(Integer.parseInt(strTok[1]));
			emp.setDepartment(strTok[2]);
			emp.setDesignation(strTok[3]);
			emp.setCountry(strTok[4]);
			emp.setDoj(LocalDate.parse(strTok[5]));
			emp.setCreatedTime(LocalDateTime.parse(strTok[6], formatter));
			emp.setModifiedTime(LocalDateTime.parse(strTok[7], formatter));

			return emp;
		}
	}

	// bulk import
	public void bulkImport() throws EmployeeException {

		List<String> csvLines = null;

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

		/*
		 * if(employees == null) { employees = new HashMap<>();
		 */

		try {
			if (empList != null) {
				for (Employee emp : empList) {
					empDaoSer.create(emp);
				}
			}
		} catch (EmployeeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new EmployeeException("Unable to create Employee creation");
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
}
