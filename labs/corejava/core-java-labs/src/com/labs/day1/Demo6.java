package com.labs.day1;

public class Demo6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// pascal triangle

		int i, j, rows = 6;

		for (i = 0; i <= rows; i++) {

			for (j = 0; j <= i; j++) {
				System.out.print("*" + " ");
			}

			System.out.println("");
		}
		for (i = rows; i >= 0; i--) {

			for (j = 0; j <= i - 1; j++) {
				System.out.print("*" + " ");
			}
			System.out.println("");
		}
	}

}
