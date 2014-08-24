package com.yuanzq.socket.framer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;



/**
 * @desc基于结尾定位符的成帧方法
 * @author yuanzhiq
 *
 */
public class DelimFramer implements Framer{

	
	private byte endFlag = '\n';
	InputStream in = null;
	
	DelimFramer(InputStream in){
		this.in = in;
	}
	
	@Override
	public void frameMsg(byte[] message, OutputStream out) throws IOException {
		for(byte b : message){
			if(endFlag == b){
				throw new IOException("包含了结束符的字节数组");
			}
		}
		out.write(message);
		out.write(endFlag);
	}

	@Override
	public byte[] nextMsg() throws IOException {
		int value ;
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		while((value = in.read()) != endFlag){
			if(value == -1){
				if(bao.size() != 0){
					throw new RuntimeException("没有结束字符的字节数组");
				}else{
					return null;
				}
			}
			bao.write(value);
		}
		return bao.toByteArray();
	}

}
