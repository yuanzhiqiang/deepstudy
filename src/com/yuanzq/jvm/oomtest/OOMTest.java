package com.yuanzq.jvm.oomtest;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class OOMTest {

	
	/**
	 * desc堆内存溢出
	 * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
	 */
	public static void HeapOOM(){
		List list = new ArrayList();
		while(true){
			list.add(new OOMTest());
		}
	}
	
	/**
	 * desc栈内存溢出
	 * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
	 */
	static int i;
	public static void StackOOM(){
		i++;
		StackOOM();
	}
	
	/**
	 * desc 运行时常量池溢出
	 * VM参数：-XX:PermSize=10M -XX:MaxPermSize=10M
	 */
	public static void ConstantPoolOOM(){
		List list = new ArrayList();
		int i = 0;
		while(true){
			i++;
			list.add(String.valueOf(i).intern());
		}
	}

	/**
	 * desc:方法区溢出
	 * @param args
	 */
	public static void methodAreaOOM(){
		
	}
	
	/**
	 * 直接内存溢出
	 * @param args
	 */
	public static void DirectMemoryOOM(){
		List list = new ArrayList();
		while(true){
			ByteBuffer bb = ByteBuffer.allocateDirect(1024);
			list.add(bb);
		}
	}
	public static void main(String[] args) {
//		HeapOOM();
//		StackOOM();
//		ConstantPoolOOM();
		DirectMemoryOOM();
	}
}
