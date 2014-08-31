package com.yuanzq.thread.concurretinpractice.sector3;

import com.yuanzq.thread.concurrentinpractice.annotations.NotThreadSafe;

/**
 * MutableInteger
 * <p/>
 * Non-thread-safe mutable integer holder
 * 
 * @author Brian Goetz and Tim Peierls
 * 
 * 非线程安全的可变整数类
 * 
 */

@NotThreadSafe
public class MutableInteger {
	private int value;

	public int get() {
		return value;
	}

	public void set(int value) {
		this.value = value;
	}
}
