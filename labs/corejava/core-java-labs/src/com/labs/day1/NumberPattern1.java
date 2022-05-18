package com.labs.day1;

public class NumberPattern1 {

	public static void main(String[] args) {

		for (int i = 0; i < 7; i++) {
			int number = 1;
			for (int j = 0; j <= i; j++) {

				System.out.print(number + " ");
				number++;
			}
			System.out.println("");
		}
		System.out.println("------------------------");
		int k = 1;
		for (int i = 0; i < 7; i++) {

			for (int j = 0; j <= i; j++) {

				System.out.print(k + " ");
				k++;
			}
			System.out.println("");
		}
		System.out.println("------------------------");
		for (int i = 1; i <= 9; i++) {

			for (int j = 9; j >= i; j--) {

				System.out.print(j + " ");

			}

			System.out.println();
		}
		System.out.println("------------------------");

		for (int i = 7; i >= 1; i--) {

			for (int j = i; j >= 1; j--) {
				System.out.print(j + "");
			}
			System.out.println();
		}
		System.out.println("------------------------");
		for (int i = 1; i <= 9; i++) {
			for (int j = 9; j >= i; j--) {
				System.out.print(j + " ");
			}
			System.out.println();
		}
		System.out.println("------------------------");
		for (int i = 1; i <= 7; i++) {

			for (int j = 7; j >= i; j--) {
				System.out.print(j + " ");
			}
			System.out.println();
		}
		System.out.println("------------------------");

		for (int i = 9; i >= 1; i--) {

			for (int j = 1; j <= i; j++) {
				System.out.print(j + " ");
			}
			System.out.println();
		}

	}
}
