package stack;

import java.util.Stack;
/**
 * https://leetcode-cn.com/circle/discuss/eXOcnD/
 * 
 * 在你的面前从左到右摆放着n根长短不一的木棍，你每次可以折断一根木棍，并将折断后得到的两根木棍一左一右放在原来的位置
 * 即若原木棍有左邻居，则两根新木棍必须放在左邻居的右边，若原木棍有右邻居，新木棍必须放在右邻居的左边，所有木棍保持左右排列）。
 * 折断后的两根木棍的长度必须为整数，且它们之和等于折断前的木棍长度。你希望最终从左到右的木棍长度单调不减，那么你需要折断多少次呢？
 * 
 * 
 * 
 *
 */
public class BreakNumber {
	//输入：[3,13,60,7] -> 10
	//从后往前，进入单调递减栈，为什么从后往前呢，因为大的数可以break, 所以要从后到前保持单调递减
	//然后这个数字如果比stack.peek()大，就要对折，折到最接近stack.peek()就是最少次数
	//因为要保持单调性，折的最小的要尽可能大，所以要折的尽可能平均
	public static int breakNum(int[] arr) {
		int count = 0;
		Stack<Integer> stack = new Stack<>();
		for (int i = arr.length - 1; i >= 0; i--) {
			if (!stack.isEmpty() && stack.peek() < arr[i]) {
				int target = arr[i];
				if (target % stack.peek() == 0) {
					count += (target / stack.peek()) -1;
					stack.add(stack.peek());
					// 63 -> break to 7 7 7 7 7 7 7 7 7
				}
				else {
					count += target / stack.peek(); //配比尽量平均
					//60 break to 6 6 7 7 7 7 7 7
					stack.add(stack.peek() - 1);
				}
				continue;
			}
			stack.add(arr[i]);
		}
		return count;
	}
	
	public static void main(String[] args) {
		int[] arr1 = {3, 13, 60, 7};
		System.out.println(breakNum(arr1));
		int[] arr2 = {3,5,13,9,12};
		System.out.println(breakNum(arr2));
		int[] arr3 = {3,12,13,9,12};
		System.out.println(breakNum(arr3));
		int[] arr4 = {3,63,7};
		System.out.println(breakNum(arr4));
	}
}
