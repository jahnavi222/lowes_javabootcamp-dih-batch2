package com.labs.day8;

public class TypeCastDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// implicit conversion
		int a = 10;
		long b = a; // automatically converts the integer type into long type
		float c = b; // automatically convert the long into float type
		
		System.out.println("a:"+a+" "+"b:"+b+" "+"c:"+c);

		// expilicit conversion
		double a1 = 12.45;
		long b1 = (long) a; // converting double into long type explicitly
		int c1 = (int) b; // converting long type into int explicitly
		System.out.println("a1:"+a1+" "+"b1:"+b1+" "+"c1:"+c1);
		
		//converting integer into int
		Integer a2=100;
	    int b2 = a2.intValue();
	    int c2 = a2;
       System.out.println(a2 + " " + b2 + " " + c2);
       
    // converting all primitive type into its wrapper class
       byte a4 = 10;
       short b4 = 20;
       int c4 = 30;
       long d = 40;
       float e = 12.3f;
       double f = 12.23;
       char g = 'c';
       boolean h = true;

       // autoboxing : converting primitive into object
       Byte byteObj = a4;
       Short shortObj = b4;
       Byte chh=(byte) g;
       // unboxing : converting object into primitive type
       byte byteValue = byteObj;
       short shortValue = shortObj;
      
	}
}
