package com.yuanzq.datastructure.part10;

public class Tree {

	
	public Node root;
	
	/**
	 * 插入节点
	 * @param data
	 */
	public void insert(long data) {
		Node node = new Node(data);
		if(null == root){
			root = node;
			return;
		}
		Node currentNode = root;
		Node parent;
		while(true){
			parent = currentNode;
			if(data == currentNode.data){
				throw new RuntimeException("插入的节点数据值和二叉树上的节点值重复");
			}
			if(data < currentNode.data){
				currentNode = currentNode.leftChild;
				if(null == currentNode){
					parent.leftChild = node;
					return;
				}
			}else{
				currentNode = currentNode.rightChild;
				if(null == currentNode){
					parent.rightChild = node;
					return;
				}
			}
		}
	}
	
	/**
	 * 查找节点
	 * @param value
	 */
	public Node find(long data) {
		Node currentNode = root;
		while(true){
			if(null == currentNode){
				throw new RuntimeException("没有查找到数据");
			}
			if(data == currentNode.data){
				return currentNode;
			}else if(data < currentNode.data){
				currentNode = currentNode.leftChild;
			}else{
				currentNode = currentNode.rightChild;
			}
		}
	}
	
	/**
	 * 删除节点
	 * @param value
	 */
	public void delte(long value) {
		
	}

}
