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
		//fillHeap(1000);
		
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();
		createBusyThread();
		br.readLine();
		createLockedThread(new Object());
		
	}
}
