package com.assignments.arrays;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
//remove the duplicates
public class RemoveDuplicates {
	public static int removeDuplicates(int a[], int n) {
		
		if (n == 0 || n == 1) {
			return n;
		}

		int j = 0;

		for (int i = 0; i < n - 1; i++) {
			if (a[i] != a[i + 1]) {
				a[j++] = a[i];
			}
		}

		a[j++] = a[n - 1];

		return j;
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
			
            System.out.println("Print Array elements without duplicates");
            Arrays.sort(srcArray);
			// printing array elements
            int j=0;
            j=removeDuplicates(srcArray, srcArray.length);
			for (int i = 0; i < j; i++) {
				System.out.print(srcArray[i] + " ");
			}

		} catch (InputMismatchException | ArrayIndexOutOfBoundsException e) {
			System.out.println("Exception occured:" + " " + e);
		} finally {
			if (sc != null)
				sc.close();
		}

	}

}
