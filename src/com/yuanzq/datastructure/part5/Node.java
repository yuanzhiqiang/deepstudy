package com.yuanzq.datastructure.part5;

public class Node {

	//数据域
	public long data;
	
	//next指针域
	public Node next;
	
	//pre指针域
	public Node pre;
	
	public  Node(long data){
		this.data = data;
	}
	
	/**
	 * 显示方法
	 */
	public void display(){
		System.out.println(data + " ");
	}
}
