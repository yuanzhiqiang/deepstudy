package com.yuanzq.thread.langsin1;

import java.util.HashSet;
import java.util.Set;



/**
 * @desc 对象共享：栈线程局限、ThreadLocal、不可变对象
 * @author yuanzhiq
 * @commet 共享对象根本原则:除非必要共享，否则全部声明private
 *				除非必要改变，否则全部声明不可变（final） 
 *	如果一个对象需要被共享，经验就是：
		从一个静态初始化函数初始化对象
		将这个对象的引用放到一个volatile或AtomicRefrence的区域
		将对这个对象的引用放入一个final 或是加锁区域
	
	可以考虑的线程共享方式：
		线程局限
		只读共享
		线程安全共享
		Lock
 */
public class ObjectShare {

	private String[] states = new String[]{"aaa","bbb"};  
	
	//不安全的方式
//	public String[] getState(){
//		return states;
//	} 
	
	//栈线程局限
	public String getState(int i){
		return states[i];
	}
	
	//不可变对象
	private final Set<String> stooges = new HashSet<String>();
	
	public ObjectShare(){
        stooges.add("Moe");
        stooges.add("Larry");
        stooges.add("Curly");
	}
	
	public boolean isStooge(String name) {
        return stooges.contains(name);
    }
}
