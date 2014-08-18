package com.yuanzq.jvm.gc;

public class ClassCinit {

	static{
		if(true){
			System.out.println("线程：" + Thread.currentThread().getName() + "先到达");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				ClassCinit cc = new ClassCinit();
			}
		};
		Thread t1 = new Thread(r);
		t1.setName("线程1");
		Thread t2 = new Thread(r);
		t2.setName("线程2");
		t1.start();t2.start();
		
	}
}
