package com.yuanzq.datastructure.part2;



/**
 * 选择排序
 * @author Administrator
 *
 */
public class SelectionSort {

	public static void sort(long[] arr) {
		int index;
		long tmp;
		for(int i = 0; i < arr.length; i++){
			index = i;
			tmp = arr[i];
			for(int j = arr.length - 1; j > i; j--){
				if(arr[j] < tmp){
					tmp = arr[j];
					index = j;
				}
			}
			if(index != i){
				long temp = arr[i];
				arr[i] = tmp;
				arr[index] = temp;
				
			}
		}
	}
}
