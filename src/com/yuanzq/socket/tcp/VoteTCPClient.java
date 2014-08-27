package com.yuanzq.socket.tcp;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.yuanzq.socket.bookexample.chapter3.VoteMsg;
import com.yuanzq.socket.framer.Framer;
import com.yuanzq.socket.framer.LengthFramer;
import com.yuanzq.socket.serilizable.VoteMsgSerilizable;
import com.yuanzq.socket.serilizable.VoteMsgTextSerilizable;

public class VoteTCPClient {

	public static void main(String[] args) throws Exception {

		Socket s = new Socket("localhost", 8888);
		OutputStream out = s.getOutputStream();
		InputStream in = s.getInputStream();
		VoteMsg vm = createVoteFactory();
		VoteMsgSerilizable voteMsgserilizalble = new VoteMsgTextSerilizable();
		byte[] output = voteMsgserilizalble.serilizable(vm);
		Framer framer = new LengthFramer(new DataInputStream(in));
		framer.frameMsg(output, out);
		byte[] buffer = framer.nextMsg();
		vm = voteMsgserilizalble.unserilizable(buffer);
		StringBuffer sb = new StringBuffer();
		System.out.println("Client excute result: " + vm);
		System.out.println("Client Finished");
		s.close();
	}
	
	private static VoteMsg createVoteFactory(){
		VoteMsg vm = new VoteMsg(false, false , 100, 0);
		//VoteMsg vm = new VoteMsg(false, true , 100, 10);
		return vm;
	} 
}
