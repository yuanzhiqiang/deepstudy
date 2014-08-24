package com.yuanzq.socket.framer;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class LengthFramer implements Framer {

	DataInputStream ds = null;
	
	LengthFramer(DataInputStream ds){
		this.ds = ds;
	}
	@Override
	public void frameMsg(byte[] message, OutputStream out) throws IOException {
		out.write((message.length >> 8) & 0xff);
		out.write(message.length & 0xff);
		out.write(message);
		out.flush();
	}

	@Override
	public byte[] nextMsg() throws IOException {
		byte[] buffer = new byte[ds.readUnsignedShort()];
		ds.readFully(buffer);
		return buffer;
	}

}
