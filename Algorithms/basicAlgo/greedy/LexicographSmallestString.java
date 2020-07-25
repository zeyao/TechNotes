import java.util.*;
//Given two integers N and K. 
//The task is to print the lexicographically smallest string of length N consisting of lower-case English alphabates such that the sum of the characters of the string equals to K 
//where ‘a’ = 1, ‘b’ = 2, ‘c’ = 3, ….. and ‘z’ = 26.
public class LexicographSmallestString {
	
	private static String findSmallestString(int n, int k) {
		char arr[] = new char[n];   
        Arrays.fill(arr, 'a'); 
		k -= n;
		for (int i = n - 1; i >= 0; i--) {
			if (k > 0) {
				if (k >= 25) {
					arr[i] = 'z';
					k -= 25;
				}
				else {
					arr[i] = (char)('a' + k);
					k = 0;
				}
			}
		}
		return new String(arr);
	}

	public static void main(String[] args) { 
		System.out.println(findSmallestString(5,42)); 
		System.out.println("xxxxxxxx"); 
		System.out.println(findSmallestString(3,25)); 
    } 
}
