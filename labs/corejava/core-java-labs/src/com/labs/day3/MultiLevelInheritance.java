package com.labs.day3;

//Multilevel inheritance
class A1 {

	public void test1() {

	}
}

class B extends A1 {

	public void test2() {

	}
}

class C extends A1 {

	public void test3() {

	}
}

public class MultiLevelInheritance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		A1 a = new A1();
		a.test1();

		B b = new B();
		b.test2();
		b.test1();

		C c = new C();
		c.test1();
		c.test3();

	}

}
