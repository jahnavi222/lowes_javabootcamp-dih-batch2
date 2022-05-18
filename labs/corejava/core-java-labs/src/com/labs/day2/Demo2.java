package com.labs.day2;

public class Demo2 {

	public static void main(String[] args) {

		for (int i = 1; i < 10; i++) {
			if (i == 5) {
				System.out.println("break");
				break;
			}
			System.out.println(i);
		}
	}

}