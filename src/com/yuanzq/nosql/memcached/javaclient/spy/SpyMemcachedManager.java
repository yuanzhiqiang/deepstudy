package com.yuanzq.nosql.memcached.javaclient.spy;

import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketAddress;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.ConnectionObserver;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.transcoders.Transcoder;

public class SpyMemcachedManager {

	private List<SpyMemcachedServer> servers;

	private MemcachedClient memClient;

	public SpyMemcachedManager(List<SpyMemcachedServer> servers) {
		this.servers = servers;
	}

	public void connect() throws IOException {
		if (memClient != null) {
			return;
		}
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < servers.size(); i++) {
			SpyMemcachedServer server = servers.get(i);
			buf.append(server.toString()).append(" ");
		}
		memClient = new MemcachedClient(
				AddrUtil.getAddresses(buf.toString()));
	}

	public void disConnect() {
		if (memClient == null) {
			return;
		}
		memClient.shutdown();
	}
	
	public void addObserver(ConnectionObserver obs) {
		memClient.addObserver(obs);
	}
	
	public void removeObserver(ConnectionObserver obs) {
		memClient.removeObserver(obs);
	}
	
	//---- Basic Operation Start ----//
	public boolean set(String key, Object value, int expire) {
		Future<Boolean> f = memClient.set(key, expire, value);
		return getBooleanValue(f);
	}

	public Object get(String key) {
		return memClient.get(key);
	}

	public Object asyncGet(String key) {
		Object obj = null;
		Future<Object> f = memClient.asyncGet(key);
		try {
			obj = f.get(SpyMemcachedConstants.DEFAULT_TIMEOUT,
					SpyMemcachedConstants.DEFAULT_TIMEUNIT);
		} catch (Exception e) {
			f.cancel(false);
		}
		return obj;
	}

	public boolean add(String key, Object value, int expire) {
		Future<Boolean> f = memClient.add(key, expire, value);
		return getBooleanValue(f);
	}

	public boolean replace(String key, Object value, int expire) {
		Future<Boolean> f = memClient.replace(key, expire, value);
		return getBooleanValue(f);
	}

	public boolean delete(String key) {
		Future<Boolean> f = memClient.delete(key);
		return getBooleanValue(f);
	}

	public boolean flush() {
		Future<Boolean> f = memClient.flush();
		return getBooleanValue(f);
	}

	public Map<String, Object> getMulti(Collection<String> keys) {
		return memClient.getBulk(keys);
	}

	public Map<String, Object> getMulti(String[] keys) {
		return memClient.getBulk(keys);
	}

	public Map<String, Object> asyncGetMulti(Collection<String> keys) {
		Map map = null;
		Future<Map<String, Object>> f = memClient.asyncGetBulk(keys);
		try {
			map = f.get(SpyMemcachedConstants.DEFAULT_TIMEOUT,
					SpyMemcachedConstants.DEFAULT_TIMEUNIT);
		} catch (Exception e) {
			f.cancel(false);
		}
		return map;
	}

	public Map<String, Object> asyncGetMulti(String keys[]) {
		Map map = null;
		Future<Map<String, Object>> f = memClient.asyncGetBulk(keys);
		try {
			map = f.get(SpyMemcachedConstants.DEFAULT_TIMEOUT,
					SpyMemcachedConstants.DEFAULT_TIMEUNIT);
		} catch (Exception e) {
			f.cancel(false);
		}
		return map;
	}
	//---- Basic Operation End ----//

		
	//---- increment & decrement Start ----//
	public long increment(String key, int by, long defaultValue, int expire) {
		return memClient.incr(key, by, defaultValue, expire);
	}
	
	public long increment(String key, int by) {
		return memClient.incr(key, by);
	}
	
	public long decrement(String key, int by, long defaultValue, int expire) {
		return memClient.decr(key, by, defaultValue, expire);
	}
	
	public long decrement(String key, int by) {
		return memClient.decr(key, by);
	}
	
	public long asyncIncrement(String key, int by) {
		Future<Long> f = memClient.asyncIncr(key, by);
		return getLongValue(f);
	}
	
	public long asyncDecrement(String key, int by) {
		Future<Long> f = memClient.asyncDecr(key, by);
		return getLongValue(f);
	}
    //	---- increment & decrement End ----//
	
	public void printStats() throws IOException {
		printStats(null);
	}
	
	public void printStats(OutputStream stream) throws IOException {
		Map<SocketAddress, Map<String, String>> statMap = 
			memClient.getStats();
		if (stream == null) {
			stream = System.out;
		}
		StringBuffer buf = new StringBuffer();
		Set<SocketAddress> addrSet = statMap.keySet();
		Iterator<SocketAddress> iter = addrSet.iterator();
		while (iter.hasNext()) {
			SocketAddress addr = iter.next();
			buf.append(addr.toString() + "/n");
			Map<String, String> stat = statMap.get(addr);
			Set<String> keys = stat.keySet();
			Iterator<String> keyIter = keys.iterator();
			while (keyIter.hasNext()) {
				String key = keyIter.next();
				String value = stat.get(key);
				buf.append("  key=" + key + ";value=" + value + "/n");
			}
			buf.append("/n");
		}
		stream.write(buf.toString().getBytes());
		stream.flush();
	}
	
	public Transcoder getTranscoder() {
		return memClient.getTranscoder();
	}
	
	private long getLongValue(Future<Long> f) {
		try {
			Long l = f.get(SpyMemcachedConstants.DEFAULT_TIMEOUT,
					SpyMemcachedConstants.DEFAULT_TIMEUNIT);
			return l.longValue();
		} catch (Exception e) {
			f.cancel(false);
		}
		return -1;
	}

	private boolean getBooleanValue(Future<Boolean> f) {
		try {
			Boolean bool = f.get(SpyMemcachedConstants.DEFAULT_TIMEOUT,
					SpyMemcachedConstants.DEFAULT_TIMEUNIT);
			return bool.booleanValue();
		} catch (Exception e) {
			f.cancel(false);
			return false;
		}
	}
	
	/*public static void main(String[] args) {
		ExecutorService threadPool = Executors.newSingleThreadExecutor();
	   Future<String> f= threadPool.submit(new Callable<String>() {

			@Override
			public String call() throws Exception {
				// TODO Auto-generated method stub
				return null;
			}
		});
	   
	}*/

}