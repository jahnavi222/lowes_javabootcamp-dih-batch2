package com.labs.day6;

import java.util.Arrays;

public class StringDemo {

	public static void main(String[] args) {
	   char ch[] = { 'H', 'e', 'l', 'l', 'o' };

        String s24 = new String(ch);  // converting char array to string
        System.out.println(s24);
		String s1 = new String("MARK");
		String s2="MARK";
		String s3 = new String("MARK");
		String s4="PAUL";
		String s5="mark";
		String s6="MARK";
		System.out.println(s1.equals(s2));//true
		System.out.println(s1==(s2));//false
		System.out.println(s1==s5);//false
		System.out.println(s1==s3);//false
		System.out.println(s2==s6);//true
		System.out.println(s5.equals(s6));//false
		System.out.println(s5.equalsIgnoreCase(s6));//true
		System.out.println(s5.equals(s4));//false
		
		//String concatenation
		System.out.println(s1.concat(s6));
		System.out.println(s1.concat(s3));
		System.out.println(s1.concat(s4) +" test");
		String str = "Hello" + "World";
        System.out.println(str); // HelloWorld
		String str2 = 10 + 20 + 30 + "Sum" + 40 + 50 + true;
		 System.out.println(str2);//60Sum4050true
		String str1 = "Hello";
        str1.concat("World"); // we are using concat() method to append the string at the end
        System.out.println(str1);  // will print hello onl;y bcoz strings are immutable object

        String str3 = "Hello";
        str3 = str3.concat("world");
        System.out.println(str3);
        
        //Compare Method
        System.out.println(s1.compareTo(s6));//returns 0 because s1 == s2
        
        System.out.println(s1.compareTo(s4)); //-3 because m is 3 times lower then p
        System.out.println(s4.compareTo(s1));//3 because m is 3 times greater then p
        
        //substring operations
        String message = "Welcome to java training";
        System.out.println(message);
        System.out.println(message.substring(0));
        System.out.println(message.substring(0,6));
        System.out.println(message.substring(6));
        String str44 = new String("Welcome home");
        System.out.println(str44);
        System.out.println(str44.substring(8));
        
        //split method
        String message1 = "mark@gmail.com";
        String[] test = message1.split("@");
        System.out.println(Arrays.toString(test));
        //CharAt method
        String message2 ="welcome to java world";
        System.out.println(message2.charAt(8));
        //indexof method to fine first occurance 
        System.out.println(message2.indexOf('l'));
        System.out.println(message2.indexOf("to"));
        System.out.println(message2.indexOf('e', 2));
        
        //lastIndexof method used to find the last occurance
        System.out.println(message2.lastIndexOf('t'));
	}

}
