package com.labs.day3;

//usage of static block
class Test {

	public void addnumber(int a, int b) {

		System.out.println(a + b);
	}

	public static void subNumber(int a, int b) {

		System.out.println(a - b);
	}

}

public class Demo1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test t = new Test();
		t.addnumber(100, 50);
		Test.subNumber(100, 50);

	}

}
