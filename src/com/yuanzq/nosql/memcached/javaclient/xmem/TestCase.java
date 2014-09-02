package com.yuanzq.nosql.memcached.javaclient.xmem;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.Counter;
import net.rubyeye.xmemcached.GetsResponse;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.auth.AuthInfo;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.transcoders.StringTranscoder;
import net.rubyeye.xmemcached.utils.AddrUtil;

public class TestCase {
	public static void main(String[] args)throws IOException {
		test1();
	}
	public static void test1() throws IOException {
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(
				AddrUtil.getAddresses("localhost:11211"));
		 //AddrUtil.getAddresses("localhost:11211 localhost:11212");

		builder.setFailureMode(true);

		builder.setCommandFactory(new BinaryCommandFactory());

		builder.setConnectionPoolSize(10);
		MemcachedClient client = builder.build();
		try {

			/*client.set("hello", 0, "Hello,xmemcached");
			String value = client.get("hello");
			System.out.println("hello=" + value);*/
			/*for(int i = 0 ; i < 10;i++){
				client.set("key"+i, 0, "value"+i);
			}*/
			for(int i = 0; i < 10;i++){
				System.out.println("key"+i+"="+client.get("key"+i));
			}
			
			//client.delete("hello");
			//value = client.get("hello");
			//System.out.println("hello=" + value);

			// value=client.get(“hello”,3000);

			/*GetsResponse<Integer> result = client.gets("a");
			long cas = result.getCas();
			if (!client.cas("a", 0, 2, cas)) {
				System.err.println("cas error");
			}*/
		} catch (MemcachedException e) {
			System.err.println("MemcachedClient operation fail");
			e.printStackTrace();
		} catch (TimeoutException e) {
			System.err.println("MemcachedClient operation timeout");
			e.printStackTrace();
		} catch (InterruptedException e) {
			// ignore
		}
		try {
			// close memcached client
			client.shutdown();
		} catch (IOException e) {
			System.err.println("Shutdown MemcachedClient fail");
			e.printStackTrace();
		}

	}

	public void test2() throws TimeoutException, InterruptedException,
			MemcachedException, IOException {
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(
				AddrUtil.getAddresses("localhost:11211"));
		MemcachedClient client = builder.build();
		client.flushAll();
		if (!client.set("hello", 0, "world")) {
			System.err.println("set error");
		}
		if (client.add("hello", 0, "dennis")) {
			System.err.println("Add error,key is existed");
		}
		if (!client.replace("hello", 0, "dennis")) {
			System.err.println("replace error");
		}
		client.append("hello", " good");
		client.prepend("hello", "hello ");
		String name = client.get("hello", new StringTranscoder());
		System.out.println(name);

		client.deleteWithNoReply("hello");
	}

	public void incrDecr() throws IOException, TimeoutException,
			InterruptedException, MemcachedException {
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(
				AddrUtil.getAddresses("localhost:11211"));
		MemcachedClient client = builder.build();

		System.out.println(1 == client.incr("a", 5, 1));
		System.out.println (6 == client.incr("a", 5));
		System.out.println (10 == client.incr("a", 4));
		System.out.println (9 == client.decr("a", 1));
		System.out.println (7 == client.decr("a", 2));
	}

	public void counter() throws Exception {
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(
				AddrUtil.getAddresses("localhost:11211"));
		MemcachedClient client = builder.build();
		Counter counter = client.getCounter("counter", 0);
		counter.incrementAndGet();
		counter.decrementAndGet();
		counter.addAndGet(-10);
	}

	public void auth() throws Exception {
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(
				AddrUtil.getAddresses("localhost:11211"));
		builder.addAuthInfo(AddrUtil.getOneAddress("localhost:11211"),
				AuthInfo.typical("cacheuser", "123456"));
		// Must use binary protocol
		builder.setCommandFactory(new BinaryCommandFactory());
		MemcachedClient client = builder.build();
	}

	public void nioPool() throws Exception {
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(
				AddrUtil.getAddresses("localhost:11211"));
		builder.setConnectionPoolSize(5);
	}

	public void testGet() throws IOException, TimeoutException,
			InterruptedException, MemcachedException {
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(
				AddrUtil.getAddresses("localhost:11212"));
		MemcachedClient client = builder.build();
		String value = client.get("1");
		System.out.println("hello=" + value);
	}

	/*
	 * public void testGet2() throws IOException, TimeoutException,
	 * InterruptedException, MemcachedException{ MemcachedClientBuilder builder
	 * = new XMemcachedClientBuilder( AddrUtil.getAddresses("localhost:11212"));
	 * MemcachedClient client = builder.build(); String value =
	 * client.get("srp_"+MD5Util.MD5("3rdsearch_周杰伦"));
	 * System.out.println(value); }
	 */
}
