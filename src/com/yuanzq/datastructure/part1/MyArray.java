package com.yuanzq.datastructure.part1;

public class MyArray {

	
	
	private long[] arr;
	
	private int elements;
	
	public MyArray(){
		this(20);
	}
	
	public MyArray(int capicity){
		arr = new long[capicity]; 
	}
	
	/**
	 * 添加数据
	 */
	public void insert(long value) {
		if(elements >= arr.length){
			long[] newArr = new long[arr.length * 2];
			System.arraycopy(arr, 0, newArr, 0, arr.length);
			arr = newArr;
		}
		arr[elements++] = value;
	}
	
	/**
	 * 显示数据
	 */
	public void display() {
		System.out.print("[");
		for(int i = 0; i < elements; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println("]");
	}
	
	/**
	 * 查找数据
	 */
	public int search(long value) {
		for(int i = 0; i < elements; i++){
			if(value == arr[i])return i;
		}
		return -1;
	}
	
	/**
	 * 查找数据，根据索引来查
	 */
	public long get(int index) {
		if(index >= elements || index < 0){
			throw new ArrayIndexOutOfBoundsException();
		}
		return arr[index];
	}
	
	/**
	 * 删除数据
	 */
	public void delete(int index) {
		if(index >= elements || index < 0){
			throw new ArrayIndexOutOfBoundsException();
		}
		for(int i = elements; i < elements - 1; i++){
			arr[i] = arr[i + 1];
		}
		elements--;
	}
	
	/**
	 * 更新数据
	 */
	public void change(int index, int newvalue) {
		if(index >= elements || index < 0){
			throw new ArrayIndexOutOfBoundsException();
		}
		arr[index] = newvalue;
	}
}
