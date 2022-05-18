package com.labs.day3;

class Student {
	public String name;

	public int rollnumber;

	static String schoolname = "Testtt";

}

public class DemoOnStatic {

	public static void main(String[] args) {

		Student std1 = new Student();
		std1.name = "t";
		std1.rollnumber = 1;

		System.out.println("name " + std1.name + " rollnumber " + std1.rollnumber + Student.schoolname);

		Student std2 = new Student();
		std2.name = "p";
		std2.rollnumber = 2;
		System.out.println("name " + std2.name + " rollnumber " + std2.rollnumber + " " + Student.schoolname);
	}

}
