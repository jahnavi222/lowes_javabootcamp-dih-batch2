package com.assignments.day1;

import java.util.InputMismatchException;
import java.util.Scanner;

//This class  calculate the sum of the numbers occurring in the multiplication
//table for the given number
public class SumOfMulTable {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = 0, sum = 0;
		System.out.println("Enter the number to display multiliplication table:");
		try {

			n = sc.nextInt();
			System.out.println("You have Enetered the number: " + n);
			System.out.println("Multiplication of table  " + n);
			System.out.println("****************");
			for (int i = 1; i <= 10; i++) {

				System.out.println(n + " X " + i + " = " + (n * i));
				// calculates the total sum for the given table
				sum = sum + (n * i);
				System.out.println("sum of the each iteration  " + sum);
			}

		} catch (InputMismatchException ex) {

			System.out.println("invalid input");

		} finally {
			if (sc != null)
				sc.close();
		}
		System.out.println("************");
		System.out.println("Total Sum of the values in Multiplication table for the given number:" + sum);
	}
}
