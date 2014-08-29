package com.yuanzq.socket.udp;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Arrays;

public class UDPMulticastReceiver {

	
	//aaa
	public static void main(String[] args) throws Exception {
		int MAXSIZE = 1024;
		byte[] buf = new byte[MAXSIZE];
		InetAddress multiAddess = InetAddress.getByName("224.5.5.9");
		MulticastSocket ms = new MulticastSocket(1234);
		ms.joinGroup(multiAddess);
		DatagramPacket dp = new DatagramPacket(buf, buf.length);
		while(true){
			ms.receive(dp);
			System.out.println(new String(Arrays.copyOfRange(buf, 0, dp.getLength())));
			dp.setLength(MAXSIZE);
		}
	}
}
