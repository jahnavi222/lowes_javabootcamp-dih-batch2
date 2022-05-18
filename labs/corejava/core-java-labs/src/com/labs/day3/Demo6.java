package com.labs.day3;

class A {

	static int a = 30;

	static {
		System.out.println(a);
	}

	public void display() {
		System.out.println(a);
	}

}

public class Demo6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		A ob = new A();
		ob.display();
	}

}
