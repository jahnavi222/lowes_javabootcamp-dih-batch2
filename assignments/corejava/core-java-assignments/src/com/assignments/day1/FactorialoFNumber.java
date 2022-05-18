package com.assignments.day1;

import java.util.InputMismatchException;
import java.util.Scanner;

//This class calculates the factorial of given number using for loop and while loop
public class FactorialoFNumber {
	// method used to calculate factorial of number using whileLoop
	static int factUsingWhileLoop(int n) {

		int fact = 1;

		// returns factorial of zero as 1
		if (n == 0)
			return fact;

		int i = 1;
		// calculates factorial of number >0
		while (i <= n) {

			fact = fact * i;
			i++;

		}
		return fact;

	}

	// method used to calculate factorial of number using forLoop
	static int factUsingForLoop(int n) {

		int fact = 1;
		// returns factorial of zero as 1
		if (n == 0)
			return fact;
		// calculates factorial of number >0
		for (int i = 1; i <= n; i++) {
			fact = fact * i;
		}

		return fact;
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n1 = 0;
		System.out.println("Enter the number to calculate the factorial of number:");
		try {

			n1 = sc.nextInt();
			System.out.println("You have Enetered the number: " + n1);
			// factorial of number using forloop
			System.out.println("Factorial of number " + n1 + " using for Loop" + " is " + factUsingForLoop(n1));
			// factorial of number using Whileloop
			System.out.println("Factorial of number " + n1 + " using While Loop" + " is " + factUsingForLoop(n1));
		} catch (InputMismatchException ex) {

			System.out.println("invalid input");

		} finally {
			if (sc != null)
				sc.close();
		}

	}
}
