package sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class selectionSort {
	
	public static void main(String[] args) {
		Integer[] arr = {8,9,6,3,22,999,1,5698};
		sort(arr);
		List<Integer> list = new ArrayList<>(Arrays.asList(arr));
		System.out.println(list);
	}
	
	private static void sort(Integer[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int min = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[min]) {
					min = j;
				}
			}
			//swap the min with the sorted last index;
			if (min != i) {
				int tmp = arr[i];
				arr[i] = arr[min];
				arr[min] = tmp;
			}
		}
	}

}
