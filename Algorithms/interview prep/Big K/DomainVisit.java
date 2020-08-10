import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class DomainVisit {

    public static List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> map = new HashMap<>();
        for (String s : cpdomains) {
            String[] arr = s.split("\\s+");
            int count = Integer.parseInt(arr[0]);
            String[] domainArr = arr[1].split("\\.");
            String str = "";
            for (int i = domainArr.length - 1; i >= 0; i--) {
                str = domainArr[i] + str;
                if (!map.containsKey(str)) {
                    map.put(str, count);
                }
                else {
                    map.put(str, map.get(str) + count);
                }
                str = "." + str;
            }
        }
        List<String> res = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String val = entry.getValue() + " " + entry.getKey();
            res.add(val);
        }
        return res;
    }
   
    // Pt.2 Longest Continuous Common History: Given visiting history of each user, 
    // find the longest continuous common history between two users. (LeetCode 718, dp)
    // [["3234.html", "xys.html", "7hsaa.html"], // user1
    //   ["3234.html", "sdhsfjdsh.html", "xys.html", "7hsaa.html"] // user2
    // out put "xys.html", "7hsaa.html"]
    public static List<String> longestCommonVisitedDomain(String[] A, String[] B) {
        int max = 0;
        int left = -1;
        int right = -1;
        int[][] dp = new int[A.length+1][B.length+1];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                if (A[i].equals(B[j])) {
                    dp[i+1][j+1] = dp[i][j] + 1;
                    if (max < dp[i+1][j+1]) {
                        max = dp[i+1][j+1];
                        left = i;
                        right = j;
                    }
                }
            }
        }
        List<String> res = new LinkedList<>();
        for (int k = 0; k < max; k++) {
            String s = B[right];
            res.add(0, s);
            right--;
        }
        return res;
    }
    public static void main(String[] args) {  
        String[] A = {"3234.html", "xys.html", "7hsaa.html"};
        String[] B = {"3234.html", "sdhsfjdsh.html", "xys.html", "7hsaa.html"};
        System.out.println(longestCommonVisitedDomain(A, B));

        String[] A1 = {"A"};
        String[] B1 = {"C"};
        System.out.println(longestCommonVisitedDomain(A1, B1));

        String[] A2 =  {"1","2","3","2","1"};
        String[] B2 = {"3","2","1","4","7"};
        System.out.println(longestCommonVisitedDomain(A2, B2));
    }
}

