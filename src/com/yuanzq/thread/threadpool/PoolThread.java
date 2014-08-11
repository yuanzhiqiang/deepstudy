package com.yuanzq.thread.threadpool;

import com.yuanzq.thread.blockingqueue.BlockingQueue;

public class PoolThread extends Thread{

	private boolean isStoped = false;
	private BlockingQueue<Runnable> queue = null;
	
	PoolThread(BlockingQueue<Runnable> queue){
		this.queue = queue;
	}
	
	@Override
	public void run() {
		while(!isStoped){
			try {
				Runnable runnable = queue.deQuery();
				runnable.run();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
	}
	
	public synchronized void toStoped(){
		isStoped = true;
		this.interrupt();
	}
}
