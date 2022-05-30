package com.labs.day11;

public class ThreadDemo2 implements Runnable {

	public static void main(String[] args) {
		ThreadDemo2 demo = new ThreadDemo2();
		// pass the runnable reference to Thread
		Thread t = new Thread(demo, "gfg");

		// start the thread
		t.start();

		// get the name of the thread
		System.out.println(t.getName());
		Thread demo1 = new Thread(new ThreadDemo2());
		demo1.setName("t1");
		demo1.start();

		Thread demo2 = new Thread(new Runnable() {

			public void run() {
				for (int i = 0; i < 100; i++) {
					System.out.println(Thread.currentThread().getName());
				}
			}

		});
		demo2.setName("t2");
		demo2.start();
	}

	@Override
	public void run() {
		System.out.println("Inside run method");

	}

}
