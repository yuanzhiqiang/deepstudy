package com.yuanzq.thread.langsin3;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Test_FutureTask {

	
	
	public static void main(String[] args) {
		
		Callable<Integer> func = new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				System.out.println("inside callable");
				Thread.sleep(1000);
				return new Integer(1);
			}
		};
		FutureTask<Integer> future = new FutureTask<Integer>(func);
		Thread newthread = new Thread(future);
		newthread.start();
		try {
			System.out.println("future returns:" + future.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
