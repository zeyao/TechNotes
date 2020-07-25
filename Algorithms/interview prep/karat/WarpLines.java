import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**


 * 
 */
public class WarpLines {

   //Pt.1 Connecting words with '-' as blank spaces, no exceeds maxLength
   //e.g. ["1p3acres", "is", "a", "good", "place", "to", "communicate"], 12 => {"1p3acres-is", "a-good-place", "for", "communicate"}
    public static List<String> warp(String[] arr, int maxLen) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (String str : arr) {
            if (sb.length() + 1 + str.length() <= maxLen) {
                if (sb.length() > 0) {
                    sb.append("-");
                }
                sb.append(str);
            }
            else {
                res.add(sb.toString());
                sb = new StringBuilder();
                sb.append(str);
            }
        }
        if (sb.length() > 0) {
            res.add(sb.toString());
        }
        return res;
    }

    //Pt.2 Require every line to be "balanced".
    //Input: String[] lines, ["the way it moves like me", "another sentence example",...], int maxLength.
    //Output: List lines.
    //e.g. ["123 45 67 8901234 5678", "12345 8 9 0 1 23"], 10 => {"123--45-67", "8901234", "5678-12345", "8-9-0-1-23"}
    //["123 45 67 8901234 5678", "12345 8 9 0 1 23"], 15 => {"123----45----67", "8901234----5678", "12345---8--9--0", "23"}


    public static List<String> warpBalance(String[] lines, int maxLength) {
        List<String> input = new ArrayList<>();
        for (String str : lines) {
            String[] arr = str.split(" ");
            for (String s : arr) {
                input.add(s);
            }
        }
        String[] inputArr = new String[input.size()];
        for (int i = 0 ; i< input.size(); i++) {
            inputArr[i] = input.get(i);
        }
        List<String> list = warp(inputArr, maxLength);
        List<String> res = new ArrayList<>();

        //balance it;
        for (String str : list) {
            int slashCount = 0;
            for (int i = 0; i < str.length(); i++) {
                if (i > 0 && str.charAt(i) == '-' && str.charAt(i-1) != '-') {
                    slashCount++;
                }                
            }
            if (slashCount == 0) {
                res.add(str);
                continue;
            }
            int add = maxLength - str.length();// slash we need to add
            int[] arr = new int[slashCount];
            int index = 0;
            while (add > 0) {
                arr[index]++;
                add--;
                index++;
                if (index == slashCount) {
                    index = 0;
                }
            }
            StringBuilder sb = new StringBuilder();
            index = 0;
            for (int i = 0; i < str.length(); i++) {
                if (i > 0 && str.charAt(i) == '-' && str.charAt(i-1) != '-') {
                    for (int j = 0; j < arr[index]; j++) {
                        sb.append("-");
                    }
                    index++;
                }
                sb.append(str.charAt(i));
            }
            res.add(sb.toString());
        }
        return res;
    }
    public static void main(String[] args) {    
        String[] arr = new String[]{"1p3acres", "is", "a", "good", "place", "to", "communicate"};
        System.out.println(warp(arr, 12));
        String[] arr2 = new String[]{"123 45 67 8901234 5678", "12345 8 9 0 1 23"};
        System.out.println(warpBalance(arr2, 10));
        System.out.println(warpBalance(arr2, 15));

    }
}

