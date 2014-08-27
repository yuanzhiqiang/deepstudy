package com.yuanzq.thread.langsin1;

public class SimpleTest {

	static class Simple  extends Thread{
		@Override
		public void run() {
			while(true){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getId() + "--running");
			}
		}
	}
	public static void main(String[] args) {
		for(int i = 0; i < 10; i++){
			Simple s = new Simple();
			s.start();
		}
	}

}
