package com.assignments.scanner;

import java.util.Scanner;
import java.util.InputMismatchException;

//This class provieds details about calculator app
public class CalculatorApp {
	// menu options
	public static void ShowMenuOptions() {
		System.out.println("1.Addition");
		System.out.println("2.Substraction");
		System.out.println("3.Multiplication");
		System.out.println("4.Division");
		System.out.println("5.Exit");
		System.out.print("Enter Your Choice (1-5): ");

	}

	public static void main(String[] args) {
		float a, b, res = 0;
		int choice = 0;
		boolean exitOption = false;
		Scanner sc = new Scanner(System.in);

		while (true) {
			ShowMenuOptions();

			try {
				choice = sc.nextInt();

				switch (choice) {
				case 1:

					// add two numbers
					System.out.println("\n Enter two numbers");
					a = sc.nextFloat();
					b = sc.nextFloat();
					res = a + b;
					break;
				case 2:
					// substract numbers
					System.out.println("\n Enter two numbers");
					a = sc.nextFloat();
					b = sc.nextFloat();
					res = a - b;
					break;
				case 3:
					// multiply two numbers
					System.out.println("\n Enter two numbers");
					a = sc.nextFloat();
					b = sc.nextFloat();
					res = a * b;
					break;
				case 4:

					// Divide two numbers
					System.out.println("\n Enter two numbers");
					a = sc.nextFloat();
					b = sc.nextFloat();
					res = a / b;
					break;

				case 5:
					System.out.println("\n Thanks you using the Calculator App");
					exitOption = true;
					return;
				default:
					System.out.println("\nInvalid choice!");
					break;

				}
				System.out.println("\nResult = " + res + "\n");
			} catch (InputMismatchException | ArithmeticException ex) {
				System.out.println("Exception Occured " + " " + ex);
				return;

			} finally {
				if (sc != null && exitOption == true)
					sc.close();
			}
		}

	}

}
