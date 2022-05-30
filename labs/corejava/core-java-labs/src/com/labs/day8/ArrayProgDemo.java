package com.labs.day8;

import java.util.Arrays;

public class ArrayProgDemo {
	// minimum element in the array
	static int minNumber(int a[]) {

		int min = a[0];

		for (int i = 1; i < a.length; i++) {

			if (min > a[i]) {
				min = a[i];
			}
		}
		System.out.println(min);

		return min;

	}

	// minimum element in the array
	static int minBySort(int a[]) {

		Arrays.sort(a);

		System.out.println(a[0]);

		return a[0];

	}

	// return the array as return type
	static int[] get() {

		return new int[] { 1, 2, 3 };

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int a[] = { 4, 7, 8, 2 };
		minNumber(a);
		minNumber(a);
		int a1[] = get();

		for (int i = 0; i < a1.length; i++) {
			System.out.println(a1[i]);
		}

		int a2[][] = { { 1, 2, 3 }, { 5, 6, 7 }, { 8, 9, 0 } };

		for (int i = 0; i < a2.length; i++) {
			for (int j = 0; j < a2[i].length; j++) {

				System.out.print(a2[i][j] + " ");
			}
			System.out.println(" ");
		}

	}

}
