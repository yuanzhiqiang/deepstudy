package com.yuanzq.socket.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {

	private static int MAXSIZE = 255;
	public static void main(String[] args) throws Exception {
		byte[] buf = new byte[MAXSIZE];
		DatagramPacket dp = new DatagramPacket(buf, MAXSIZE);
		DatagramSocket ds = new DatagramSocket(8888);
		while(true){
			ds.receive(dp);
			System.out.println("Handling client at " + dp.getAddress().getHostAddress() + " on port " + dp.getPort());
			ds.send(dp);
			dp.setLength(MAXSIZE);
		}
	}
	
}
