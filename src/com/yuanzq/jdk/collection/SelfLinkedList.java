package com.yuanzq.jdk.collection;

public class SelfLinkedList<E> {

	private int size;
	
	private SelfNode<E> head;
	
	private SelfNode<E> tail;
	
	public SelfLinkedList(){
		head = new SelfNode<E>();
		tail = head;
		//head.next = tail;
		//tail.pre = head;
	}
	

	public  E get(){
		return tail.element;
	}
	
	public  E get(int index){
		if(index >= size){
			throw new RuntimeException("请求的元素不在容器存储的范围内");
		}
		SelfNode<E> node = head;
		int count = 0;
		while(null != node.next){
			if(index == count){
				break;
			}
			count++;
			node = node.next;
		}
		return node.element;
	}
	
	public SelfLinkedList add(E e){
		if(null == head.element){
			head.element = e;
			size++;
			return this;
		}
		SelfNode<E> node = new SelfNode<E>();
		node.element = e;
		node.pre = tail;
		tail.next = node;
		tail = node;
		size++;
		return this;
	}
	
	public SelfLinkedList remove(){
		
		tail = tail.pre ;
		tail.next = null;
		size--;
		return this;
	}
	
	public SelfLinkedList remove(int index){
		if(index >= size){
			throw new RuntimeException("请求的元素不在容器存储的范围内");
		}
		SelfNode<E> node = head;
		int count = 0;
		while(null != node.next){
			if(index == count){
				break;
			}
			count++;
			node = node.next;
		}
		if(head == node){
			head = head.next;
			size--;
			return this;
		}
		if(tail == node){
			tail = tail.pre;
			tail.next = null;
			size--;
			return this;
		}
		node.pre.next = node.next;
		node.next.pre = node.pre;
		size--;
		return this;
	}
	
	public int size(){
		return size;
	}
	
	static class SelfNode<E>{
		private SelfNode<E> pre;
		private E element;
		private SelfNode<E> next;
	}
	
	public static void main(String[] args) {
		SelfLinkedList<String> list = new SelfLinkedList<String>();
//		for(int i = 0; i < 100; i++){
//			list.add("yuanzhiqiang" + i);
//		}
//		list.remove(99);
//		for(int i = 0; i < list.size(); i++){
//			System.out.println((String)list.get(i));
//		}
		System.out.println(list.add("yuanzhiqiaing").
				add("yuanzhiqiang2").
				add("yuanzhiqiaing3").
				add("yuanzhiqiaing4").remove(1).get(2));
	}
}
