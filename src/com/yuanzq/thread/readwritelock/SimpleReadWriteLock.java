package com.yuanzq.thread.readwritelock;

public class SimpleReadWriteLock {

	
	int readRequestCount;
	
	int readCount;
	
	int writeRequestCount;
	
	int writeCount;
	
	
	
	public void lockRead() throws InterruptedException{
		readRequestCount++;
		synchronized(this){
			while(writeCount > 0 || writeRequestCount > 0){
				wait();
			}
		}
		readRequestCount--;
		readCount++;
	}
	
	public void unLockRead(){
		synchronized(this){
			notifyAll();
		}
		if(readCount > 0){
			readCount--;
		}
	}
	
	public void lockWrite() throws InterruptedException{
		writeRequestCount++;
		synchronized(this){
			while(readCount > 0 || writeCount > 0){
				wait();
			}
		}
		writeRequestCount--;
		writeCount++;
	}
	
	public void unLockWrite(){
		
		synchronized(this){
			notifyAll();
		}
		if(writeCount > 0){
			writeCount--;
		}
	}
}
