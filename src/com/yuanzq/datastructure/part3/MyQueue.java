package com.yuanzq.datastructure.part3;

/**
 * 先进先出，队列
 * @author Administrator
 *
 */
public class MyQueue {
	
	//底层实现是一个数组
		private long arr[];
		
		//队头
		private int head;
		
		//队尾
		private int tail;
		
		/**
		 * 默认的构造方法
		 */
		public MyQueue() {
			arr = new long[10];
			head = 0;
			tail = 0;
		}
		
		/**
		 * 带参数构造方法，参数为数组初始化大小
		 */
		public MyQueue(int maxsize) {
			arr = new long[maxsize];
			head = 0;
			tail = -1;
		}
		
		/**
		 * 入队,从队尾插入
		 */
		public void insert(int value) {
			arr[++tail] = value;
		}
		
		/**
		 * 出队，从队头删除
		 */
		public long remove() {
			return arr[head++];
		}
		
		/**
		 * 查看数据
		 */
		public long peek() {
			return arr[head];
		}
		
		/**
		 * 判断是否为空
		 */
		public boolean isEmpty() {
			return head >  tail;
		}
		
		
		/**
		 * 判断是否满了
		 */
		public boolean isFull() {
			return head == 0 && tail == arr.length;
		}
}