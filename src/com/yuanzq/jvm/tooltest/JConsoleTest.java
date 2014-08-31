package com.yuanzq.jvm.tooltest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;




/**
 * -verbose:gc -Xms100M -Xmx100M -XX:+PrintGCDetails -XX:+UseSerialGC
 * @author yuanzhiq
 *
 */
public class JConsoleTest {

	
	static class OOMObject{
		public byte[] placeholder = new byte[64 * 1024];
	}
	
	/**
	 * 填充堆内存，使用JConsole工具查看内存布局
	 * @param num
	 * @throws InterruptedException
	 */
	public static void fillHeap(int num) throws InterruptedException{
		List<OOMObject> list = new ArrayList();
		for(int i = 0; i < num; i++){
			Thread.sleep(50);
			list.add(new OOMObject());
		}
		System.gc();
	}
	
	/**
	 * 线程死循环演示
	 */
	public static void createBusyThread(){
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){}
			}
		}, "testBusyThread");
		thread.start();
	}
	
	public static void createLockedThread(final Object lock){
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				synchronized(lock){
					try {
						lock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}, "testLockThread");
		thread.start();
	}
	public static void main(String[] args) throws InterruptedException, IOException {
		fillHeap(1000);
		
		
		
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		br.readLine();//线程状态为runnable，runnable状态的线程会被分配运行时间，但readByte方法检查到流没有更新时会立刻归还执行令牌
//		createBusyThread();//线程状态为runnable，不会归还线程执行令牌，会在空循环运行全部时间直到线程切换，这种等待会消耗较多的CPU资源
//		br.readLine();
//		createLockedThread(new Object());//线程状态为WAITING状态，被唤醒前不会分配执行时间
		
		
		
		
//		new Thread(new SynAddRunnable(1,2)).start();
//		new Thread(new SynAddRunnable(2,1)).start();
	}
	
	
	static class SynAddRunnable implements Runnable{

		int a,b;
		
		SynAddRunnable(int a, int b){
			this.a = a;
			this.b = b;
		}
		@Override
		public void run() {
			synchronized(Integer.valueOf(a)){
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				synchronized(Integer.valueOf(b)){
					System.out.println(a + b);
				}
			}
		}
		
	}
}
