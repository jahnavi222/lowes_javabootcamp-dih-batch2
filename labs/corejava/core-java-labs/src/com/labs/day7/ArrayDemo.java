package com.labs.day7;

public class ArrayDemo {

	public static void main(String[] args) {
		
		int numbers[] = new int[4];
		
		numbers[0]=10;
		numbers[1]=20;
		numbers[2]=30;
		numbers[3]=40;
		//numbers[4]=40; throws index out of bound exception
		for(int i =0;i<numbers.length;i++) {
			
			System.out.println(numbers[i]);
		}
		int a[]= {1,2,3,4,7,8,9};
		
		for(int numbers1:a) {
			System.out.println(numbers1);
			
		}

	}

}
