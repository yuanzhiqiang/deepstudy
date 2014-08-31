package com.yuanzq.thread.langsin2;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Test_CopyOnWriteArrayList {

	public static void main(String[] args) {
//		ArrayList al = new ArrayList<>();
		List al = new CopyOnWriteArrayList<>();//内存拷贝
		//al.add("hello");
		al.add("world");
		Iterator it = al.iterator();
		while(it.hasNext()){
			al.remove(it.next());
		}
		System.out.println("list remain:" + al.size());
	}

}
