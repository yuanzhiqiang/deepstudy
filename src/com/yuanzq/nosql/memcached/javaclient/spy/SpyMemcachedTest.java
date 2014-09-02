package com.yuanzq.nosql.memcached.javaclient.spy;

import java.io.IOException;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.TestCase;
import net.spy.memcached.ConnectionObserver;
import net.spy.memcached.transcoders.Transcoder;

public class SpyMemcachedTest extends TestCase {

	private SpyMemcachedManager manager;

	protected void setUp() throws Exception {
		super.setUp();
		String[][] servs = new String[][] {
				{"localhost", "11211"},
//			    {"localhost", "11212"}
				
		};
		List<SpyMemcachedServer> servers = new ArrayList<SpyMemcachedServer>();
		for (int i = 0; i < servs.length; i++) {
			SpyMemcachedServer server = new SpyMemcachedServer();
			server.setIp(servs[i][0]);
			server.setPort(Integer.parseInt(servs[i][1]));
			servers.add(server);
		}
		manager = new SpyMemcachedManager(servers);
		manager.connect();
		addObserver();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		manager.disConnect();
	}

	public void testSet() {
		System.out.println("====TestSet====");
		for (int i = 0; i < 10; i++) {
			String key = "key" + i;
			String value = "value" + i;
			manager.set(key, value, 200000);
		}
	}
	
	public void testGet() {
		System.out.println("====TestGet====");
        for (int i = 0; i < 10; i++) {
        	String key = "key" + i;
        	Object value = manager.get(key);
        	if (value != null) {
        		System.out.println("From memcached");
        		System.out.println("key=" + key + ";value=" + value);
        	} else {
        		System.out.println("Not found");
        		System.out.println("key=" + key + ";value=" + value);
        	}
        }
	}

	public void testAdd() {
		System.out.println("====TestAdd====");
		boolean flag = manager.add("key1", "value1-added", 20); //exist
		assertEquals(false, flag);
		flag = manager.add("key100", "value100", 20); // don't exist
		assertEquals(true, flag);
		testGet();
	}

	public void testReplace() {
		System.out.println("====TestReplace====");
		boolean flag = manager.replace("key2", "value2-replaced", 20);
		assertEquals(true, flag);
		flag = manager.replace("key1000", "value1000", 20);
		assertEquals(false, flag);
		testGet();
	}

	public void testDelete() {
		System.out.println("====TestDelete====");
		boolean flag = manager.delete("key3");
		assertEquals(true, flag);
		flag = manager.delete("key1000");
		assertEquals(false, flag);
		testGet();
	}
	
	public void testAsyncGet() {
		System.out.println("====TestAsyncGet====");
		Object value = manager.asyncGet("key4");
		if (value != null) {
			assertEquals("value4", (String)value);
		}
		System.out.println("value=" + value);
	}
	
	public void testGetMulti() {
		System.out.println("====TestGetMulti====");
		List<String> keys = new ArrayList<String>();
		String[] strKeys = new String[5];
		for (int i = 0; i < 5; i++) {
			keys.add("key" + i);
			strKeys[i] = "key" + i;
		}
		Map<String, Object> cache = manager.getMulti(keys);
		printMap(cache);
		
		cache = manager.getMulti(keys);
		printMap(cache);
	}
	
	public void testAsyncGetMulti() {
		System.out.println("====TestAsyncGetMulti====");
		List<String> keys = new ArrayList<String>();
		String[] strKeys = new String[5];
		for (int i = 0; i < 5; i++) {
			keys.add("key" + i);
			strKeys[i] = "key" + i;
		}
		Map<String, Object> cache = manager.asyncGetMulti(keys);
		printMap(cache);
		
		cache = manager.asyncGetMulti(keys);
		printMap(cache);
	}
	
		
	public void testIncrAndDecr() {
		System.out.println("====TestIncrAndDecr====");
		long l = -1;
		l = manager.increment("incr", 2, 100, 60);
		assertEquals(100, l);
		l = manager.increment("incr", 4);
		assertEquals(104, l);
		l = manager.decrement("decr", 4, 100, 60);
		assertEquals(100, l);
		l = manager.decrement("decr", 3);
		assertEquals(97, l);
		System.out.println("incr=" + manager.get("incr").toString());
		System.out.println("decr=" + manager.get("decr").toString());
	}
	
	public void testAsyncIncrAndDecr() {
		System.out.println("====TestAsyncIncrAndDecr====");
		long l = -1;
		l = manager.asyncIncrement("incr", 2);
		assertEquals(106, l);
		l = manager.asyncDecrement("decr", 4);
		assertEquals(93, l);
		System.out.println("aincr=" + manager.get("incr").toString());
		System.out.println("adecr=" + manager.get("decr").toString());
	}
	
	public void testPrintStat() throws IOException {
		System.out.println("====TestPrintStat====");
		manager.printStats();
	}
	
	private void addObserver() {
		//System.out.println("====AddObserver====");
		ConnectionObserver obs = new ConnectionObserver() {
			public void connectionEstablished(SocketAddress sa, int reconnectCount) {
				System.out.println("Established " + sa.toString());
			}

			public void connectionLost(SocketAddress sa) {
				System.out.println("Lost " + sa.toString());
			}
		};
		manager.addObserver(obs);
	}
	
	public void testGetTranscoder() {
		System.out.println("====TestGetTranscoder====");
		Transcoder tran = manager.getTranscoder();
		System.out.println(tran.getClass().toString());
	}
	
	private void printMap(Map map) {
		StringBuffer temp = new StringBuffer();
    	Set set = map.keySet();
    	Iterator iter = set.iterator();
    	while(iter.hasNext()) {
    		String key = (String)iter.next();
    		Object value = map.get(key);
    		temp.append("key=" + key + ";value=" + value + "/n");
    	}
    	System.out.println(temp.toString());
	}
}
