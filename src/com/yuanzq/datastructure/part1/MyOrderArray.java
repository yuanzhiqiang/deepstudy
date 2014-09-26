package com.yuanzq.datastructure.part1;

public class MyOrderArray {

	private long[] arr;

	private int elements;

	public MyOrderArray() {
		this(20);
	}

	public MyOrderArray(int capicity) {
		arr = new long[capicity];
	}

	/**
	 * 添加数据
	 */
	public void insert(long value) {
		if (elements >= arr.length) {
			long[] newArr = new long[arr.length * 2];
			System.arraycopy(arr, 0, newArr, 0, arr.length);
			arr = newArr;
		}
		int i;
		for (i = 0; i < elements; i++) {
			if (arr[i] > value)
				break;
		}
		for (int j = elements; j > i; j--) {
			arr[j] = arr[j - 1];
		}
		arr[i] = value;
		elements++;
	}

	/**
	 * 显示数据
	 */
	public void display() {
		System.out.print("[");
		for (int i = 0; i < elements; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println("]");
	}

	/**
	 * 查找数据
	 */
	public int search(long value) {
		for (int i = 0; i < elements; i++) {
			if (value == arr[i])
				return i;
		}
		return -1;
	}
	
	/**
	 * 二分法查找数据
	 */
	public int binarySearch(long value) {
		int reselt = -1;
		int middle = 0;
		int start = 0;
		int end = elements;
		while(start < end){
			middle = (start + end) / 2;
			if(value == arr[middle]) {
				reselt = middle;
				break;
			}
			if(arr[middle] > value){
				end = middle - 1;
			}else{
				start = middle + 1;
			}
		}
		return reselt;
	}

	/**
	 * 查找数据，根据索引来查
	 */
	public long get(int index) {
		if (index >= elements || index < 0) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return arr[index];
	}

	/**
	 * 删除数据
	 */
	public void delete(int index) {
		if (index >= elements || index < 0) {
			throw new ArrayIndexOutOfBoundsException();
		}
		for (int i = elements; i < elements - 1; i++) {
			arr[i] = arr[i + 1];
		}
		elements--;
	}

	/**
	 * 更新数据
	 */
	public void change(int index, int newvalue) {
		if (index >= elements || index < 0) {
			throw new ArrayIndexOutOfBoundsException();
		}
		arr[index] = newvalue;
	}
}
