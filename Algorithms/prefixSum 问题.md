# prefix sum 问题





### Leetcode 560. Subarray Sum Equals K：

``` 
Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:
Input:nums = [1,1,1], k = 2
Output: 2
Note:
The length of the array is in range [1, 20,000].
The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].


```
#### 方法1 ： 计算 每一个 index 的 prefix sum 
 <img src="https://raw.githubusercontent.com/zeyao/TechNotes/master/Document/prefix1.jpg" style="height:500px" />

``` 
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        int sum = 0;
        int start = 0;
        for (; start < nums.length; start++) {
            prefixSumMap.put(start, sum);
            sum += nums[start];
        }
        prefixSumMap.put(start, sum);
        int count = 0;
        for (int i = 0 ; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int subArrSum = prefixSumMap.get(j+1) - prefixSumMap.get(i);
                if (subArrSum == k) {
                    count++;
                }
            }
        }
        return count;
    }

``` 

#### 方法2:

> 用map记录下index j 的prefix sum 作为 key, 和这个sum 出现的次数；
> 如果 到index i , map 出现了 sum - k, 说明 j - i 和为 k,那么 sum - k 出现的此时就是 i ~ j 和为 k 出现的次数

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
            if (map.containsKey(sum)) {
                Integer val = map.get(sum);
                val ++;
                map.put(sum, val);
            }
            else {
                map.put(sum, 1);
            }
        }
        return count;
        
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
 