package com.yuanzq.socket.udp;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class UDPMulticastSender {

	
	
	
	public static void main(String[] args) throws Exception {
		
		//组播地址从224.0.0.0 to 239.255.255.255
		InetAddress multicastAdress = InetAddress.getByName("224.5.5.9");
		int port = 1234;
		MulticastSocket ms = new MulticastSocket(port);
		ms.joinGroup(multicastAdress);
		byte[] buf = "hello,multicastserver".getBytes();
		//DatagramPacket对象必须指定InetAddress和端口号
		DatagramPacket dp = new DatagramPacket(buf, buf.length,multicastAdress,port);
		ms.send(dp);
	}
}
