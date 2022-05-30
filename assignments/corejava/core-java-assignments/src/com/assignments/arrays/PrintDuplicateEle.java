package com.assignments.arrays;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PrintDuplicateEle {
	// find the duplicate elements in the array
	static void findDuplicate(int arr[]) {

		 int count[] = new int[arr.length];
	        int i;
	 
	        System.out.println("duplicate elements are : ");
	        for (i = 0; i < arr.length; i++)
	        {
	            if (count[arr[i]] == 1)
	                System.out.print(arr[i] + " ");
	            else
	                count[arr[i]]++;
	        }

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

			System.out.println("Print Array elements");
			for (int i : srcArray) {
				System.out.println(i);
			}
			findDuplicate(srcArray);

			

		} catch (InputMismatchException | ArrayIndexOutOfBoundsException e) {
			System.out.println("Exception occured:" + " " + e);
		} finally {
			if (sc != null)
				sc.close();
		}

	}

}
