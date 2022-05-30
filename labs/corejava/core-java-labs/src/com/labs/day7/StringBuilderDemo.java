package com.labs.day7;

public class StringBuilderDemo {

	public static void main(String[] args) {
     StringBuilder sb = new  StringBuilder("Hello");
		//append method
		sb.append("World");
		System.out.println(sb);
		//insert method
		sb.insert(1, "Test");
		System.out.println(sb);
		//delete method
		sb.delete(1,5);
		System.out.println(sb);
		//reverse
		sb.reverse();
		System.out.println(sb);
		
		//capacity
		System.out.println(sb.capacity());
		
		sb.append("welcome to my java world");
		
		System.out.println(sb.capacity());
		
		System.out.println(sb);
		System.out.println(sb.charAt(3));
		System.out.println(sb.substring(4));
	     StringBuilder sb3 = new StringBuilder("Test");
	     StringBuilder sb4 = new StringBuilder("Test");
		System.out.println(sb4.equals(sb3));//false
	     StringBuilder sb5=sb4;
		
		System.out.println(sb5.equals(sb4));//true

	}

}
