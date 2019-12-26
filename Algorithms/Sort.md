
# Sort 问题


## 1. QuickSort

- 快排应用了一种分治的思想，选取一个Key 从左到右开始，把比key小的都放去key左边，比key大的都放去key右边
- 然后完成分别递归key 左边和右边的子数组


<img src="https://raw.githubusercontent.com/zeyao/TechNotes/master/Document/quicksort.jpg" style="height:500px" />


```


    public static void quickSort(int left, int right, Integer[] arr) {
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
```


