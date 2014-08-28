package com.yuanzq.thread.langsin1;

public class BadConcurrent implements Runnable {

	public BadConcurrent() {
		synchronized (BadConcurrent.class) {
			// ...
		}
	}

	// static{
	// try {
	// Thread.sleep(2000);aa
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	// //....do something
	// synchronized(BadConcurrent.class){
	//
	// }
	// }

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			BadConcurrent bc = new BadConcurrent();
			Thread t = new Thread(bc);
			t.start();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
