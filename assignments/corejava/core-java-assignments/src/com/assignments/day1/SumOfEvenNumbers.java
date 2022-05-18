package com.assignments.day1;

import java.util.InputMismatchException;
import java.util.Scanner;

//This class calculates the sum of the first N even numbers 
public class SumOfEvenNumbers {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int sum = 0;
		System.out.println("Enter the number to calculate the sum of even numbers :");
		int i = 0;
		try {
			int n = sc.nextInt();

			System.out.println("You have Enetered the number: " + n);
			while (i <= n) {

				// check for the even number
				if (i % 2 == 0) {
					System.out.println("even number:  " + i);
					sum = sum + i;
				}
				i++;
			}

		} catch (InputMismatchException ex) {

			System.out.println("invalid input");

		} finally {
			if (sc != null)
				sc.close();
		}

		System.out.println("total Sum of even numbers :" + sum);
	}
}