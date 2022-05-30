package com.labs.day1;

public class Demo1 {
	static int z = 100;

	int b = 20;
	int c = 30;
	int d = 40;
	int a = 60;

	public int addNumber() {

		int a = 30;
		int b = 20;

		return (a + b);

	}

	public static void main(String[] args) {

		Demo1 dm = new Demo1();
		// operators demo
		System.out.println(dm.addNumber());
		System.out.println(dm.b * dm.c);
		System.out.println(dm.c - dm.b);
		System.out.println(dm.c / dm.b);
		System.out.println(dm.b > dm.c);
		System.out.println(dm.b < dm.c);
		System.out.println(dm.b++);
		System.out.println(dm.b);
		System.out.println(++dm.b);
		System.out.println(Demo1.z);
		System.out.println(dm.b > dm.c && dm.d > dm.c); // false
		System.out.println(dm.d > dm.b && dm.c < dm.b); // false
		System.out.println(dm.a > dm.b || dm.a < dm.c); // true
		// TODO Auto-generated method stub

		// ternary operator usage
		int max = dm.a > dm.b ? dm.a : dm.b;
		System.out.println(max);

		int age = 14;
		
		
		
				

		String result = age >= 18 ? "eligible" : "not eligible";

		System.out.println(result);

		int number = 8;
		String output = (number % 2 == 0) ? "even" : "odd";
		System.out.println(output);

		int score = 80;

		// if else if block statement

		if (score >= 80)

			System.out.println("grade A");

		else if (score < 80 && score > 60)

			System.out.println("grade B");

		else if (score < 50 && score > 40)
			System.out.println("grade C");
		else

			System.out.println("grade D");

		int age1 = 15;

		// if else block
		if (age1 > 18)
			System.out.println("eligible");
		else
			System.out.println("not eligible");

		// largest of three numbers

		int a = 30, b = 20, c = 10;

		int largest = c > (a > b ? a : b) ? c : ((a > b) ? a : b);

		System.out.println("largest number:+ " + largest);

		int smallest = c < (a < b ? a : b) ? c : ((a < b) ? a : b);
		System.out.println("smallest number:+ " + smallest);
		String x="abc";
		String y="abc";
		System.out.println(x);
	}

}
