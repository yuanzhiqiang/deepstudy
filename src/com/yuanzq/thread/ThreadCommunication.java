package com.yuanzq.thread;

public class ThreadCommunication {

	static Object lock = new Object();
	private static boolean signal = false;

	public void dowait() throws InterruptedException {
		synchronized (lock) {
			while (!signal) {
				System.out.println("-----" + Thread.currentThread().getName() + ":开始等待-------");
				lock.wait();
			}
			System.out.println("------" + Thread.currentThread().getName() + ":收到信号，执行完成-------");
			signal = false;
		}
	}

	public void donotify() {
		synchronized (lock) {
			signal = true;
			//lock.notify();
			lock.notifyAll();
			System.out.println("------" + Thread.currentThread().getName() + ":发出信号-------");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		new Thread(new Runnable(){

			@Override
			public void run() {
				ThreadCommunication tc = new ThreadCommunication();
				try {
					tc.dowait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}).start();
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				ThreadCommunication tc = new ThreadCommunication();
				try {
					tc.dowait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}).start();
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				ThreadCommunication tc = new ThreadCommunication();
				try {
					tc.dowait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}).start();
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				ThreadCommunication tc = new ThreadCommunication();
				try {
					tc.dowait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}).start();
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				ThreadCommunication tc = new ThreadCommunication();
				tc.donotify();
			}
			
		}).start();
	}
}
