package com.yuanzq.socket.tcp;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {

	public static void main(String[] args) throws Exception {

		Socket s = new Socket("localhost", 80);
		OutputStream out = s.getOutputStream();
		out.write("Hello Server!".getBytes());
		s.shutdownOutput();
		InputStream in = s.getInputStream();
		byte[] buffer = new byte[100];
		StringBuffer sb = new StringBuffer();
		while(-1 != in.read(buffer)){
			sb.append(new String(buffer));
		}
		
		System.out.println("Client excute result: " + sb);
		System.out.println("Client Finished");
		s.close();
	}
}
