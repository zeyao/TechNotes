package sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertionSort {
	//从未排序的开头开始，每次和前面已经排序的比较，插入位置
	public static void main(String[] args) {
		Integer[] arr = {8,9,11,24,1,2,3,5};
		insertionSort(arr);
		List<Integer> list = new ArrayList<>(Arrays.asList(arr));
		System.out.println(list);
	}
	
	private static void insertionSort(Integer[] arr) {
		for (int i = 1; i < arr.length; i++) {
			for (int j = i; j > 0; j--) {
				if (arr[j] < arr[j-1]) {
					int tmp = arr[j-1];
					arr[j-1] = arr[j];
					arr[j] = tmp;
				}
			}
		}
	}
}
