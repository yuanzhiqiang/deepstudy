package com.yuanzq.thread.langsin2;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test_Executor {

	
	public static void main(String[] args) {
		Executor executor = Executors.newFixedThreadPool(10);
		
		Runnable task = new Runnable(){

			@Override
			public void run() {
				System.out.println("task " + Thread.currentThread().getId() +"start" + System.currentTimeMillis());
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		};
		
		executor.execute(task);
		ExecutorService service = (ExecutorService) executor;
		service.shutdown();
		
	}
}
