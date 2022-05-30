package com.assignments.arrays;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ArrayCopy {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = 0;
		System.out.print("Enter the number of elements you want to store: ");
		try {
			if (sc.hasNextInt()) {
				n = sc.nextInt();
			}
			int srcArray[] = new int[n];

			for (int i = 0; i < n; i++) {
				srcArray[i] = sc.nextInt();
			}
             //first method using clone method
			int destArray[] = srcArray.clone();
			System.out.println("Source Array elements");
			for (int i : srcArray) {
				System.out.println(i);
			}

			System.out.println("destination Array elements");
			for (int i : destArray) {
				System.out.println(i);
			}

			// second method to copy the array

			for (int i = 0; i < n; i++) {
				destArray[i] = srcArray[i];
			}
			// source array
			System.out.println("Source Array elements");
			for (int i : srcArray) {
				System.out.println(i);
			}
			// destination
			System.out.println("destination Array elements");
			for (int i : destArray) {
				System.out.println(i);
			}
		} catch (InputMismatchException e) {
			System.out.println("Exception occured:" + " " + e);
		} finally {
			if (sc != null)
				sc.close();
		}
	}

}
