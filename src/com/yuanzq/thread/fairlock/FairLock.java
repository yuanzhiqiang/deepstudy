package com.yuanzq.thread.fairlock;

import java.util.ArrayList;



public class FairLock {
	
	Boolean lockFlag = true;
	private boolean isLocked = false;
	//private Lock currentLock = null;
	ArrayList<Lock> waitLocks = new ArrayList<Lock>();
	public void lock(){
		
		Lock lock = new Lock();
		synchronized(this){
			waitLocks.add(lock);
		}
		while(lockFlag){
			synchronized(this){
				lockFlag = isLocked || lock != waitLocks.get(0);
				if(!lockFlag){
					waitLocks.remove(0);
					lockFlag = true;
					return;
				}
			}
			try {
				lock.dowait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
	public void unlock(){
		
		isLocked = false;
		if(waitLocks.size() > 0){
			 waitLocks.get(0).donotify();
		}
	}
}