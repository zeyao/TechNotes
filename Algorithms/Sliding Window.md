
# Sliding Window 问题


## Window 内至多至少元素的题型

- 有一种Sliding Window的题型是计算包含最多K个元素，求window长度或者，个数
- 经典题目有：
- LC340 https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
- LC1248 https://leetcode.com/problems/count-number-of-nice-subarrays/
- LC992 https://leetcode.com/problems/subarrays-with-k-different-integers/
- LC159 https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/
- LC 76 https://leetcode.com/problems/minimum-window-substring/

- 这种类型的题关键点在于，**找到并保持 window 里的元素满足条件要求**，（不超出条件的要求），在移动的过程中对**满足条件的每一种可能进行计算**，如果right pointer移动过程中，发现超出题意要求，移动left pointer直到window 回归到满足要求过程

#### LC340 Longest Substring with At Most K Distinct Characters
- Given a string, find the length of the longest substring T that contains at most k distinct characters.

``` 
Input: s = "eceba", k = 2
Output: 3
Explanation: T is "ece" which its length is 3.

``` 

- 这道题要求，window内的distinct character 数量最多K个，那么我们用map计数，map size就是 unique key的数量就是 distinct character的数量，所以当map size 超出 K, left pointer 移动让左边的元素离开window直到继续满足条件（window内的distinct character 数量最多K个）, 那么当满足条件时候，每一个 right - left + 1 就是一个substring 长度的可能性

 <img src="https://raw.githubusercontent.com/zeyao/TechNotes/master/Document/LC340.jpg" style="height:450px" />
 
 ``` 

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (k == 0) return 0;
        Map<Character, Integer> map = new HashMap<>();
        char[] arr = s.toCharArray();
        int left = 0;
        int maxLen = 0;
        for (int right = 0; right < arr.length; right++) {
            if (!map.containsKey(arr[right])) {
                map.put(arr[right], 1);
            }
            else {
                int count = map.get(arr[right]);
                count++;
                map.put(arr[right], count);
            }
            
            while (map.size() > k) { //当map size > k 不满足条件了，left pointer移动直到再次满足条件
                int count = map.get(arr[left]);
                count--;
                if (count == 0) {
                    map.remove(arr[left]);
                }
                else{
                    map.put(arr[left], count);
                }
                left++;
            }
            maxLen = Math.max(right - left + 1, maxLen);
        }
        return maxLen;
    }
``` 

#### LC992 Subarrays with K Different Integers

- Given an array A of positive integers, call a (contiguous, not necessarily distinct) subarray of A good if the number of different integers in that subarray is exactly K.

```
Input: A = [1,2,1,2,3], K = 2
Output: 7
Explanation: Subarrays formed with exactly 2 different integers: 
[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].

```

-  这道题要找 K Different Integers Subarray的个数，exactly K Different Integers Subarray的个数 = at most K Different Integers Subarray的个数 - at most K-1 Integers Subarray 个数， 这就类似于 LC340， 计算at most 问题的sliding window, 依旧是保证window 内 不超过 K Different Integers， 如果超过这个要求，left pointer 向右移动

```
class Solution {
    public int subarraysWithKDistinct(int[] A, int K) {
        return atMostk(A, K) - atMostk(A, K - 1);
    }
    
    private int atMostk(int[] A, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        int sum = 0;
        int left = 0;
        for (int right = 0 ; right < A.length; right++) {
            if (!map.containsKey(A[right])) {
                map.put(A[right], 1);
            }
            else {
                int count = map.get(A[right]);
                count++;
                map.put(A[right], count);
            }
            while (map.size() > k) {
                int count = map.get(A[left]);
                count--;
                if (count == 0) {
                    map.remove(A[left]);
                }
                else {
                    map.put(A[left], count);
                }
                left++;
            }
            sum += right - left + 1;
            //计算移动过程中 left 到 right subarray 个数
        }
        return sum;
    }
}
```
-  如何计算left - right 移动过程中 sub array 个数的总和

 <img src="https://raw.githubusercontent.com/zeyao/TechNotes/master/Document/sunarraycount.jpg" style="height:450px" />



