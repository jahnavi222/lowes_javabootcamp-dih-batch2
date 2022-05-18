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
		
		
		System.out.println(sb.capacity());
		
		sb.append("welcome to my java world");
		
		System.out.println(sb.capacity());
		
		System.out.println(sb);
		System.out.println(sb.charAt(3));
		System.out.println(sb.substring(4));

	}

}
