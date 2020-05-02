package dp;

import java.util.HashMap;
import java.util.Map;

public class VowelsLongestSubsequence {
	/**
	 * Given a string, determine the length of the longest subsequence that contains all the vowels in order.
	 * For example, the string aeeiiouu contains all the vowels in order. The string aeeiiaou does not because of the ‘a’ at position 5, (0 based indexing). 
	 * The first string is the longest subsequence of the second string that contains all vowels in order.
	 * 
	 * increasing subsequences and must have all the characters in the giving scope
	 * 
	 */
	
    public static int longestVowelSubsequence(String s) {
    // Write your code here
    //a e i o u -> must follow sequence to be both increase and having all five char;
    //before e must be a or e;
        Map<Character, Character> map = new HashMap<>();
        map.put('a', 'a');
        map.put('e', 'a');
        map.put('i', 'e');
        map.put('o', 'i');
        map.put('u', 'o');
        int max = 0;
        int len = s.length();
        int[] dp = new int[len];
        char[] nums = s.toCharArray();
        for (int i = 0; i < len; i++) {
            if (nums[i] == 'a') {
                dp[i] = 1;
            }
        }
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] == 0) continue;
                if (nums[i] == nums[j] || map.get(nums[i]) == nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            if (nums[i] == 'u') {
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }
}
