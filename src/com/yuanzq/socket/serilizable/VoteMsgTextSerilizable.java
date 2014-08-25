package com.yuanzq.socket.serilizable;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Scanner;

import com.yuanzq.socket.bookexample.chapter3.VoteMsg;

public class VoteMsgTextSerilizable implements VoteMsgSerilizable {

	public static final String MAGIC = "Voting";
	public static final String VOTESTR = "v";
	public static final String INQSTR = "i";
	public static final String RESPONSESTR = "R";

	public static final String CHARSETNAME = "US-ASCII";
	public static final String DELIMSTR = " ";
	public static final int MAX_WIRE_LENGTH = 2000;

	@Override
	public byte[] serilizable(VoteMsg msg) throws IOException {
		String msgString = MAGIC + DELIMSTR
				+ (msg.isInquiry() ? INQSTR : VOTESTR) + DELIMSTR
				+ (msg.isResponse() ? RESPONSESTR + DELIMSTR : "")
				+ Integer.toString(msg.getCandidateID()) + DELIMSTR
				+ Long.toString(msg.getVoteCount());
		byte data[] = msgString.getBytes(CHARSETNAME);
		return data;

	}

	@Override
	public VoteMsg unserilizable(byte[] input) throws IOException {
		boolean isInquiry; // true if inquiry; false if vote
		boolean isResponse;// true if response from server
		int candidateID; // in [0,1000]
		long voteCount; // nonzero only in response
		ByteArrayInputStream bis = new ByteArrayInputStream(input);
		Scanner scanner = new Scanner(bis);
		String token = scanner.next();
		if (!token.equals(MAGIC)) {
			throw new RuntimeException("error");
		}
		token = scanner.next();
		if (token.equals(INQSTR)) {
			isInquiry = true;
		}else{
			isInquiry = false;
		}
		token = scanner.next();
		if(RESPONSESTR.equals(token)){
			isResponse = true;
			token = scanner.next();
		}else{
			isResponse = false;
		}
		candidateID = Integer.parseInt(token);
		token = scanner.next();
		voteCount = Integer.parseInt(token);
		return new VoteMsg(isResponse, isInquiry, candidateID, voteCount);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		double d = scanner.nextDouble();
		System.out.println();
	}
}
