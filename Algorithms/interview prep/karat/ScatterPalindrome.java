import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class ScatterPalindrome {
 /**
  * 
    Find all scatter palindrome strings inside given string. A scatter palindrome is defined as a string in which characters can be shuffled to obtain a palindrome.

    Example:

    Input: "aabb"
    Output: [a, aa, aab, aabb, abb, b, bb]
  */
    
    private static List<String> findScatterPalindrome(String s) {
        List<String> res = new ArrayList<>();
        dfs(res, 0, s, "");
        return res;
    }

    private static void dfs(List<String> res, int start, String s, String target) {
        if (target.length() > 0 && isValid(target)) {
            res.add(target);
        }
        for (int i = start; i < s.length(); i++) {
            if (i > start && s.charAt(i) == s.charAt(i-1)) {
                continue;
            }
            dfs(res, i+1, s, target + s.charAt(i));
        }
    }

    private static boolean isValid(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c-'a']++;
        }
        int odd = 0;
        for (int c : count) {
            if (c % 2 != 0) {
                odd++;
            }
            if (odd > 1) {
                return false;
            }
        }
        return true;
     }
                
    public static void main(String[] args) {    
        System.out.println(findScatterPalindrome("aabb"));
    }
}

