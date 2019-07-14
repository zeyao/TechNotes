package dp;

public class LongestCommonSubstring {
/**
 * 
 * @param A
 * @param B
 * dp[i][j] 记录下 A index i, B index j 的longest common substring
 */
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
}
