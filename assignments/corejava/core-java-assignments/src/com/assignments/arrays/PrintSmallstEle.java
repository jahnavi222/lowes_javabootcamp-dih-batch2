package com.assignments.arrays;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PrintSmallstEle {

	
		static int smallElement(int arr[]) {

			int smallNumber;
			smallNumber = arr[0];
			for (int i = 1; i < arr.length; i++) {
				if (arr[i] < smallNumber) {
					smallNumber = arr[i];
				}

			}
			return smallNumber;

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

				System.out.println("Smallst Element in Array is:" + " " + smallElement(srcArray));

			} catch (InputMismatchException | ArrayIndexOutOfBoundsException e) {
				System.out.println("Exception occured:" + " " + e);
			} finally {
				if (sc != null)
					sc.close();
			}
		
	}

}
