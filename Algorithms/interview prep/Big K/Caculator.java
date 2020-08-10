import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Caculator {

    //Pt.1 Calculator without parenthesis, only +, -, non-negative ints
    public static int basicCalculator1(String str) {
        int sum = 0;
        int plus = 1;
        int i = 0;
        while (i < str.length()) {
            String numStr = "";
            while (i < str.length() && Character.isDigit(str.charAt(i))) {
                numStr += str.charAt(i);
                i++;
            }
            int num = Integer.parseInt(numStr);
            sum += plus * num;
            if (i >= str.length()) {
                break;
            }
 
            if (str.charAt(i) == '+') {
                plus = 1;
            }
            else if (str.charAt(i) == '-') {
                plus = -1;
            }
            i++;           
        }
        return sum;
    }

    //Pt.2 Calculator with parenthesis (LeetCode 224)
    public static int calculate2(String s) {
        int res = 0;
        int plus = 1;
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        while (i < s.length()) {
            if (Character.isDigit(s.charAt(i))) {
                String numStr = "";
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    numStr += s.charAt(i);
                    i++;
                }
                int num = Integer.parseInt(numStr);
                res += num * plus;                
            }
            if (i >= s.length()) {
                break;
            }
            if (s.charAt(i) == '+') {
                plus = 1;
            }
            else if (s.charAt(i) == '-') {
                plus = -1;
            }
            else if (s.charAt(i) == '(') {
                stack.add(res);
                stack.add(plus);
                plus = 1;
                res = 0;
            }
            else if (s.charAt(i) == ')') {
                int operator = stack.pop();
                int lastNum = stack.pop();
                res = res * operator + lastNum;
            }
            i++;
        }
        return res;
    }

    //p3 - handle + - * / 
    public static int calculate3(String s) {
        Stack<Integer> stack = new Stack<>();
        char operator = '+';
        int i = 0;
        while (i < s.length()) {
            String numStr = "";
            while (i < s.length() && (Character.isDigit(s.charAt(i)) || s.charAt(i) == ' ')) {
                numStr += s.charAt(i);
                i++;
            }
            int num = Integer.parseInt(numStr.trim());
            if (operator == '+') {
                stack.add(num);
            }
            else if (operator == '-') {
                stack.add(-1 * num);
            }
            else if (operator == '*') {
                stack.add(stack.pop() * num);
            }
            else if (operator == '/') {
                stack.add(stack.pop() / num);
            }
            
            if (i >= s.length()) {
                break;
            }
            operator = s.charAt(i);
            i++;
        }
        int res = 0;
        for (int n : stack) {
            res += n;
        }
        return res;
    }

    public static void main(String[] args) {    
        System.out.println(basicCalculator1("1+43+78") == 1+43+78);
        System.out.println(basicCalculator1("1+430-30+1") == 1+430-30+1);
        System.out.println(basicCalculator1("1+473+787+112+5645+7676+24123") == 1+473+787+112+5645+7676+24123);
        System.out.println(basicCalculator1("132+473+786667+1222+5645+7676+24123") == 132+473+786667+1222+5645+7676+24123);



        System.out.println(calculate2("132+473+786667-(1222-5645+7676)+24123") == 132+473+786667-(1222-5645+7676)+24123);
        System.out.println(calculate2("(1+132)-(473-7667)-(1222-5645+7676)+24123") == (1+132)-(473-7667)-(1222-5645+7676)+24123);
    }
}

