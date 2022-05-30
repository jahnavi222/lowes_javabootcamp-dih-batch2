package com.labs.day11;

public class ThreadDemo extends Thread {
	
	public void run() {
		for(int i=0;i<100;i++) {
			System.out.println(Thread.currentThread().getName());
		}
	
	}

	public static void main(String[] args) {
		/*System.out.println("Hello world");;
		System.out.println(Thread.currentThread().getId());
		System.out.println(Thread.currentThread().getName());
		System.out.println(Thread.currentThread().getPriority());
		System.out.println(Thread.currentThread().getState().name());
		System.out.println(Thread.currentThread().isAlive());
		System.out.println(Thread.currentThread().isDaemon());*/
		ThreadDemo dem0 = new ThreadDemo();
		System.out.println(Thread.currentThread().getName());
		dem0.setName("t1");
		dem0.start();
		ThreadDemo dem02 = new ThreadDemo();
		System.out.println(Thread.currentThread().getName());
		dem0.setName("t2");
		dem02.start();
	}

}
