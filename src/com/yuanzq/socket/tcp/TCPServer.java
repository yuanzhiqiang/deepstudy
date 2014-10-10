package com.yuanzq.socket.tcp;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

	public static void main(String[] args) throws Exception {
		ServerSocket ss = new ServerSocket(80);
		ss.setReuseAddress(true);
		System.out.println(ss.getLocalPort());
		System.out.println(ss.getReuseAddress());
		System.out.println(ss.getLocalSocketAddress());
		System.out.println("server started!!");
		while (true) {
			Socket socket = ss.accept();
			System.out.println("server accept a request");
			InputStream in = socket.getInputStream();
			byte[] buffer = new byte[100];
			StringBuffer sb = new StringBuffer();
			while (-1 !=  in.read(buffer)) {
				sb.append(new String(buffer));
			}
			System.out.println("request String is: " + sb);
			OutputStream out = socket.getOutputStream();
			out.write("Hello Client!!!".getBytes());
			System.out
					.println("server execute finished!! return to wait another client");
			socket.close();
		}
	}
}
