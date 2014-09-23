package com.yuanzq.thread.langsin3;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

public class Test_CompletionService {

	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Executor executor = Executors.newFixedThreadPool(10);
		Callable<Integer> task = new Callable<Integer>(){

			@Override
			public Integer call() throws Exception {
				System.out.println("task:" + Thread.currentThread() + " start");
				Thread.sleep(2000);
				System.out.println("task" + Thread.currentThread()+ " end");
				return 100;
			}
			
		};
		
		
		Runnable task2 = new Runnable() {
			
			@Override
			public void run() {
				System.out.println("task:" + Thread.currentThread() + " start");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("task" + Thread.currentThread()+ " end");
			}
		};
		
		
		CompletionService<Integer> service = new ExecutorCompletionService<Integer>(executor);
		
		service.submit(task);
		service.submit(task2,200);
		
		System.out.println(service.take().get());
		System.out.println(service.take().get());
	}
}
