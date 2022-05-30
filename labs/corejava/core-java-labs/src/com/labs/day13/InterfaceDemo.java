package com.labs.day13;

public interface InterfaceDemo {
	//java 8
	static void staticprint() {
		System.out.println("Test static");
	}
	//java 8
	default void testDefault() {
		System.out.println("Test default");
		testPrivate();
	}
	//java 9
	private void testPrivate() {
		System.out.println("Test private");
		
	}

}
