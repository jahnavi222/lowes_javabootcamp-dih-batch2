
package com.assignments.arrays;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PrintArrayEvenEle {

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

			System.out.println("Print Array even elements");
			for (int i : srcArray) {
				if(i%2==0)
				System.out.println(i);
			}

		} catch (InputMismatchException|ArrayIndexOutOfBoundsException e) {
			System.out.println("Exception occured:" + " " + e);
		} finally {
			if (sc != null)
				sc.close();
		}
	}

	}


