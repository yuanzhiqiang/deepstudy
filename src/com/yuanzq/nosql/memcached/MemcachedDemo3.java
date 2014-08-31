package com.yuanzq.nosql.memcached;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

public class MemcachedDemo3 {

	public static void main(String[] args) {
		MemCachedClient client = new MemCachedClient();
		String[] addr = {"127.0.0.1:11212"};
		Integer[] weights = {3};
		SockIOPool pool = SockIOPool.getInstance();
		pool.setServers(addr);
		pool.setWeights(weights);
		pool.setInitConn(5);
		pool.setMinConn(5);
		pool.setMaxConn(200);
		pool.setMaxIdle(100 * 30 * 30);
		pool.setMaintSleep(30);
		
		//socket param timeout....
		pool.setNagle(false);
		pool.setSocketTO(30);
		pool.setSocketConnectTO(0);
		pool.initialize();
		Student stu1 = new Student();
		stu1.setAge(20);
		stu1.setName("java");
//		client.set("stu1", stu1);
		System.out.println(client.get("stu1"));
	}

}
