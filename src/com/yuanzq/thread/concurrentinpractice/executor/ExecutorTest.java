package com.yuanzq.thread.concurrentinpractice.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

/**
 * 线程池
 * 
 * @author Administrator
 * 
 */
public class ExecutorTest {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) throws InterruptedException {

		Executor cachedThreadPool = Executors.newCachedThreadPool();
		cachedThreadPool.execute(new Runnable() {

			@Override
			public void run() {
				System.out.println("cachedThreadPool");
			}

		});

		Executor fixedThreadPool = Executors.newFixedThreadPool(2);
		fixedThreadPool.execute(new Runnable() {
			@Override
			public void run() {
				System.out.println("fixedThreadPool");
			}
		});
		
		Executor singleThread = Executors.newSingleThreadExecutor();
		singleThread.execute(new Runnable() {
			@Override
			public void run() {
				System.out.println("singleThread");
			}
		});
		
		Executor scheduledThreadPool = Executors.newScheduledThreadPool(2);
		scheduledThreadPool.execute(new Runnable() {
			@Override
			public void run() {
				System.out.println("scheduledThreadPool");
			}
		});
		
		CompletionService completionService = new ExecutorCompletionService(Executors.newCachedThreadPool());
		completionService.submit(new Callable() {
			@Override
			public Object call() {
				System.out.println("1");
				return "1";
			}
		});
		completionService.submit(new Callable() {
			@Override
			public Object call() {
				System.out.println("2");
				return "2";
			}
		});
		completionService.take();
	}
}
