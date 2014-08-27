package com.yuanzq.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

import com.yuanzq.socket.bookexample.chapter3.VoteMsg;
import com.yuanzq.socket.serilizable.VoteMsgSerilizable;
import com.yuanzq.socket.serilizable.VoteMsgTextSerilizable;

public class VoteUDPClient {

	private static int TIMEOUT = 500;
	private static boolean FLAG = false;
	private static int REQUESTTIMES = 5;

	public static void main(String[] args) throws IOException {

		VoteMsg vm = createVoteFactory();
		VoteMsgSerilizable voteMsgserilizalble = new VoteMsgTextSerilizable();
		byte[] output = voteMsgserilizalble.serilizable(vm);
		DatagramPacket dp = new DatagramPacket(output,
				output.length);
		DatagramPacket receivePacket = new DatagramPacket(
				new byte[VoteMsgTextSerilizable.MAX_WIRE_LENGTH], VoteMsgTextSerilizable.MAX_WIRE_LENGTH);
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
			vm = voteMsgserilizalble.unserilizable(receivePacket.getData());
			System.out.println("Received: "
					+ vm);
		} else {
			System.out.println("No response -- giving up.");
		}
		ds.close();
	}
	
	private static VoteMsg createVoteFactory(){
		VoteMsg vm = new VoteMsg(false, false , 100, 0);
		//VoteMsg vm = new VoteMsg(false, true , 100, 10);
		return vm;
	} 
}
