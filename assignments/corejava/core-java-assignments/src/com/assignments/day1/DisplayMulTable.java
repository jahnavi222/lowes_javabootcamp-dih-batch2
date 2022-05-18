package com.assignments.day1;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class is used to display the multiplication table for the given number
 * 
 */
public class DisplayMulTable {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = 0;
		System.out.println("Enter the number to display multiliplication table:");
		try {

			n = sc.nextInt();
			System.out.println("You have Enetered the number: " + n);
			System.out.println("Multiplication of table  " + n);
			System.out.println("****************");
			for (int i = 1; i <= 10; i++) {

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
