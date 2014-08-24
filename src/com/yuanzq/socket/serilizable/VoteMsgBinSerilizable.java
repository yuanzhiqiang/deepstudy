package com.yuanzq.socket.serilizable;

import java.io.IOException;

import com.yuanzq.socket.bookexample.chapter3.VoteMsg;

public class VoteMsgBinSerilizable implements VoteMsgSerilizable {

	@Override
	public byte[] serilizable(VoteMsg msg) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VoteMsg unserilizable(byte[] input) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
