package com.yuanzq.datastructure.part3;

/**
 * 循环
 * @author Administrator
 *
 */
public class MyCycleQueue {
	
	//底层实现是一个数组
		private long arr[];
		
		int elements;
		//队头
		private int head;
		
		//队尾
		private int tail;
		
		/**
		 * 默认的构造方法
		 */
		public MyCycleQueue() {
			arr = new long[10];
			head = 0;
			tail = 0;
			elements = 0;
		}
		
		/**
		 * 带参数构造方法，参数为数组初始化大小
		 */
		public MyCycleQueue(int maxsize) {
			arr = new long[maxsize];
			head = 0;
			tail = -1;
			elements = 0;
		}
		
		/**
		 * 入队,从队尾插入
		 */
		public void insert(int value) {
			if(elements == arr.length){
				throw new RuntimeException("队列已满");
			}
			if(tail == arr.length - 1){
				tail = -1;
			}
			arr[++tail] = value;
			elements++;
		}
		
		/**
		 * 出队，从队头删除
		 */
		public long remove() {
			if(elements == 0){
				throw new RuntimeException("队列无数据");
			}
			if(head == arr.length - 1){
				head = 0;
			}
			elements--;
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
			return 0 == elements;
		}
		
		
		/**
		 * 判断是否满了
		 */
		public boolean isFull() {
			return elements == arr.length;
		}
}