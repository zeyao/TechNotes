
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



## 2. 三色排序

> Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.

> Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

```
 Input: [2,0,2,1,1,0]
 Output: [0,0,1,1,2,2]

```
> AKA  Dutch national flag problem  https://en.wikipedia.org/wiki/Dutch_national_flag_problem


- 需要三个pointer left right current 
- 保证 [0, left) 只有 0  
- [left, current) 只有 1， 
- [right, length - 1] 只有2
- current 到 right 之间未知， 不断swap, 直到current 遇到 right, 遇到 0 swap arr[current] 和 arr[left] left++ 遇到2 swap arr[current] 和 arr[right] right-- . 遇到1 current++ 
- 若left > current, curent++;

<img src="https://raw.githubusercontent.com/zeyao/TechNotes/master/Document/%E4%B8%89%E8%89%B2%E6%8E%92%E5%BA%8F.jpg" style="height:500px" />



```

    public void sortColors(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int current = 0;
        while (current <= right) {
            if (nums[current] == 1) {
                current++;
            }
            else if (nums[current] == 0) {
                int tmp = nums[left];
                nums[left] = nums[current];
                nums[current] = tmp;
                left++;   
                if (current < left) {
                    current++;
                }
                //left 不可超过 current 若超过 说明之前都是0
                
            }
            else if (nums[current] == 2){
                int tmp = nums[right];
                nums[right] = nums[current];
                nums[current] = tmp;
                right--;
            }            
        }
    }
    
    
```


