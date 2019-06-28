package twoPointer;

public class FindClosestInTwoSortedList {

	public static void main(String[] args){
		
		FindClosestInTwoSortedList test = new FindClosestInTwoSortedList();
		int[] arr1 = {1,2,6,9,67,99};
		int[] arr2 = {20,40,101,9999};
	    int result = test.findClosest(arr1, arr2);
	    System.out.println(result);
	}
	
	private int findClosest(int[] arr1, int[] arr2) {
		int index1 = 0;
		int index2 = 0;
		int dif = Integer.MAX_VALUE;
		while (index1 < arr1.length && index2 < arr2.length) {
			dif = Math.min(dif, Math.abs(arr1[index1] - arr2[index2]));
			if (arr1[index1] > arr2[index2]) {
				index2++;
			} else {
				index1++;
			}
		}
		return dif;
	}
}