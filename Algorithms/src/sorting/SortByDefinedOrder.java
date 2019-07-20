package sorting;

import java.util.Arrays;
import java.util.Comparator;

public class SortByDefinedOrder {
	/**
	 * 
	 * LC 953 https://leetcode.com/problems/verifying-an-alien-dictionary/
	 * 算法第四版： 2.5.16 公正的选举
	 * 
	 */
	private static String[] sortByDefinedOrder(String[] arr, String order) {
		Comparator<String> compareFactor = new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				char[] s1Arr = s1.toCharArray();
				char[] s2Arr = s2.toCharArray();
				for (int i = 0 ; i < Math.min(s1Arr.length, s2Arr.length); i++) {
					if (order.indexOf(String.valueOf(s1Arr[i])) != order.indexOf(String.valueOf(s2Arr[i]))) {
						return order.indexOf(String.valueOf(s1Arr[i])) - order.indexOf(String.valueOf(s2Arr[i]));
					}
				}
				return s1.length() - s2.length();
			}
			
		};
		Arrays.sort(arr,compareFactor);
		return arr;
	}
	
	public static void main(String[] args) {
		String order = "hlabcdefgijkmnopqrstuvwxyz";
		String[] arr = {"hello","word","hell","words"};
		System.out.println(Arrays.asList(sortByDefinedOrder(arr,order)));
	}
}
