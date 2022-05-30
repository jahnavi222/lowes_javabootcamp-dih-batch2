package com.assignments.arrays;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SortEleAscOrder {
	// print the array elements
	static void printArray(int arr[]) {
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}

	// sort the elements by ascending order
	static void sortByAscOrder(int arr[]) {

		int temp = 0;
		for (int i = 0; i < arr.length; i++) {

			for (int j = i + 1; j < arr.length; j++) {

				if (arr[i] > arr[j]) {
					temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
	}

	// get the third largest number after sorting the array by ascending order
	static int thirdLargestNum(int arr[]) {

		return arr[2];

	}

	// get the second largest number after sorting the array by ascending order
	static int secondLargestNum(int arr[]) {

		return arr[1];

	}

	// get the largest number after sorting the array by ascending order
	static int largestNum(int arr[]) {

		return arr[arr.length - 1];

	}

	// get the smallest number after sorting the array by ascending order
	static int smallestNum(int arr[]) {

		return arr[0];

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
			System.out.println("Third Largest Number in Array:" + " " + thirdLargestNum(srcArray));
			System.out.println("Second Largest Number in Array:" + " " + secondLargestNum(srcArray));
			System.out.println(" Largest Number in Array:" + " " + largestNum(srcArray));
			System.out.println(" smallest Number in Array:" + " " + smallestNum(srcArray));
		} catch (InputMismatchException | ArrayIndexOutOfBoundsException e) {
			System.out.println("Exception occured:" + " " + e);
		} finally {
			if (sc != null)
				sc.close();
		}

	}
}
