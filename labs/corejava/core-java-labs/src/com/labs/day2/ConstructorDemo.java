package com.labs.day2;

//This class defines on constructor and different types of constructor usage

class Student {

	public String name;
	public int id;
	public String grade;
	public String group;
	int counter = 0;
	static int counter1 = 0;

	public Student() {
		System.out.println("default constructor");
		counter++;
		counter1++;
		System.out.println(counter);
		System.out.println(counter1);
	}

	public Student(String name) {
		System.out.println("parameterised constructor");
	}

	public Student(int id) {
		System.out.println("diffrenet data type based constructor");
	}

	public Student(String grade, int id) {
		System.out.println("number of parameter  based constructor");
	}

	public Student(int id, String group) {
		System.out.println("order of parameter  based constructor");
	}

	public Student(String name, int id, String grade, String group) {
		this.name = name;
		this.id = id;
		this.grade = grade;
		this.group = group;
		System.out.println("name:" + name + " id:" + id + " grade:" + grade + " group:" + group);
	}

	public Student(Student std) {
		this.name = std.name;
		this.id = std.id;
		this.grade = std.grade;
		this.group = std.group;
		System.out.println("name:" + name + " id:" + id + " grade:" + grade + " group:" + group);
	}

	public Student(Student std, String group) {
		this.name = std.name;
		this.id = std.id;
		this.grade = std.grade;
		this.group = group;
		System.out.println("name:" + name + " id:" + id + " grade:" + grade + " group:" + group);

	}

}

public class ConstructorDemo {
	public static void main(String[] args) {

		Student std = new Student();
		Student std11 = new Student();
		Student std112 = new Student();
		Student std1 = new Student("Student1");
		Student std2 = new Student(100);
		Student std3 = new Student("A", 200);
		Student std4 = new Student(300, "B");
		// TODO Auto-generated method stub
		Student std5 = new Student("Student2", 100, "B", "yellow");
		Student std6 = new Student(std5);
		Student std7 = new Student(std5, "Blue");
	}

}
