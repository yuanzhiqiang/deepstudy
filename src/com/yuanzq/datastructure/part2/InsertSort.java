package com.yuanzq.datastructure.part2;


/**
 * 插入排序
 * @author Administrator
 *
 */
public class InsertSort {

	
	public static void sort(long[] arr) {
		
		for(int i = 1; i < arr.length; i++){
			long tmp = arr[i];
			int j = i -1;
			while(j >= 0 && arr[j] > tmp){
				arr[j + 1] = arr[j];
				j--;
			}
			if(j != i -1){
				arr[j] = tmp;
			}
		}
		
		
	}
}
