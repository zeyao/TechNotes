# Substring 以及 Subsequence 问题归类


Substring 以及 Subsequence 这一类问题很多情况下要用到动态规划，用dp[i][j]储存string A index i 到 String B index j 的一个状态，然后分析具体题目找到递推关系

### 1. Longest common substring：


> LintCode 79 https://www.lintcode.com/problem/longest-common-substring/description
 
``` 
 Given two strings, find the longest common substring.
 Return the length of it.
 Example
 Input:  "ABCD" and "CBCE"
 Output:  2
 Explanation:
 Longest common substring is "BC"

``` 
- 这道题其实分析一下要找到最长的common substring,我们可以用 dp[i][j]储存string A index i 到 String B index j， 存在的公共Substring的长度
- 可以分析到如果 A.charAt(i) == B.charAt(j) 那么 dp[i][j] = dp[i-1][j-1] + 1 ，如果不相等，到这里应归零；
- 当i = 0 或者 j = 0 的时候，如果不存在累积状态，那么当 i = 0; 如果 A.charAt(0) == B.charAt(j) 那么 dp[0][j] = 1;反之 dp[0][j] = 0;
- 最后找到所有dp里最大的数就可以了

于是有：

```
	public int longestCommonSubstring(String A, String B) { 
        if (A.length() == 0 || B.length() == 0) return 0;
        
        int[][] dp = new int[A.length()][B.length()];
        char[] arrA = A.toCharArray();
        char[] arrB = B.toCharArray();
        
        for (int i = 0 ; i < arrA.length; i++) {
            if (arrA[i] == arrB[0]) {
                dp[i][0] = 1;    
            }
            else {
                dp[i][0] = 0;
            }
        }
        
        for (int j = 0 ; j < arrB.length; j++) {
            if (arrB[j] == arrA[0]) {
                dp[0][j] = 1;
            }
            else{
                dp[0][j] = 0;
            }
            
        }

        for (int i = 1 ; i < arrA.length; i++) {
            for (int j = 1 ; j < arrB.length; j++) {
                if (arrA[i] == arrB[j]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                else {
                    dp[i][j] = 0;
                }
            }
        }
        
        int max = 0;
        for (int i = 0 ; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                max = Math.max(dp[i][j],max);
            }
        }
        return max;
    }

```


### 2. Longest common Subsequence：

> LintCode 77 https://www.lintcode.com/problem/longest-common-substring/description

```

	Input: "ABCD" and "EACB"
	Output:  2
	
	Explanation: 
	LCS is "AC"
	
```
- 这道题其实分析一下要找到最长的common Subsequence, 和第一题 最长common substring 很类似
- 不同点在于要搞清楚Subsequence 的概念， 如果 A.charAt(i) == B.charAt(j) 那么 dp[i][j] = dp[i-1][j-1] + 1 
- 如果不相等，因为Subsequence可以跳过不相等的index,所以需要沿用之前的结果，不归零， 而是需要沿用之前 最长的 Subsequence， 那么之前最长的在哪里呢, 因为dp是累加状态，所以，一定在 dp[i-1][j], dp[i][j-1] 的其中一个，取最大即可
- 由于是累加状态，不需要再遍历一遍找到MAX， dp[i][j]就是累加下最大的 common Subsequence

```
	public int longestCommonSubsequence(String A, String B) {
        if (A.length() == 0 || B.length() == 0) return 0;
        int[][] dp = new int[A.length()][B.length()];
        char[] arrA = A.toCharArray();
        char[] arrB = B.toCharArray();
        
        if (arrA[0] == arrB[0]) {
            dp[0][0] = 1;
        }
        else {
            dp[0][0] = 0;
        }
        
        for (int i = 1 ; i < arrA.length; i++) {
            if (arrA[i] == arrB[0]) {
                dp[i][0] = 1;    
            }
            else {
                dp[i][0] = dp[i-1][0];
            }
        }
        
        for (int j = 1 ; j < arrB.length; j++) {
            if (arrB[j] == arrA[0]) {
                dp[0][j] = 1;
            }
            else{
                dp[0][j] = dp[0][j-1];
            }
            
        }
        
        for (int i = 1 ; i < arrA.length; i++) {
            for (int j = 1 ; j < arrB.length; j++) {
                if (arrA[i] == arrB[j]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);                
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }

```