package com.yuanzq.socket.tcp;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import com.yuanzq.socket.bookexample.chapter3.VoteMsg;
import com.yuanzq.socket.framer.Framer;
import com.yuanzq.socket.framer.LengthFramer;
import com.yuanzq.socket.serilizable.VoteMsgSerilizable;
import com.yuanzq.socket.serilizable.VoteMsgTextSerilizable;

public class VoteTCPServer {

	private static HashMap<Integer, Long> voteMap = new HashMap<Integer, Long>();

	public static void main(String[] args) throws Exception {
		ServerSocket ss = new ServerSocket(8888);
		System.out.println("server started!!");
		while (true) {
			Socket socket = ss.accept();
			System.out.println("server accept a request");
			InputStream in = socket.getInputStream();
			Framer framer = new LengthFramer(new DataInputStream(in));
			byte[] input = framer.nextMsg();
			VoteMsgSerilizable voteMsgserilizalble = new VoteMsgTextSerilizable();
			VoteMsg voteMsg = voteMsgserilizalble.unserilizable(input);
			voteMsg.setResponse(true);
			if (!voteMsg.isInquiry()) {
				int candidateID = voteMsg.getCandidateID();
				long count = null == voteMap.get(candidateID) ? 0  : voteMap.get(candidateID);
				voteMap.put(candidateID, count + 1);
			}
			voteMsg.setVoteCount(voteMap.get(voteMsg.getCandidateID()));
			OutputStream out = socket.getOutputStream();
			byte[] output = voteMsgserilizalble.serilizable(voteMsg);
			framer.frameMsg(output, out);
			System.out
					.println("server execute finished!! return to wait another client");
			socket.close();
		}
	}
}
