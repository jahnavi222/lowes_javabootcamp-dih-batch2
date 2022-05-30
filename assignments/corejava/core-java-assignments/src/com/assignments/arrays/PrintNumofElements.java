package com.assignments.arrays;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PrintNumofElements {

	static int countElements(int arr[]) {

		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			count++;

		}
		return count;

	}

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

			System.out.println("Number of Element in Array is:" + " " + countElements(srcArray));

		} catch (InputMismatchException | ArrayIndexOutOfBoundsException e) {
			System.out.println("Exception occured:" + " " + e);
		} finally {
			if (sc != null)
				sc.close();
		}
	}
}
