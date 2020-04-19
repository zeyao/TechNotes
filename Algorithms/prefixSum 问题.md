# prefix sum 问题

- prefix sum问题 经常需要用一个 当前index的sum （或者按照题意的counter）在hash map当作key
- 通过这个key可以间接计算出结果


### Leetcode 560. Subarray Sum Equals K：

``` 
Given an array of integers and an integer k, 
you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:
Input:nums = [1,1,1], k = 2
Output: 2
Note:
The length of the array is in range [1, 20,000].
The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].


```

- 用map记录下index j 的prefix sum 作为 key, 和这个sum 出现的次数；
- 如果 到index i , map 出现了 sum - k, 说明 j - i 和为 k,那么 sum - k 出现的此时就是 i ~ j 和为 k 出现的次数
- 这里用到了当前index和sum 作为prefix sum key

 <img src="https://raw.githubusercontent.com/zeyao/TechNotes/master/Document/prefix2.jpg" style="height:500px" />
 
 ``` 
     public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        //记录下index i 的 prefix sum 作为 k, 和这个sum 出现的次数；
        int count = 0;
        int sum = 0;
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            // 如果 0 ～ j的prefix sum 为 sum - k 那么  j ~ i的和就是 K
            // 由于两段subarray和为sum, 那么0 ~ j 和为sum - k 的可能此时就是 j ~ i 和为 k 的可能次数
            if (!map.containsKey(sum)) {
                map.put(sum, 1);
            }
            else {
                map.put(sum, map.get(sum) + 1);
            }
        }
        return count;
        
    }
    
 ```
 
### 325. Maximum Size Subarray Sum Equals k 

```
Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.

Input: nums = [1, -1, 5, -2, 3], k = 3
Output: 4 
Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.

```

- 要找到max subarray len sum to K, 同样的道理用sum做prefix sum, 如果map.containsKey(sum - k) -> 这个index ～ i的sum 就是k
- 要找最长的，所以只有当map里没有prefix key的时候，存入最左index

```
    public int maxSubArrayLen(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int len = 0;
        int sum = 0;
        for (int i = 0 ; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                len = Math.max(len, i - map.get(sum - k));
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return len;
    }
    
```
 
### LC 525. Contiguous Array

```

Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

Input: [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
 
``` 

 <img src="https://raw.githubusercontent.com/zeyao/TechNotes/master/Document/LC525.jpg" style="height:500px" />
 
- 要找到subarray with equal number 0 and 1, 用map记录下每个index 0， 1 的dif
- 如果map.containsKey(dif) -> 说明dif存在， 那么 最早dif存在的index和current index之间dif = 0;
- 这里利用了当前index 的dif作为prefix sum，作为Key
- 这种题目要找最长的len, 所以prefix key只put一次，(!map.containsKey(dif) 时候) 因为最先put的就是最左的index


 ``` 
     public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int dif = 0;
        map.put(0, -1);
        int len = 0;
        for (int i = 0 ; i < nums.length; i++) {
            if (nums[i] == 0) {
                dif++;
            }
            else {
                dif--;
            }
            if (!map.containsKey(dif)) {
                map.put(dif, i);
            }
            else {
                len = Math.max(len, i - map.get(dif));
            }
        }
        return len;
    }
    
 ``` 
 
 
 
### 1124. Longest Well-Performing Interval
 
 
 ```
We are given hours, a list of the number of hours worked per day for a given employee.

A day is considered to be a tiring day if and only if the number of hours worked is (strictly) greater than 8.

A well-performing interval is an interval of days for which the number of tiring days is strictly larger than the number of non-tiring days.

Return the length of the longest well-performing interval.

Input: hours = [9,9,6,0,6,6,9]
Output: 3
Explanation: The longest well-performing interval is [9,9,6].


 ```
 
- LC525 的变种， 要计算出dif at least > 1; 525 是计算dif = 0; 同理可以做at least k
- 一个道理， 需要用 dif作为prefix sum key
- 如果dif > 0 说明从0 到index i， subarray dif > 0 -> len = i + 1;
- 如果dif < 0; 那么应用prefix sum 的思路， map没有dif 存下dif and index,
- if (map.containsKey(dif - 1)) -> 那么从这个dif - 1 的index ~ i -> dif 是大于 1的; （这里可以K）

<img src="https://raw.githubusercontent.com/zeyao/TechNotes/master/Document/LC1124.jpg" style="height:500px" />

```
    public int longestWPI(int[] hours) {
        Map<Integer, Integer> map = new HashMap<>();
        int dif = 0;
        int len = 0;
        for (int i = 0 ; i < hours.length; i++) {
            if (hours[i] > 8) {
                dif++;
            }
            else {
                dif--;
            }
            
            if (dif > 0) { 
                len = i + 1;
            }
            else {
                if (!map.containsKey(dif)) {
                    map.put(dif, i);
                }
                if (map.containsKey(dif - 1)) { // follow up 如果larger than k,  map.containsKey(dif - k)
                    len = Math.max(len, i - map.get(dif-1));
                }
            }
        }
        return len;
    }
```
 
### Leetcode 1171. Remove Zero Sum Consecutive Nodes from Linked List

``` 
Given the head of a linked list, we repeatedly delete consecutive sequences of nodes that sum to 0 until there are no such sequences.

After doing so, return the head of the final linked list.  You may return any such answer.

 

(Note that in the examples below, all sequences are serializations of ListNode objects.)
``` 


 <img src="https://raw.githubusercontent.com/zeyao/TechNotes/master/Document/LC1171.jpg" style="height:500px" />
 
 > 如果一个node A prefix sum = n , node B prefix sum = n 那么 A 到 B 之间sum 就是 0， 所以可以把prefix sum 当作 key, node当作value放进map， 如果找到， A.next = B.next;
 
 ```
public ListNode removeZeroSumSublists(ListNode head) {
        Map<Integer, ListNode> map = new HashMap<>();
        ListNode tmp = head;
        int sum = 0;
        while (tmp != null) {
            sum += tmp.val;
            if (sum != 0) {
                map.put(sum, tmp);
            }    
            tmp = tmp.next;
        }
        
        tmp = head;
        sum = 0;
        while (tmp != null) {
            sum += tmp.val;
            if (sum == 0) {
                head = tmp.next;
            }            
            if (map.containsKey(sum)) {
                ListNode nextNode = map.get(sum);
                tmp.next = nextNode.next;               
            }
            tmp = tmp.next;
        }
        return head;
    }
```  
 