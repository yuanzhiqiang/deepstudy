package com.yuanzq.datastructure.part13;
/*
 * 二叉树类
 */
public class Tree {
	//根节点
	public Node root;
	
	/**
	 * 插入节点
	 * @param value
	 */
	public void insert(long value,String sValue) {
		//封装节点
		Node newNode = new Node(value,sValue);
		//引用当前节点
		Node current = root;
		//引用父节点
		Node parent;
		//如果root为null，也就是第一插入的时候
		if(root == null) {
			root = newNode;
			return;
		} else {
			while(true) {
				//父节点指向当前节点
				parent = current;
				//如果当前指向的节点数据比插入的要大,则向左走
				if(current.data > value) {
					current = current.leftChild;
					if(current == null) {
						parent.leftChild = newNode;
						return;
					}
				} else {
					current = current.rightChild;
					if(current == null) {
						parent.rightChild = newNode;
						return;
					}
				}
			}
		}
	}
	
	/**
	 * 查找节点
	 * @param value
	 */
	public Node find(long value) {
		//引用当前节点，从根节点开始
		Node current = root;
		//循环，只要查找值不等于当前节点的数据项
		while(current.data != value) {
			//进行比较，比较查找值和当前节点的大小
			if(current.data > value) {
				current = current.leftChild;
			} else {
				current = current.rightChild;
			}
			//如果查找不到
			if(current == null) {
				return null;
			}
		}
		return current;
	}
	
	/**
	 * 前序遍历,"根-左-右"
	 */
	public void frontOrder(Node localNode) {
		if(null != localNode){
			System.out.println(localNode.data + ", " + localNode.sData);
			frontOrder(localNode.leftChild);
			frontOrder(localNode.rightChild);
		}
	}
	
	/**
	 * 中序遍历,"左-根-右"
	 */
	public void inOrder(Node localNode) {
		if(null != localNode){
			inOrder(localNode.leftChild);
			System.out.println(localNode.data + ", " + localNode.sData);
			inOrder(localNode.rightChild);
		}
	}
	
	/**
	 * 后序遍历,"左-右-根"
	 */
	public void afterOrder(Node localNode) {
		if(null != localNode){
			afterOrder(localNode.leftChild);
			afterOrder(localNode.rightChild);
			System.out.println(localNode.data + ", " + localNode.sData);
		}
	}
	
	public void delete(long value){
		Node current = root;
		Node parentNode = current; 
		Boolean isleftChild = false;
		while(current.data != value) {
			parentNode = current;
			if(current.data > value) {
				current = current.leftChild;
			} else {
				current = current.rightChild;
			}
		}
		if(parentNode.leftChild == current){
			isleftChild = true;
		}
		//删除叶子节点
		if(null == current.leftChild && null == current.rightChild){
			if(current == root){
				root = null;
				return;
			}
			if(isleftChild){
				parentNode.leftChild = null;
				return;
			}else{
				parentNode.rightChild = null;
				return;
			}
		}else if(null == current.leftChild){
			if(current == root){
				root = current.rightChild;
				return;
			}
			if(isleftChild){
				parentNode.leftChild = current.rightChild;
			}else{
				parentNode.rightChild = current.rightChild;
				return;
			}
		}else if(null == current.rightChild){
			if(current == root){
				root = current.leftChild;
				return;
			}
			if(isleftChild){
				parentNode.leftChild = current.leftChild;
			}else{
				parentNode.rightChild = current.leftChild;
				return;
			}
		}else{
			Node deleteNode = getSuccessor(current);
			if(current == root){
				deleteNode.leftChild = root.leftChild;
				deleteNode.rightChild = root.rightChild;
				root = deleteNode;
				return;
			}
			if(isleftChild){
				deleteNode.leftChild = current.leftChild;
				deleteNode.rightChild = current.rightChild;
				parentNode.leftChild = deleteNode;
			}else{
				deleteNode.leftChild = current.leftChild;
				deleteNode.rightChild = current.rightChild;
				parentNode.rightChild = deleteNode;
				return;
			}
		}
	}
	
	private Node getSuccessor(Node deleteNode){
		Node seccessNode = deleteNode;
		Node parentNode = deleteNode;
		Node currentNode = seccessNode.rightChild;
		while(null != currentNode){
			parentNode = seccessNode;
			seccessNode = currentNode;
			currentNode = currentNode.leftChild;
		}
		if(parentNode.leftChild == deleteNode){
			parentNode.leftChild = null;
		}else{
			parentNode.rightChild = null;
		}
		return seccessNode;
	}
}