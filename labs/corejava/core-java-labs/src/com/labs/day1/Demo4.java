package com.labs.day1;

public class Demo4 {

	public static void main(String[] args) {

		for (int i = 0; i < 10; i++) {

			System.out.println(i);
		}

		System.out.println();
		System.out.println();
		for (int i = 10; i > 0; i--) {

			System.out.println(i);
		}
		System.out.println();

		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= i; j++) {
				System.out.print("*" + " ");
			}
			System.out.println();
		}
		System.out.println();

		for (int i = 1; i <= 5; i++) {
			for (int j = 5; j >= i; j--) {
				System.out.print("*" + " ");
			}
			System.out.println();
		}

		System.out.println();
		int row = 5;
		for (int i = 0; i < 5; i++) {
			for (int j = (row - i); j > 1; j--) {
				System.out.print(" ");
			}
			for (int j = 0; j < i; j++) {
				System.out.print("*" + " ");
			}
			System.out.println();
		}
	}

}
