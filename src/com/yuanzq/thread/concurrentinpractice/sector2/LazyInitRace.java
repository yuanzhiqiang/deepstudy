package com.yuanzq.thread.concurretinpractice.sector2;

import com.yuanzq.thread.concurrentinpractice.annotations.NotThreadSafe;

/**
 * LazyInitRace
 * 
 * Race condition in lazy initialization
 * @desc 先检查后执行
 * @author Brian Goetz and Tim Peierls
 */

@NotThreadSafe
public class LazyInitRace {
	private ExpensiveObject instance = null;

	public ExpensiveObject getInstance() {
		if (instance == null)
			instance = new ExpensiveObject();
		return instance;
	}
}

class ExpensiveObject {
}
