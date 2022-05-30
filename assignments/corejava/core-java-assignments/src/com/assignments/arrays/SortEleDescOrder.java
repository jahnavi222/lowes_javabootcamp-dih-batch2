package com.assignments.arrays;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SortEleDescOrder {


	static void printArray(int arr[]) {
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}

	static void sortByAscOrder(int arr[]) {

		int temp = 0;
		for (int i = 0; i < arr.length; i++) {

			for (int j = i + 1; j < arr.length; j++) {

				if (arr[i] < arr[j]) {
					temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
	}
	
	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = 0;
		System.out.print("Enter the numbers to store in Array: ");
		try {
			if (sc.hasNextInt()) {
				n = sc.nextInt();
			}
			int srcArray[] = new int[n];

			for (int i = 0; i < n; i++) {
				srcArray[i] = sc.nextInt();
			}
			System.out.println("Before Sorting:");
			printArray(srcArray);
			sortByAscOrder(srcArray);
			System.out.println("After Sorting:");
			printArray(srcArray);
			
			
		} catch (InputMismatchException | ArrayIndexOutOfBoundsException e) {
			System.out.println("Exception occured:" + " " + e);
		} finally {
			if (sc != null)
				sc.close();
		}

	}
}
