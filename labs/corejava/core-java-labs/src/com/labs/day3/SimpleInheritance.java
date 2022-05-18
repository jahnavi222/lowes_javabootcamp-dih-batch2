package com.labs.day3;

class Employee {

	public String firstname;
	public String lastname;

	// public Employee() {
	// System.out.println("parent constructor");
	// }
	public Employee(String test) {
		System.out.println("parent constructor");
	}

	public void getName() {
		System.out.println(firstname + " " + lastname);
	}

}

class FullTimeEmployee extends Employee {
	String test;
	public int annualSlarary;

	// public FullTimeEmployee() {
	// System.out.println("child1 constructor");
	// }

	public FullTimeEmployee(String test) {
		super(test);
		System.out.println("child1 constructor");
	}
}

class PartTimeEmployee extends Employee {
	public int hourlySalary;
	String test;

	public PartTimeEmployee(String test) {
		super(test);
		System.out.println("child2 constructor");
	}
}

public class SimpleInheritance {

	public static void main(String[] args) {
		String test = "kkk";
		FullTimeEmployee emp = new FullTimeEmployee(test);
		emp.firstname = "Test1";
		emp.lastname = "TT";
		emp.getName();

	}
}