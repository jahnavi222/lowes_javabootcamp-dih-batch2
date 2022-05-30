package com.labs.day8;

import java.util.Scanner;

public class ScannerDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		    @SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
	        System.out.println("Enter first name and last name");
	        String firstName = scanner.next();
	        String lastName = scanner.next();
	        System.out.println("FullName: "+ (firstName + lastName));

	}

}
