package com.yuanzq.datastructure.part5;
/*
 * 双向链表
 */
public class DoubleLinkList {
	
	
	
	private Node head;
	
	private Node tail;
	
	public DoubleLinkList(){
		head = null;
		tail = null;
	}
	
	/**
	 * 插入一个结点，在头结点后进行插入
	 */
	public void insertFirst(long value) {
		Node node = new Node(value);
		if(null == head && null == tail){
			tail = node;
			head = node;
			return;
		}
		node.next = head;
		head = node;
	}
	
	/**
	 * 插入一个结点，从尾结点进行插入
	 */
	public void insertLast(long value) {
		Node node = new Node(value);
		if(null == head && null == tail){
			tail = node;
			head = node;
			return;
		}
		tail.next = node;
		tail = node;
	}
	
	/**
	 * 删除一个结点，在头结点后进行删除
	 */
	public Node deleteFirst() {
		if(head == null){
			return null;
		}
		Node node = head;
		head = head.next;
		return node;
	}
	
	/**
	 * 删除结点，从尾部进行删除
	 */
	public Node deleteLast() {
		if(tail == null){
			return null;
		}
		Node node = tail;
		tail = tail.pre;
		return node;
	}
	/**
	 * 显示方法
	 */
	public void display() {
		Node current = head;
		while(current != null) {
			current.display();
			current = current.next;
		}
		System.out.println();
	}
	
	/**
	 * 删除方法，根据数据域来进行删除
	 */
	public Node delete(long value) {
		Node current = head;
		while(current.data != value) {
			if(current.next == null) {
				return null;
			}
			current = current.next;
		}
		
		if(current == head) {
			head = head.next;
		} else {
			current.pre.next = current.next;
		}
		return current;
	}
}