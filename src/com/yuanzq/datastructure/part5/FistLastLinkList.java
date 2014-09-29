package com.yuanzq.datastructure.part5;

/**
 * 链表
 * @author Administrator
 *
 */
public class FistLastLinkList {
	
	
	public Node head;
	
	public Node tail;
	
	public FistLastLinkList() {
		head = null;
		tail = null;
	}
	
	/**
	 * 插入一个结点
	 */
	public void insertFirst(long value) {
		Node node = new Node(value);
		node.next = head;
		head = node;
	}
	/**
	 * 删除一个结点
	 */
	public Node deleteFirst() {
		Node deleteNode = head;
		head = head.next;
		return deleteNode;
	}
	
	/**
	 * 显示方法
	 */
	public void display() {
		Node node = head;
		while(null != node){
			node.display();
			node = node.next;
		}
	}
	
	/**
	 * 查找方法
	 */
	public Node find(long value) {
		Node result = null;
		Node node = head;
		while(null != node){
			if(value == node.data){
				result = node;
				break;
			}
			node = node.next;
		}
		return result;
	}
	
	/**
	 * 删除方法，根据数据域来进行删除
	 */
	public Node delete(long value) {
		if(value == head.data){
			Node node = head;
			head = head.next;
			node.next = null;
			return node;
		}
		Node preNode = head;
		Node deleteNode = head.next;
		while(null != deleteNode){
			if(value == deleteNode.data){
				break;
			}
			preNode = preNode.next;
			deleteNode = deleteNode.next;
		}
		preNode.next = deleteNode.next;
		deleteNode.next = null;
		return deleteNode;
	}
}