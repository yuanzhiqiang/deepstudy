package com.yuanzq.datastructure.part9;

/*
 * 快速排序
 */
public class QuickSort {

	public static int  partition(long[] arr, int left, int right, long point) {
		int leftPre = left - 1;
		int rightPre = right;
		while (true) {
			while (leftPre < rightPre && arr[++leftPre] < point);

			while (leftPre < rightPre && arr[--rightPre] > point);

			if (leftPre >= rightPre)
				break;
			long tmp = arr[leftPre];
			arr[leftPre] = arr[rightPre];
			arr[rightPre] = tmp;
		}
		long tmp = arr[leftPre];
		arr[leftPre] = arr[right];
		arr[right] = tmp;
		return leftPre;
	}

	public static void sort(long[] arr, int left, int right){
		if(left >= right) return;
		long point = arr[right];
		int index = partition(arr, left, right, point);
		//左子数组快速排序
		sort(arr, left, index - 1);
		//右子数组快速排序
		sort(arr, index + 1, right);
	} 
	
	public static void displayArr(long[] arr) {
		System.out.print("[");
		for(long num : arr) {
			System.out.print(num + " ");
		}
		System.out.print("]");
		System.out.println();
	}
}