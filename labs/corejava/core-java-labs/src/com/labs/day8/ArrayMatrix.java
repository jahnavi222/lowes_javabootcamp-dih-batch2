package com.labs.day8;

public class ArrayMatrix {

	public static void main(String[] args) {
		int a[][] = { { 1, 2, 3 }, { 3, 4, 5 }, { 6, 7, 8 } };
		int b[][] = { { 1, 2, 3 }, { 3, 4, 5 }, { 6, 7, 8 } };

		int c[][] = new int[3][3];

		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b[i].length; j++) {
				c[i][j] = a[i][j] + b[i][j];
				System.out.print((c[i][j] + " "));
			}
			System.out.println();
		}
		System.out.println("------------------");

		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b[i].length; j++) {
				c[i][j] = 0;
				for (int z = 0; z < a[i].length; z++) {
					c[i][j] = a[i][z] + b[z][j];
				}
				System.out.print((c[i][j] + " "));
			}
			System.out.println();
		}
	}

}
