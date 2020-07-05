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
		int max = 0;
   		int[][] dp = new int[A.length()+1][B.length()+1];
      	for (int i = 0 ; i < A.length; i++) {
            for (int j = 0 ; j < B.length; j++) {
                if (A.charAt(i) == B.charAt(j)) {
                    dp[i+1][j+1] = dp[i][j] + 1;
                    max = Math.max(dp[i+1][j+1], max);
                }
            }
      	}
     	return max;
    }

```


### 2. Longest common Subsequence：

> http://leetcode.com/problems/longest-common-subsequence/

```

	Input: "ABCD" and "EACB"
	Output:  2
	
	Explanation: 
	LCS is "AC"
	
```

- 如果 A.charAt(i) == B.charAt(j) 相当于 那么 i，j 都在LCS之中，都要选，那么dp[i][j] = dp[i-1][j-1] + 1 
- 如果i，j 不相等，那么我们不可以同时选i j 构建LCS， 要选i j其中一个 或者都不选，选取其中最大的结果
- 如果选i 不选 j ，说明i可能和j之前的序列构成LCS，相当于要沿用 dp[i][j-1] 的结果
- 如果选j不选 i 沿用 dp[i-1][j]的结果
- 都不要 dp[i-1][j-1]， 但这个结果肯定是最小就不用写在代码里面了


<img src="https://raw.githubusercontent.com/zeyao/TechNotes/master/Document/LCS.jpg" style="height:500px" />

```
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m+1][n+1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i+1][j+1] = dp[i][j] + 1;
                }
                else {
                    dp[i+1][j+1] = Math.max(dp[i][j+1], dp[i+1][j]);   
                }
            }
        }
        return dp[m][n];
    }

```


### 3.  Longest Palindromic Substring 

> https://leetcode.com/problems/longest-palindromic-substring/

```
Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.

```


- 如果char at i = j -> dp[i+1][j-1]是回文 那么dp[i][j]也是
- 例如 baab aa是回文 那么 baab也是回文
- 如果 i - j < 2 那么只需要判断 char at i = j

```
    public String longestPalindrome(String s) {
        int n = s.length();
        if (s == null || s.length() == 0) return s;
        boolean[][] dp = new boolean[n][n];
        String res = "";
        //[j, i]
        for (int i = 0 ; i < n; i++) {
            for (int j = i; j >= 0; j--) {
                if (s.charAt(i) == s.charAt(j) && (i - j < 2 || dp[j+1][i-1])) {
                    dp[j][i] = true;
                    if (i - j + 1 > res.length()) {
                        res = s.substring(j, i+1);
                    }
                }
            }
        }
        return res;
    }

```