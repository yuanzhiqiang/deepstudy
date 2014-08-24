package com.yuanzq.socket.serilizable;

import java.io.IOException;

import com.yuanzq.socket.bookexample.chapter3.VoteMsg;

public interface VoteMsgSerilizable {

	//序列化方法
	byte[] serilizable(VoteMsg msg) throws IOException;
	
	
	//反序列化方法
	VoteMsg unserilizable(byte[] input) throws IOException;
}
