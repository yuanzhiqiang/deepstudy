package com.yuanzq.datastructure.part6;
public class Triangle {
	
	
	public static int getNumberByRecursion(int n) {
		if(n == 1){
			return 1;
		}
		return n + getNumberByRecursion(n - 1);
	}
	
	
	public static void main(String[] args) {
		System.out.println(getNumberByRecursion(100));
	}
}