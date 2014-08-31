package com.yuanzq.thread.concurretinpractice.sector5;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.yuanzq.thread.concurrentinpractice.annotations.GuardedBy;

/**
 * HiddenIterator
 * <p/>
 * Iteration hidden within string concatenation
 * 
 * @author Brian Goetz and Tim Peierls
 */
public class HiddenIterator {
	@GuardedBy("this")
	private final Set<Integer> set = new HashSet<Integer>();

	public synchronized void add(Integer i) {
		set.add(i);
	}

	public synchronized void remove(Integer i) {
		set.remove(i);
	}

	public void addTenThings() {
		Random r = new Random();
		for (int i = 0; i < 10; i++)
			add(r.nextInt());

		// 存在抛出ConcurrentModifuacationException的风险，标准容器的toString(),
		//hashCode,equels,contaisAll,removeAll,retains等方法都可能会遍历容器类
		System.out.println("DEBUG: added ten elements to " + set);
	}

	public static void main(String[] args) {
		HiddenIterator hi = new HiddenIterator();
		hi.addTenThings();
	}
}
