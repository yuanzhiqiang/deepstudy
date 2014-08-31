package com.yuanzq.thread.langsin2;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Test_ConcurrentHashMap {

	
	public static void main(String[] args) {
	
//		Map<String, String> myMap = new HashMap<String, String>();
		Map<String, String> myMap = new ConcurrentHashMap<String, String>();
		myMap.put("1", "1");
		myMap.put("2", "1");
		myMap.put("3", "1");
		myMap.put("4", "1");
		myMap.put("5", "1");
		myMap.put("6", "1");
		System.out.println("HashMap before iterator:" + myMap);
		Iterator<String> it = myMap.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			if(key.equals("3"))
				myMap.put(key + "new", "new3");
				
			System.out.println("HashMap after iterator" + myMap);
		}
	}
	
}
