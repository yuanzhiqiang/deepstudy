package com.yuanzq.socket.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.HashMap;

import com.yuanzq.socket.bookexample.chapter3.VoteMsg;
import com.yuanzq.socket.serilizable.VoteMsgSerilizable;
import com.yuanzq.socket.serilizable.VoteMsgTextSerilizable;

public class VoteUDPServer {

	private static int MAXSIZE = 255;
	private static HashMap<Integer, Long> voteMap = new HashMap<Integer, Long>();
	
	public static void main(String[] args) throws Exception {
		byte[] buf = new byte[VoteMsgTextSerilizable.MAX_WIRE_LENGTH];
		DatagramSocket ds = new DatagramSocket(8888);
		while(true){
			DatagramPacket dp = new DatagramPacket(buf, VoteMsgTextSerilizable.MAX_WIRE_LENGTH);
			ds.receive(dp);
			byte[] input = dp.getData();
			VoteMsgSerilizable voteMsgserilizalble = new VoteMsgTextSerilizable();
			VoteMsg voteMsg = voteMsgserilizalble.unserilizable(input);
			voteMsg.setResponse(true);
			if (!voteMsg.isInquiry()) {
				int candidateID = voteMsg.getCandidateID();
				long count = null == voteMap.get(candidateID) ? 0  : voteMap.get(candidateID);
				voteMap.put(candidateID, count + 1);
			}
			voteMsg.setVoteCount(voteMap.get(voteMsg.getCandidateID()));
			byte[] output = voteMsgserilizalble.serilizable(voteMsg);
			dp.setData(output);
			System.out.println("Handling client at " + dp.getAddress().getHostAddress() + " on port " + dp.getPort());
			ds.send(dp);
			dp.setLength(MAXSIZE);
		}
	}
	
}
