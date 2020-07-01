
# BinarySearch 问题
## 模板1
- basic 二分查找， 二分查找一个数
- loop condition : left <= right
- search left : right = mid - 1;
- search right: left = mid + 1;


### Search in Rotated Sorted Array

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

- 通过比较left 和 mid 可以判断出旋转的array的递增区间是left ～ mid 还是 mid ～ right


``` 
public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[left] <= nums[mid]) { // from left to mid is incease
                if (nums[left] <= target && nums[mid] > target) { // target is in range left to mid
                    right = mid - 1;
                }
                else { // target is in range left to mid
                    left = mid + 1;
                }
            }
            else { //from mid to right is incease
                if (nums[right] >= target && nums[mid] < target) { //target is in range mid and right
                    left = mid + 1;
                }
                else {
                    right = mid - 1; //target is in range mid and right
                }
            }
        }
        return -1;
    }


``` 

### 702. Search in a Sorted Array of Unknown Size

Given an integer array sorted in ascending order, write a function to search target in nums.  
If target exists, then return its index, otherwise return -1. 

However, the array size is unknown to you. 
You may only access the array using an ArrayReader interface, where ArrayReader.get(k) returns the element of the array at index k

- 二分查找，不知道upper边界，那么可以用双倍扩容法找到又边界再进行二分查找
 
```
/* interface ArrayReader {
 *     public int get(int index) {}
 * }
 */
 

class Solution {
    public int search(ArrayReader reader, int target) {
        //first define the binary search range, then do the bianry search
        int left = 0;
        int right = 1;
        while (reader.get(right) < target) {
            right *= 2;
        }
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            int num = reader.get(mid);
            if (num == target) {
                return mid;
            }
            if (num < target) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
```

## 模板2
- 寻找一个condition，并不是某一个直接的数字，比如找到min number larger than target， 或者是找到第一个出现的正数
- 左右条件不对等, 比如找比目标数字大的最小的数，如果mid比 target小，那么一定在mid + 1 ~ end, 但是如果mid比target大，可能在【0，mid】
- 寻找左边界
- loop condition : left < right
- search left : right = mid;
- search right: left = mid + 1;


- **注意由于找到的左边界，由于当出现两个（双数）mid的时候，int mid = (right - left) / 2 + left 帮我们选择了左边的一个，但是如果是要找右边界，就需要int mid = (right - left) / 2 + left + 1， 让这mid，成为右边那一个**

- 同理：寻找右边界ß
- loop condition : left < right
- search left : right = mid - 1;
- search right: left = mid;

### First Bad Version

Since each version is developed based on the previous version, all the versions after a bad version are also bad.

Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.

You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.


```

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int left = 0;
        int right = n;
        while (left < right) { //注意这里是left < right
            int mid = (right - left) / 2 + left;
            if (isBadVersion(mid)) {
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }
        return right;
    }
}


```
### 744. Find Smallest Letter Greater Than Target
> https://leetcode.com/problems/find-smallest-letter-greater-than-target/



### LC34. Find First and Last Position of Element in Sorted Array

> https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/

> Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

> Your algorithm's runtime complexity must be in the order of O(log n).

> If the target is not found in the array, return [-1, -1].

> Input: nums = [5,7,7,8,8,10], target = 8

> Output: [3,4]


- 分别找到左边界和右边界
- 注意左边界 int mid = (right - left) / 2 + left;
- 右边界 int mid = (right - left) / 2 + left + 1; 让双数的 mid 偏向后面

```
    public int[] searchRange(int[] nums, int target) {
        // 找到左边界然后找到右边界
        int[] arr = new int[2];
        int left = 0;
        int right = nums.length-1;
        
        //find left bound
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] < target) {
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }
        arr[0] = left < nums.length && nums[left] == target ? left : -1;
        
        // find right
        left = 0;
        right = nums.length - 1;
        while (left < right) {
            int mid = (right - left) / 2 + left + 1; // 让双数的 mid 偏向后面
            if (nums[mid] > target) {
                right = mid - 1;
            }
            else {
                left = mid;
            }
        }
        arr[1] = right >= 0 && nums[right] == target ? right : -1;
        return arr;
    }
  


```



### Doing Homework

- https://www.lintcode.com/problem/doing-homework/description

> n个人，他们每个人需要独立做m份作业。
> 第i份作业需要花费cost[i]的时间。由于每个人的空闲时间不同，第i个人有val[i]的时间，
> 这代表他做作业的总时间不会超过val[i]。每个人都按照顺序，从1号作业开始，然后做2号，3号...... 
> 现在，你需要计算出他们最多花了多少的时间。

```
样例

给定`cost=[1,2,3,5]`，`val=[6,10,4]`，返回`15`。
输入:
[1,2,3,5]
[6,10,4]
输出
15
    
```

- 用prefix sum array记录下cumulative sum
- 二分查找第一个小于等于v 的 sum
- 注意handle not found case;
    
```
    public long doingHomework(int[] cost, int[] val) {
        long[] prefixSum = new long[cost.length];
        long sum = 0;
        for (int i = 0; i < cost.length; i++) {
            sum += (long) cost[i];
            prefixSum[i] = sum;
        }
        long res = 0;
        for (int v : val) {
            //找prefix array <= v 的最大数
            int left = 0;
            int right = cost.length - 1;
            while (left < right) {
                int mid = (right - left) / 2 + left + 1;
                if (prefixSum[mid] <= v) {
                    left = mid;
                }
                else {
                    right = mid - 1;
                }
            }
            if (prefixSum[right] <= v) { //handle not found 
                res += prefixSum[left];
            }
        }
        return res;
    }
      
```
### LC 658 Find K Closest Elements

> Given a sorted array, two integers k and x, find the k closest elements to x in the array. 
> The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.


> Input: [1,2,3,4,5], k=4, x=3

> Output: [1,2,3,4]

- 由于数组是递增的，我们要找到一个长度为K的range，closest to k 这个左边界可能在0 ～ arr.length - k 之间
- case 1， X在range的中间，我们要找到这个range的左边界就可以， 也就是要找到左边界的第一个数，x 要位于左边界的中间至少靠左，所以要满足x - arr[mid] <= arr[mid+k] - x 
- 所以如果x - arr[mid] >  arr[mid+k] - x 那么mid一定不是左边界 左边界一定在mid右边，mid = left + 1; 由于要找到满足提议的最右左边界，else：  mid = right；

<img src="https://raw.githubusercontent.com/zeyao/TechNotes/master/Document/LC658.jpg" style="height:500px" />

- case 2： X在range的右边， x - arr[mid] >  arr[mid+k] - x 永远成立，于是一直收缩左边
- case 3   收缩right
- 所以无论如何 收缩边界利用 x - arr[mid] 比较  arr[mid+k] - x 都成立

``` 
    public List<Integer> findClosestElements(int[] arr, int k, int x) {        
        int left = 0;
        int right = arr.length - k;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (x - arr[mid] > arr[mid+k] - x) {
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int i = left; i < left + k; i++) {
            list.add(arr[i]);
        }
        return list;
    }

``` 

    
### Find Peak Element

A peak element is an element that is greater than its neighbors.

Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that nums[-1] = nums[n] = -∞.

> Input: nums = [1,2,3,1]

>Output: 2

>Explanation: 3 is a peak element and your function should return the index number 2.

- 一个数左边比他大，那么0 ～ i-1一定有个peak 同理一个数右边比他大【i+1，end】has peak

<img src="https://raw.githubusercontent.com/zeyao/TechNotes/master/Document/peakarray.jpg" style="height:500px" />

```
class Solution {
    public int findPeakElement(int[] nums) {
        // 一个数左边比他大，那么0 ～ i-1一定有个peak 同理一个数右边比他大【i+1，end】has peak
        //So:
        //如果nums[i] > nums[i+1] 题意 nums[-1] = nums[n] = -∞ ，说明 【0，i】 一定有peak
        //如果nums[i] < nums[i-1] 说明 【i+1, end】 一定有peak
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] > nums[mid+1]) { //【0 ～ mid】has peak， 防止越界，因为偶数个数情况下mid靠前。mid-1会越界
                right = mid;
            }
            else { 
                left = mid +1;
            }
        }
        return left;
    }
}
```

### Find Minimum in Rotated Sorted Array

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

Find the minimum element.

You may assume no duplicate exists in the array.


>Input: [3,4,5,1,2] 

>Output: 1

- 如果arr[mid] < arr[right] 找最小的，所以最小的肯定不在（1， end】 于是 right - mid
- else 说明arr[mid] > arr[right] 那么说明肯定在mid ～ right 之间有Rotated的点， 所以 left = mid + 1

```
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] < nums[right]) {
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }
        return nums[left];
    }

```



### 1. Leftmost Column with at Least a One

A binary matrix means that all elements are 0 or 1. For each individual row of the matrix, this row is sorted in non-decreasing order.

Given a row-sorted binary matrix binaryMatrix, return leftmost column index(0-indexed) with at least a 1 in it. If such index doesn't exist, return -1.

You can't access the Binary Matrix directly.  You may only access the matrix using a BinaryMatrix interface:

BinaryMatrix.get(x, y) returns the element of the matrix at index (x, y) (0-indexed).
BinaryMatrix.dimensions() returns a list of 2 elements [m, n], which means the matrix is m * n.
Submissions making more than 1000 calls to BinaryMatrix.get will be judged Wrong Answer.  Also, any solutions that attempt to circumvent the judge will result in disqualification.

For custom testing purposes you're given the binary matrix mat as input in the following four examples. You will not have access the binary matrix directly.

Eg: 

 [0,0,0,1]
 
 [0,0,1,1]
 
 [0,1,1,1]
 
Output : 1

- 二分查找每一行， 由于每一行都是升序排列， 二分查找找到第一个1；
- 在所有行数里面找懂最小的
- n log M

``` 
/**
 * // This is the BinaryMatrix's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface BinaryMatrix {
 *     public int get(int x, int y) {}
 *     public List<Integer> dimensions {}
 * };
 */

class Solution {
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> list = binaryMatrix.dimensions();
        int row = list.get(0);
        int col = list.get(1);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < row; i++) {
            int first = binarySearch(i, col, binaryMatrix);
            min = Math.min(min, first);
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
    
    private int binarySearch(int row, int col, BinaryMatrix binaryMatrix) {
        int left = 0;
        int right = col - 1;
        while (left < right) {
            int mid = (right - left) /2 + left;
            if (binaryMatrix.get(row, mid) >= 1) {
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }
        if (binaryMatrix.get(row, left) == 0) {
            return Integer.MAX_VALUE;
        }
        return left;
    }
}
``` 