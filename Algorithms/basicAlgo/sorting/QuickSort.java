package sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuickSort {
	public static void main(String[] args) {
		Integer[] arr = {44, 32, 11, 32,12,7,78,23,45};
		quickSort(0, arr.length-1, arr);
		List<Integer> list = new ArrayList<>(Arrays.asList(arr));
		System.out.println(list);
	}
	
	private static void quickSort(int left, int right, Integer[] arr) {
		if (left >= right) return;
		int index = divided(left, right, arr);
		quickSort(left, index - 1, arr);
		quickSort(index + 1, right, arr);
	}
	
	private static int divided(int left, int right, Integer[] arr) {
		int key = arr[left];
		while (left < right) {
			while (left < right && arr[right] > key) {
				right--;
			}
			if (left < right) {
				int tmp = arr[left];
				arr[left] = arr[right];
				arr[right] = tmp;
				left++;
			}
		
			while (left < right && arr[left] < key) {
				left++;
			}
			
			if (left < right) {
				int tmp = arr[left];
				arr[left] = arr[right];
				arr[right] = tmp;
				right--;
			}
		}
		return left;
	}
}
