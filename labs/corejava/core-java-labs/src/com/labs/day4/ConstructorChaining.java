package com.labs.day4;

//Constructor chaining using this object
class Test {

	public Test() {
		this("A", "B");
		System.out.println(" base default constructor");
	}

	public Test(String a, String b) {

		System.out.println("base parameterized constructor");
	}

	public void display() {

		System.out.println("this() chaining");
	}

}
//Constructor chaining using super call
class Test1 extends Test {

	public Test1() {
		System.out.println(" derived default parameter");
	}

	public Test1(String aa, String bb) {
		super("C", "D");
		System.out.println("derived parameter constructor");

	}

	public void display() {

		System.out.println("super call chaining");
	}

}

public class ConstructorChaining {

	public static void main(String[] args) {

		Test t = new Test();
		t.display();
		Test1 t1 = new Test1();
		t1.display();
		Test1 t2 = new Test1("tt", "bb");
		t2.display();

	}

}
