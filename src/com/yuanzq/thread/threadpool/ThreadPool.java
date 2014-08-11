package com.yuanzq.thread.threadpool;

import java.util.ArrayList;
import java.util.List;

import com.yuanzq.thread.blockingqueue.BlockingQueue;

public class ThreadPool {

	BlockingQueue<Runnable> queue = null;
	List<PoolThread> threads = new ArrayList<PoolThread>();
	ThreadPool(int noofThreads, int noOfMaxTasks){
		queue = new BlockingQueue<Runnable>(noOfMaxTasks);
		for(int i = 0; i < noofThreads; i++){
			PoolThread pt = new PoolThread(queue);
			threads.add(pt);
		}
		for(PoolThread pt : threads){
			pt.start();
		}
	}
	
	public synchronized void execute(Runnable runnble) throws InterruptedException{
		queue.enQueue(runnble);
	}
	
	public synchronized void stop(){
		for(PoolThread pt : threads){
			pt.toStoped();
		}

	}
	
}
