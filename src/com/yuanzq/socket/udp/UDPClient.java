package com.yuanzq.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

public class UDPClient {

	private static int TIMEOUT = 500;
	private static boolean FLAG = false;
	private static int REQUESTTIMES = 5;

	public static void main(String[] args) throws IOException {

		String sendString = "hello";
		DatagramPacket dp = new DatagramPacket(sendString.getBytes(),
				sendString.length());
		DatagramPacket receivePacket = new DatagramPacket(
				new byte[sendString.length()], sendString.length());
		dp.setAddress(InetAddress.getByName("localhost"));
		dp.setPort(8888);
		DatagramSocket ds = new DatagramSocket(9999);
		ds.setSoTimeout(TIMEOUT);
		int times = 0;
		do {
			try {
				ds.send(dp);
				ds.receive(receivePacket);
				FLAG = true;
			} catch (SocketTimeoutException s) {
				times++;
				s.printStackTrace();
			}
		} while (!FLAG && times < REQUESTTIMES);
		if (FLAG) {
			System.out.println("Received: "
					+ new String(receivePacket.getData()));
		} else {
			System.out.println("No response -- giving up.");
		}
		ds.close();
	}
}
