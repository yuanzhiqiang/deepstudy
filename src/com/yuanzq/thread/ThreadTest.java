package com.yuanzq.thread;

public class ThreadTest {

	public static void main(String[] args) throws InterruptedException {
		final RnTest test = new RnTest();
		new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					test.run();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		},"Thread-1").start();
		new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					test.run();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		},"Thread-2").start();
	}
	
}

class RnTest{
	
	
	public synchronized void run() throws Exception{
		System.out.println(Thread.currentThread().getName() + "进入");
		if(Thread.currentThread().getName().equals("Thread-1")){
			Thread.yield();
		}
		System.out.println("执行完毕");
	}
}