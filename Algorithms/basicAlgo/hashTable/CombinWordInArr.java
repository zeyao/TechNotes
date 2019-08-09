package hashTable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CombinWordInArr {
	/**
	 * 算法 2.5.2 从标准输入读入一列单词并打印出所有有两个单词组成的单词，比如after, thought, afterthought
	 *  
	 */
	public static void main(String[] args) {
		String[] arr = {"after", "thought", "afterthought"};
		System.out.println(findWord(arr));
	}
	private static List<String> findWord(String[] arr) {
		List<String> list = new ArrayList<>();
		Set<String> set = new HashSet<>();
		for (int i = 0 ; i < arr.length; i++) {
			for (int j = i + 1 ; j < arr.length; j++) {
				set.add(arr[i] + arr[j]);
				set.add(arr[j] + arr[i]);				
			}
		}
		
		for (String s : arr) {
			if (set.contains(s)) {
				list.add(s);
			}
		}
		return list;
	}
}
