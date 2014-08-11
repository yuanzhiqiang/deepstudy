package com.yuanzq.nio.buffer;

import java.nio.CharBuffer;


public class BufferTest {
	
	
	public static void main(String[] args) {
		
		/**
		 * 基本使用
		 */
//		CharBuffer cb = CharBuffer.allocate(16);
//		cb.put("yuanzhiqiang");
//		cb.flip();
//		while(cb.hasRemaining()){
//			System.out.print(cb.get());
//		}
		/**
		 *  创建
		 */
//		CharBuffer cb = CharBuffer.allocate(16);
		char[] array = new char[100];
		CharBuffer cb = CharBuffer.wrap(array);
	}
	
	
	
	
}
