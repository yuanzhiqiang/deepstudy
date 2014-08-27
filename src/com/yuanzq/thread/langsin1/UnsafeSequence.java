package com.yuanzq.thread.langsin1;

/**
 * 
 * @author yuanzhiq
 *String 
 */
public class UnsafeSequence {
	private int value;

	/**
	 * Returns a unique value.
	 */
	public int getNext() {
		return value++;
	}
}