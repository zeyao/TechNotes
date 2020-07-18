package stack;

import java.util.Stack;

public class PostfixToPrefix {
	
	//ABC/-AK/L-*   -> *-A/BC-/AKL
	public static String convertPostFixToPrefix(String str) {
		Stack<String> stack = new Stack<>();
		for (char c : str.toCharArray()) {
			if (c == '+' || c == '-' || c == '*' || c == '/') {
				String first = stack.pop();
				String second = stack.pop();
				stack.add(c + second + first);
			}
			else {
				stack.add(String.valueOf(c));
			}
		}
		return stack.pop();
	}

	//*-A/BC-/AKL -> ABC/-AK/L-*
	public static String convertPrefixToPostFix(String str) {
		Stack<String> stack = new Stack<>();
		for (int i = str.length()-1; i >= 0; i--) {
			char c = str.charAt(i);
			if (c == '+' || c == '-' || c == '*' || c == '/') {
				String first = stack.pop();
				String second = stack.pop();
				stack.add(first + second + c);
			}
			else {
				stack.add(String.valueOf(c));
			}
		}
		return stack.pop();
	}
	
	
	public static void main(String[] args) {
		System.out.println(convertPostFixToPrefix("ABC/-AK/L-*"));
		System.out.println(convertPrefixToPostFix(convertPostFixToPrefix("ABC/-AK/L-*")));
	}
}
