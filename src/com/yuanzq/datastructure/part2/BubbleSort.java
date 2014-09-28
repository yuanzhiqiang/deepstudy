package com.yuanzq.datastructure.part2;



/**
 * 冒泡排序
 * @author Administrator
 *
 */
public class BubbleSort {

	public static void sort(long[] arr) {
		
		for(int i = 0; i < arr.length; i++){
			for(int j = arr.length - 1; j > i; j--){
				if(arr[j] < arr[j - 1]){
					long temp = arr[j - 1];
					arr[j - 1] = arr[j];
					arr[j] = temp;
				}
			}
		}
	}
}
