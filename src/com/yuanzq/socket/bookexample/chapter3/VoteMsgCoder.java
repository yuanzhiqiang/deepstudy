package com.yuanzq.socket.bookexample.chapter3;

import java.io.IOException;

public interface VoteMsgCoder {
  byte[] toWire(VoteMsg msg) throws IOException;
  VoteMsg fromWire(byte[] input) throws IOException;
}
