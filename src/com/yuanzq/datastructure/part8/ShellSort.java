package com.yuanzq.datastructure.part8;

/*
 * 希尔排序
 */
public class ShellSort {

	/**
	 * 排序方法
	 */
	public static void sort(long[] arr) {
		int interval = 1;  //间隔
		while(interval < arr.length / 3){
			interval = interval * 3 + 1;
		}
		while(interval > 0){
			for(int i = interval; i < arr.length; i++){
				long tmp = arr[i];
				int j = i;
				while(j >= interval && arr[j - interval] > tmp){
					arr[j] = arr[j - interval];
					j = j - interval;
				}
				if(j != i){
					arr[j] = tmp;
				}
			}
			interval = (interval - 1) / 3;
		}
	}
	
}