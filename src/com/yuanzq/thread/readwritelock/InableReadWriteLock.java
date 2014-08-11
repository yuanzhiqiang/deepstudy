package com.yuanzq.thread.readwritelock;

import java.util.HashMap;
import java.util.Map;

/**
 * 可重入的读写锁
 * 
 * @author Administrator
 * 
 */
public class InableReadWriteLock {

	int readRequestCount;

	int readCount;

	int writeRequestCount;

	int writeCount;

	private Thread currentWriteLocksThread = null;

	private Map<Thread, Integer> readThread = new HashMap<Thread, Integer>();

	private boolean canAccessReadLock(Thread currentThread) {

		if (writeCount > 0) {
			return false;
		}

		if (isReader(currentThread)) {
			return true;
		}

		if (writeRequestCount > 0) {
			return false;
		}

		return true;
	}

	private boolean isReader(Thread callingThread) {
		return readThread.get(callingThread) != null;
	}

	public synchronized void lockRead() throws InterruptedException {
		readRequestCount++;
		Thread currentTrehad = Thread.currentThread();
		while (!canAccessReadLock(currentTrehad)) {
			wait();
		}
		readThread.put(currentTrehad, readThread.get(currentTrehad) == null ? 0
				: readThread.get(currentTrehad) + 1);
		readRequestCount--;
		readCount++;
	}

	public synchronized void unLockRead() {

		if (0 == readThread.get(Thread.currentThread())) {
			notifyAll();
		}

		readThread.put(Thread.currentThread(),
				readThread.get(Thread.currentThread()) - 1);
		if (readCount > 0) {
			readCount--;
		}
	}

	public synchronized void lockWrite() throws InterruptedException {
		writeRequestCount++;
		while (!canAccessWriteLock(Thread.currentThread())) {
			wait();
		}
		writeRequestCount--;
		writeCount++;
		currentWriteLocksThread = Thread.currentThread();
	}

	public synchronized void unLockWrite() {
		
		if(currentWriteLocksThread == Thread.currentThread()){
			if (writeCount > 0) {
				writeCount--;
			}
			if(writeCount == 0){
				currentWriteLocksThread = null;
			}
			notifyAll();
		}
	}

	private boolean canAccessWriteLock(Thread currentThread) {

		if (writeCount > 0) {
			return false;
		}

		if (writeRequestCount > 0) {
			return false;
		}

		if(null == currentWriteLocksThread){
			return true;
		}
		
		if (currentWriteLocksThread != currentThread) {
			return false;
		}
		return true;
	}
}
