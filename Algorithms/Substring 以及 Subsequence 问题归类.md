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




