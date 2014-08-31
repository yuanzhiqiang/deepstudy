package com.yuanzq.nosql.memcached;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

public class MemcachedDemo1 {

	public static void main(String[] args) {
		MemCachedClient client = new MemCachedClient();
		String[] addr = {"127.0.0.1:11211"};
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
		client.set("test1", "test1");
		System.out.println(client.get("test1"));
	}

}
