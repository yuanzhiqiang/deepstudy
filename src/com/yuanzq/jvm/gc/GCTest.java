package com.yuanzq.jvm.gc;

public class GCTest {

	private static final int _1MB = 1024 * 1024;
	
	
	/**
	 * desc:对象优先在Eden分配
	 * VM params:-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 
	 * -XX:+UseSerialGC
	 */
	public static void testAllocation(){
		byte[] allocation1, allocation2, allocation3, allocation4;
		allocation1 = new byte[2 * _1MB];
		allocation2 = new byte[2 * _1MB];
		allocation3 = new byte[2 * _1MB];
		allocation4 = new byte[4 * _1MB];//出现一次Minor GC
	}
	
	/**
	 * desc:大对象直接进入老年代
	 * VM params:-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8  
	 * -XX:PretenureSizeThreshold=2145728 -XX:+UseSerialGC
	 */
	public static void testPretenureSizeThreshold(){
		byte[] allocation;
		allocation = new byte[4 * _1MB];//出现一次Minor GC
	}
	
	
	/**
	 * desc:长期存活的对象将进入老年代
	 * VM params:-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8  
	 * -XX:MaxTenuringThreshold=1 -XX:+UseSerialGC
	 */
	public static void testTenuringThreshold(){
		byte[] allocation1, allocation2, allocation3;
		allocation1 = new byte[_1MB / 4];
		allocation2 = new byte[4 * _1MB];
		allocation3 = new byte[4 * _1MB];
		allocation3 = null;
		allocation3 = new byte[4 * _1MB];

	}
	
	/**
	 * desc:动态对象年龄判定
	 * VM params:-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8  
	 * -XX:MaxTenuringThreshold=15 -XX:+UseSerialGC
	 */
	public static void testTenuringThreshold2(){
		byte[] allocation1, allocation2, allocation3, allocation4;
		allocation1 = new byte[_1MB / 4];
		allocation2 = new byte[_1MB / 4];
		allocation3 = new byte[4 * _1MB];
		allocation4 = new byte[4 * _1MB];
		allocation4 = null;
		allocation4 = new byte[4 * _1MB];

	}

	/**
	 * desc:空间分配担保
	 * VM params:-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8  
	 * -XX:HandlePromotionFailure=false -XX:+UseSerialGC
	 */
	public static void testHandlePromotion(){
		byte[] allocation1, allocation2, allocation3, allocation4, allocation5, allocation6, allocation7;
		allocation1 = new byte[2 * _1MB];
		allocation2 = new byte[2 * _1MB];
		allocation3 = new byte[2 * _1MB];
		allocation1 = null;
		allocation4 = new byte[2 * _1MB];
		allocation5 = new byte[2 * _1MB];
		allocation6 = new byte[2 * _1MB];
		allocation4 = null;
		allocation5 = null;
		allocation6 = null;
		allocation7 = new byte[2 * _1MB];

	}

	public static void main(String[] args) {
//		testAllocation();
//		testPretenureSizeThreshold();
//		testTenuringThreshold();
//		testTenuringThreshold2();
		testHandlePromotion();
	}
}
