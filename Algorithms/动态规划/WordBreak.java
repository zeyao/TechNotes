import java.util.*;

/**
 * 
 * "aebbbbs"
    ["a","aeb","bbs", "bbbs"]
 *
 */
public class WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(s.substring(j,i))) {
                    dp[i] = true;
                }
            }
        }
        return dp[dp.length-1];
    }

    //follow up, print one possible solution
    public String wordBreakFollowup(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        String[] dp = new String[s.length()+1];
        Arrays.fill(dp, "");
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                if (j == 0 && set.contains(s.substring(j,i))) {
                    dp[i] = s.substring(j,i);
                }
                else if (dp[j].length() > 0 && set.contains(s.substring(j,i))) {
                    dp[i] = dp[j] + "," + s.substring(j,i);
                }
            }
        }
        return dp[dp.length-1];
    }
}