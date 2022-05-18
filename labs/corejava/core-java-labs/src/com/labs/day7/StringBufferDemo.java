package com.labs.day7;

public class StringBufferDemo {

	public static void main(String[] args) {
		
		StringBuffer sb = new  StringBuffer("Hello");
		
		sb.append("World");
		System.out.println(sb);
		
		sb.insert(1, "Test");
		System.out.println(sb);
		
		sb.delete(1,5);
		System.out.println(sb);
		
		sb.reverse();
		System.out.println(sb);
		
		
		System.out.println(sb.capacity());
		
		sb.append("welcome to my java world");
		
		System.out.println(sb.capacity());
		
		System.out.println(sb);
		System.out.println(sb.charAt(3));
	}
	
	

}
