package com.yuanzq.socket.framer;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 
 * @desc 成帧和解析
 * @author yuanzhiq
 * 
 */
public interface Framer {
	
	//成帧编码
	void frameMsg(byte[] message, OutputStream out) throws IOException;

	//解析
	byte[] nextMsg() throws IOException;
}
