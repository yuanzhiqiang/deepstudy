package com.yuanzq.socket.serilizable;

import java.io.IOException;
import java.util.Scanner;

import com.yuanzq.socket.bookexample.chapter3.VoteMsg;

public class VoteMsgTextSerilizable implements VoteMsgSerilizable {

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

	
	
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		double d = scanner.nextDouble();
		System.out.println();
	}
}
