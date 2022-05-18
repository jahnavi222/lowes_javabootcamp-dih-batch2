package com.assignments.day1;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class is used to display the multiplication table for the given number
 * in reverse order
 */
public class DisplayMulTabInRev {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = 0;
		System.out.println("Enter the number to display multiliplication of table:");
		try {

			n = sc.nextInt();
			System.out.println("You have Enetered the number: " + n);
			System.out.println("Multiplication of table " + n + " in reverse order ");
			System.out.println("****************");

			for (int i = 10; i >= 1; i--) {

				System.out.println(n + " X " + i + " = " + (n * i));
			}

		} catch (InputMismatchException ex) {

			System.out.println("invalid input");

		} finally {
			if (sc != null)
				sc.close();
		}

	}

}
