package com.yuanzq.socket.basicencode;



/**
 * 基本数据类型的编码
 * @author yuanzhiq
 *
 */
public class BasicDataEncoding {

	
	private static int BYTESIZE = Byte.SIZE / Byte.SIZE;   //一个字节
	private static int SHORTSIZE = Short.SIZE / Byte.SIZE; //两个字节
	private static int INTSIZE = Integer.SIZE / Byte.SIZE; //四个字节
	private static int LONGSIZE = Long.SIZE / Byte.SIZE;   //八个字节
	
	private final static int BYTEMASK = 0xFF; // 8 bits
	
	private static byte byteVal = 101; // one hundred and one
	private static short shortVal = 10001; // ten thousand and one
	private static int intVal = 100000001; // one hundred million and one
	private static long longVal = 1000000000001L;// one trillion and one

	public static int encodeBasicBigEndian(byte[] des, long value, int offset, int size){
		
		for(int i = 0; i < size; i++){
			des[offset++] = (byte)(value >> ((size - i -1) * Byte.SIZE)); 
		}
		return offset;
	}
	
	public static long decodeBasicBigEndian(byte[] des, int offset, int size){
		long val = 0;
		for(int i = 0; i < size; i++){
			byte temp = (byte) (des[offset] & BYTEMASK);
			/**
			 * 稍微解释一下：
    			(byte) -42 & 0xff 
			      STEP01：(byte)-42 进行扩展：
			      11111111 11111111 11111111  11010110
			    STEP02：与运算：
			      00000000 00000000 00000000 11111111  ( 0xff 也是 0x000000ff)
			       &
			    11111111 11111111 11111111 11010110  
			      结果：
			      00000000 00000000  00000000 11010110 （即是整数214）
			 */
			
			
			
			val = val <<  Byte.SIZE |  (des[offset] & BYTEMASK);
			offset++;
		}
		return val;
	}
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		byte[] basicByteArr = new byte[BYTESIZE + SHORTSIZE + INTSIZE + LONGSIZE];
		int offset = encodeBasicBigEndian(basicByteArr, byteVal, 0, BYTESIZE);
		offset = encodeBasicBigEndian(basicByteArr, shortVal, offset, SHORTSIZE);
		offset = encodeBasicBigEndian(basicByteArr, intVal, offset, INTSIZE);
		offset = encodeBasicBigEndian(basicByteArr, longVal, offset, LONGSIZE);
		
		
		long value = decodeBasicBigEndian(basicByteArr, 0, BYTESIZE);
		value = decodeBasicBigEndian(basicByteArr, 1, SHORTSIZE);
		value = decodeBasicBigEndian(basicByteArr, 3, INTSIZE);
		value = decodeBasicBigEndian(basicByteArr, 7, LONGSIZE);
	}

}
