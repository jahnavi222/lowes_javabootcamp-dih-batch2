package com.labs.day8;

public class ArrayOperations {

	public static void main(String[] args) {
		char[] sourceArray = { 'h', 'e', 'l', 'l', 'o' };

		char[] destinationArr = new char[5];

		System.arraycopy(sourceArray, 0, destinationArr, 0, 5);
		System.out.println(String.valueOf(destinationArr));

		int a[] = { 1, 2, 3, 4 };
		System.out.println("Print original Array");
		for (int i : a) {
			System.out.println(i);
		}

		int[] newArray = a.clone();
		System.out.println("Print clones Array");
		for (int i : newArray) {
			System.out.println(i);
		}
	}

}
