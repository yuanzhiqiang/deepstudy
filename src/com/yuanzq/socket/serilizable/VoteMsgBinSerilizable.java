package com.yuanzq.socket.serilizable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.yuanzq.socket.bookexample.chapter3.VoteMsg;

public class VoteMsgBinSerilizable implements VoteMsgSerilizable {

	public static final int MIN_WIRE_LENGTH = 4;
	public static final int MAX_WIRE_LENGTH = 16;
	public static final int MAGIC = 0x5400;
	public static final int MAGIC_MASK = 0xfc00;
	public static final int MAGIC_SHIFT = 8;
	public static final int RESPONSE_FLAG = 0x0200;
	public static final int INQUIRE_FLAG = 0x0100;

	@Override
	public byte[] serilizable(VoteMsg msg) throws IOException {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(byteStream); // converts
		short magicAndFlags = MAGIC;
	    if (msg.isInquiry()) {
	      magicAndFlags |= INQUIRE_FLAG;
	    }
	    if (msg.isResponse()) {
	      magicAndFlags |= RESPONSE_FLAG;
	    }
	    out.writeShort(magicAndFlags);
	    out.writeShort((short) msg.getCandidateID());
	    if(msg.isResponse()){
	    	out.writeLong(msg.getVoteCount());
	    }
		return null;
	}

	@Override
	public VoteMsg unserilizable(byte[] input) throws IOException {
		boolean isInquiry = false; // true if inquiry; false if vote
		boolean isResponse = false;// true if response from server
		int candidateID; // in [0,1000]
		long voteCount = 0; // nonzero only in response
		ByteArrayInputStream bais = new ByteArrayInputStream(input);
		DataInputStream dis = new DataInputStream(bais);
		short magic = dis.readShort();
		if((magic & MAGIC_MASK) != MAGIC){
			throw new RuntimeException("error");
		}
		if((magic & INQUIRE_FLAG) == INQUIRE_FLAG){
			isInquiry = true;
		}
		if((magic & RESPONSE_FLAG) == RESPONSE_FLAG){
			isResponse = true;
		}
		candidateID = dis.readShort();
		if(isResponse){
			voteCount = dis.readLong();
		}
		return new VoteMsg(isResponse, isInquiry, candidateID, voteCount);
	}

}
