package com.yuanzq.datastructure.part6;
public class Fibonacci {
	
	
	
	
	public static int getNumber(int n) {
		if(0 == n){
			return 0;
		}else if(1 == n){
			return 1;
		}
		return getNumber(n) + getNumber(n - 1);
	}
}