package com.yuanzq.datastructure.part6;
public class Recursion {
	
	public static void test(int n) {
		if(n > 100){
			return;
		}
		System.out.println("Hello World!" + n);
		test(n + 1);
	}
	
	public static void main(String[] args) {
		test(1);
	}
}