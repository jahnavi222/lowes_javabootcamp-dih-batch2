package com.labs.day7;

public class PerformanceTest {

	public static void main(String[] args) {

		System.out.println("Performance Test");

		StringBuffer sb = new StringBuffer("Hello");

		long startTime = System.currentTimeMillis();

		for (int i = 0; i < 1000000; i++) {
			sb.append("World");

		}

		System.out.println("StringBuff time: " + (System.currentTimeMillis() - startTime));

		StringBuilder sb1 = new StringBuilder("Hello");

		startTime = System.currentTimeMillis();

		for (int i = 0; i < 1000000; i++) {
			sb1.append("World");

		}
		// TODO Auto-generated method stub
		System.out.println("StringBuilder time: " + (System.currentTimeMillis() - startTime));
	}

}
