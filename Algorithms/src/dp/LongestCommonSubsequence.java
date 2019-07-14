package dp;

public class LongestCommonSubsequence {
	/**
	 * 
	 * LintCode 77 https://www.lintcode.com/problem/longest-common-substring/description
	 * Given two strings, find the longest common Subsequence.
	 * Return the length of it.
	 * Example
	 * Input: "ABCD" and "EACB"
	 * Output:  2
	 * Explanation: 
	 * LCS is "AC"
	 * dp[i][j] 记录下 A index i, B index j 的longest common Subsequence
	 */
	public int longestCommonSubsequence(String A, String B) {
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
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                    //不同的是如果的 找 substring dp[i][j] = 0;
                    //因为subSequence arrA[i] ！= arrB[j]的时候，不必归零，而是需要沿用之前 最长的 Subsequence， 保持不变就行了
                    //那么之前最长的在哪里呢,一定在 dp[i-1][j], dp[i][j-1] 其中一个，取最大即可
                }
            }
        }
        
        /*
        int max = 0;
        for (int i = 0 ; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                max = Math.max(dp[i][j],max);
            }
        }
        return max;
        其实没必要再遍历一边，因为状态是叠加的，所以dp的最后一个就是是最大值。
        */
        return dp[dp.length-1][dp[0].length-1];
    }
}
