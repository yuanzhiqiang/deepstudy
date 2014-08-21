package com.yuanzq.socket.basicencode;

public class BasicDataEncoding {

	
	private static int BYTESIZE = Byte.SIZE / Byte.SIZE;   //一个字节
	private static int SHORTSIZE = Short.SIZE / Byte.SIZE; //两个字节
	private static int INTSIZE = Integer.SIZE / Byte.SIZE; //四个字节
	private static int LONGSIZE = Long.SIZE / Byte.SIZE;   //八个字节
	
	
	public int encodeBasicBigEndian(byte[] des, Long value, int offset, int size){
		
		for(int i = 0; i < size; i++){
			des[offset++] = (byte)(value >> ((size - i -1) * Byte.SIZE)); 
		}
		return offset;
	}
	
	public long decodeBasicBigEndian(byte[] des, int offset, int size){
		long val = 0;
		for(int i = 0; i < size; i++){
			val = val <<  Byte.SIZE |  des[offset++];
		}
		return offset;
	}
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		byte[] basicByteArr = new byte[BYTESIZE + SHORTSIZE + INTSIZE + LONGSIZE];
		
	}

}
