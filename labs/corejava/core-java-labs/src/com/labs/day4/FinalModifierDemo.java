package com.labs.day4;
//Final Modifier demo

class Car {
	public final int speedLimit = 60;

	public void drive() {

		// variable with final modifier is not allowed to change the value
		// speedLimit = 80;
		System.out.println("car is driving at speed of " + speedLimit);
	}

}

class Bike {
	public final void drive() {
		System.out.println("driving...");
	}

	// method cannot be overridden with final modifier
	class Honda extends Bike {
		// @Override
		// public void drive() {
		// System.out.println("driving...");
		// }
	}

	// TODO Auto-generated method stub
	final class Parent3 {

	}
	// cannot inherit the parent with final modifier
	// class Child3 extends Parent3{
	//
	// }

	public class FinalModifierDemo {

		public static void main(String[] args) {

			Car car = new Car();

			// variable with final modifier is not allowed to change the value of variable
			// .speedLimit = 90;
			car.drive();

		}

	}
}