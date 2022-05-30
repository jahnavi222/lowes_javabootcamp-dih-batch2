package com.labs.day11;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CreateThreadEx implements Callable<String> {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		CreateThreadEx ex = new CreateThreadEx();

		ExecutorService exs = Executors.newFixedThreadPool(5);
		
		Future<String> future = exs.submit(ex);

		System.out.println(future.get());

		Future<String> future1 = exs.submit(new CreateThreadEx());
		System.out.println(future1.get());

		Future<String> future2 = exs.submit(new Callable<String>() {

			@Override
			public String call() throws Exception {
				for (int i = 0; i < 100; i++) {
					System.out.println(Thread.currentThread().getName() + "" + i);
				}
				return Thread.currentThread().getName() + "Thread execution completed";
			}

		});
		System.out.println(future2.get());
		exs.shutdown();
	}

	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		for (int i = 0; i < 100; i++) {
			System.out.println(Thread.currentThread().getName() + "" + i);
		}
		return Thread.currentThread().getName() + "Thread execution completed";
	}

}
