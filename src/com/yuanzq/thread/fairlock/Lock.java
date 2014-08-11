package com.yuanzq.thread.fairlock;

public class Lock {

	private static boolean signal = false;
	private int count;
	private Thread lockThread;

	public void dowait() throws InterruptedException {
		synchronized (this) {
			while (!signal && lockThread == Thread.currentThread()) {
				System.out.println("-----" + Thread.currentThread().getName()
						+ ":开始等待-------");
				wait();
			}
			System.out.println("------" + Thread.currentThread().getName()
					+ ":收到信号，执行完成-------");
			signal = false;
			lockThread = Thread.currentThread();
			count++;
		}
	}

	public void donotify() {
		synchronized (this) {
			signal = true;
			// lock.notify();
			if (lockThread == Thread.currentThread()) {
				count--;
				if (count == 0) {
					notify();
					System.out
							.println("------"
									+ Thread.currentThread().getName()
									+ ":发出信号-------");
				}
			}
		}
	}
}
