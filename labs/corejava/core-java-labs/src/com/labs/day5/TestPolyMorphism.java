package com.labs.day5;

//comiple time polymorphism
class Sum {

	public int sum(int n1, int n2) {

		return (n1 + n2);

	}

	public int sum(int n1, int n2, int n3) {

		return (n1 + n2 + n3);

	}

	public double sum(double n1, double n2) {

		return (n1 + n2);

	}
}

//run time polymorphism

class A {

	void m1() {
		System.out.println("Inside A class method");
	}
}

class B extends A {

	void m1() {
		System.out.println("Inside B class method");
	}
}

class C extends B {

	void m1() {
		System.out.println("Inside C class method");
	}
}

public class TestPolyMorphism {

	public static void main(String[] args) {
	
		// compile time
		Sum s = new Sum();
		System.out.println(s.sum(10.5, 20.5));
		System.out.println(s.sum(1, 2));
		System.out.println(s.sum(4, 5, 8));
		// run time
		A a = new B();
		B b = new C();
		C c = new C();
		A a1 = new C();
		a.m1();
		b.m1();
		c.m1();
		a1.m1();
	}

}
