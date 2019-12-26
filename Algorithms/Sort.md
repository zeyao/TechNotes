
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

- 快排的复杂度分析：
- 在最优情况下，divided 每次都划分得很均匀，如果排序n个关键字，其递归树的深度就为log2n，即仅需递归log2n次，需要时间为T（n）的话，第一次Partiation应该是需要对整个数组扫描一遍，做n次比较。然后，获得的枢轴将数组一分为二，那么各自还需要T（n/2）的时间（注意是最好情况，所以平分两半）


<img src="https://raw.githubusercontent.com/zeyao/TechNotes/master/Document/qsnlogn.png" style="height:70px" />