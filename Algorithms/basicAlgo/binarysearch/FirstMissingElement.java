public class FirstMissingElement {
	
	private static int findFistMissingElement(int[] arr) {
		int left = 0;
		int right = arr.length - 1;
		while (left + 1 < right) {
			int mid = (right - left) / 2 + left;
			if (arr[left] - left != arr[mid] - mid) {
				right = mid;
			}
			else if (arr[right] - right != arr[mid] - mid) {
				left = mid;
			}
		}
		return arr[left] + 1;
	}

	public static void main(String[] args) { 
		int[] arr = {1,2,3,4,5,6,8,9,10};
		System.out.println(findFistMissingElement(arr));
		int[] arr2 = {1,2,3,4,5,6,8,9,10,15};
		System.out.println(findFistMissingElement(arr2));
		int[] arr3 = {1,2,4,5,6,8,9,10,15,69};
		System.out.println(findFistMissingElement(arr3));
	}
}









