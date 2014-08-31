package com.yuanzq.thread.langsin2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static java.lang.System.*;

public class Test_CollectionSync {

	static List al = new ArrayList<>(100); 
	
	public static void main(String[] args) throws InterruptedException {
		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i < 100; i++){
					al.add(al.size(), new Integer(i));
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		});
		
		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i < 100; i++){
					al.add(al.size(), new Integer(i));
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		});
//		List list = new ArrayList();
//		List syncedlist = Collections.synchronizedList(list);
		thread1.start();
		thread2.start();
		thread1.join();
		thread2.join();
		out.println("ArrayList is :" + al);
	}

}
