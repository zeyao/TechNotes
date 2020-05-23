
# Sliding Window 问题


## substring Window 满足（至少）包含一定数量的条件问题

- 有一种Sliding Window的题型是计算包含最多K个元素，exactly K个元素，不少于target里的多少个元素，求window长度或者，总个数
- 这种题本质是 条件需要是window的一个子集
- 经典题目有：
- LC340 https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
- LC1248 https://leetcode.com/problems/count-number-of-nice-subarrays/
- LC992 https://leetcode.com/problems/subarrays-with-k-different-integers/
- LC159 https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/
- LC 76 https://leetcode.com/problems/minimum-window-substring/
- LC1358  https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/

- 这种类型的题关键点在于，**找到并保持 window 里的元素满足条件要求**，在移动的过程中对**满足条件的每一种可能进行计算**，如果right pointer移动过程中 （即增大window），发现己经满足了题意条件要求，就开始移动left pointer （缩短window) ，查看是否存在更优（或者更多）可能的解，直到window不满足条件要求，就停止移动left pointer，这样通过right pointer的对window的扩张，和left pointer 对window的夹逼，可以找到最优（所有）满足条件的window

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



#### LC76. Minimum Window Substring
- Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

```
Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"

```

- 要保证 windown 满足条件，**拥有T所有的元素 并且个数大于等于T**， 所以需要两个Map来计数， Map dic 计算T 各个字母的 count, Map window 计算当下 left - right 之间window 的每一个字母的数量，在保证 windown valid的时候，计算出每一个可能的 left 到 right substring，并选其中最短的substring  ***windown valid的条件就是 map window size =  map dic size, 并且每一个字母的 count 不小于dic 内的count***
- 当 window 已经满足条件的时候，因为要找到最小的substring, 所以可以把left++， 直到window不满足条件
- 这个移动left pointer 的过程，就是寻找min substring 并且满足条件的过程，所以可以比较得出min substring
- 不需要每个left - right 都计算substring, 只需要比较长度，记下substring 的 leftBound rightBound,最后返回 sunstring即可
- 例如S = AAAAAAAAAAAAB  T = AB right pointer 遇到第一个B才满足**拥有T所有的元素 并且个数大于等于T**， 满足条件后，left 夹逼向右直到不满足，这样就可以得到min window sustring



```
class Solution {
    public String minWindow(String s, String t) {
        int minLeft = -1;
        int minRight = s.length();
        
        Map<Character, Integer> map = new HashMap<>();
        Map<Character, Integer> dic = new HashMap<>();
        char[] arrt = t.toCharArray();
        for (char c : arrt) {
            if (dic.containsKey(c)) {
                dic.put(c, dic.get(c) + 1);
            }
            else {
                dic.put(c, 1);
            }
        }
        
        char[] arr = s.toCharArray();
        int left = 0;
        for (int right = 0; right < arr.length; right++) {
            if (dic.containsKey(arr[right])) {
                if (!map.containsKey(arr[right])) {
                    map.put(arr[right], 1);
                }
                else {
                    map.put(arr[right], map.get(arr[right]) + 1);
                }
            }
            
            while (checkHasAllChar(map, dic)) {
                    //we find the string has all char, then move left to find min, till we still have all char in window
                if (!map.containsKey(arr[left])) {
                    left++;
                    continue;
                }
                int count = map.get(arr[left]);
                count--;
                if (count == 0) {
                    map.remove(arr[left]);
                }
                else {
                    map.put(arr[left], count);
                }
                left++;                
                int leftBound = left - 1;
                int rightVound = right + 1;
                //possibleStr = s.substring(left-1, right+1); 
                //check is the new possible substring len < current
                
                if (rightVound - leftBound < minRight - minLeft) {
                    minLeft = leftBound;
                    minRight = rightVound;
                }
            }               
        }        
        if (minLeft == -1) return "";
        return s.substring(minLeft, minRight);
    }
    
    private boolean checkHasAllChar(Map<Character,Integer> target, Map<Character,Integer> dic) {
        //由于 entry 数量顶多 26个 所以还是check All char 方法复杂度是 constant
        if (target.size() < dic.size()) {
            return false;
        }
        for (Map.Entry<Character, Integer> entry : target.entrySet()) {
            Character targetKey = entry.getKey();
            if (entry.getValue() < dic.get(targetKey)) {
                return false;
                //char amount in window is less than dic
            }
        }
        return true;
    }
}
```



#### LC 1358. Number of Substrings Containing All Three Characters

- At least contains 问题，那么要满足window里保证有 All Three Characters, 当没有right++, 有了left++;
- 找到All Three Characters之前，right++;
- 找到了那么left-- 直到 left ~ right 之间的substring不包含 All Three Characters；
- 这过程中时候以left ~ right为substring的string，都满足题意，所以个数是s.length() - right;

<img src="https://raw.githubusercontent.com/zeyao/TechNotes/master/Document/LC1358.jpg" style="height:450px" />

```
    public int numberOfSubstrings(String s) {
        int left = 0;
        int[] count = new int[26];
        int res = 0;
        for (int right = 0; right < s.length(); right++) {
            char r = s.charAt(right);
            count[r - 'a']++;
            while (count[0] > 0 && count[1] > 0 && count[2] > 0) {
                res += s.length() - right;
                char l = s.charAt(left);
                count[l - 'a']--;
                left++;
            }
        }
        return res;
    }

```


