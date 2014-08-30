package com.yuanzq.thread.langsin1;

/**
 * @desc this对象逃逸
 * @author yuanzhiq
 * 
 */
public class ThisEscape {

	public ThisEscape() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				ThisEscape.this.print();
			}

		}).start();
	}

	public void print() {
		System.out.println("I am a static method that blong to this class");
	}

}
