package com.yuanzq.thread.blockingqueue;

import java.util.LinkedList;

public class BlockingQueue<E> {

	
	private  LinkedList<E> query = new LinkedList<E>();
	
	private int limit = 10;;
	
	public BlockingQueue(int limit){
		this.limit = limit;
	}
	
	public synchronized void enQueue(E object) throws InterruptedException{
		while(query.size() == limit){
			this.wait();
		}
		if(query.size() == 0){
			notifyAll();
		}
		query.add(object);
	}
	
	public synchronized E deQuery() throws InterruptedException{
		while(query.size() == 0){
			this.wait();
		}
		if(query.size() == limit){
			notifyAll();
		}
		return query.remove(0);
	}
}
