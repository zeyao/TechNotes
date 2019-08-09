package sorting;

import java.util.Arrays;
import java.util.Comparator;

public class ReverseDomainOrder {
	/**
	 * 
	 * 按照逆域名排序，例如输入的是 com.google 和 com.apple ，
	 * 比较的时候是按照 google.com 和 apple.com 进行比较的。
	 * 排序结果自然是 apple.com, google.com
	 * @param arr
	 * @return
	 */
	private static String[] sortByReverseDomain(String[] arr) {
		Comparator<String> compareFactor = new Comparator<String>() {
			public int compare(String str1, String str2) {
				 String[] strArr1 = str1.split("\\.");
				 String[] strArr2 = str2.split("\\.");
				 //compare each string split by dot from end;
				 for (int i = 0; i < Math.min(strArr1.length, strArr2.length); i++) {
					 String str1FromEnd = strArr1[strArr1.length - 1 - i];					 
					 String str2FromEnd = strArr2[strArr2.length - 1 - i];
					 if (str1FromEnd.compareTo(str2FromEnd) != 0) {
						 return str1FromEnd.compareTo(str2FromEnd);
					 }
				 }
				 //all element are same;
                 return strArr1.length - strArr2.length;               
			}
		};
		
		Arrays.sort(arr, compareFactor);
		return arr;
	}
	
	public static void main(String[] args) {
		String[] arr = {"com.google", "com.apple","com.pie.apple"};
		System.out.println(Arrays.asList(sortByReverseDomain(arr)));
	}
	
	
}
