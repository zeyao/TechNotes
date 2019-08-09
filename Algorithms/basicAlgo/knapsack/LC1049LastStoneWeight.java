package knapsack;

import java.util.HashMap;
import java.util.Map;

public class LC1049LastStoneWeight {
	
	public static void main(String[] args) {
		LC1049LastStoneWeight test = new LC1049LastStoneWeight();
		int[] stones = {2,7,4,1,8,1};
		int result = test.lastStoneWeightII(stones);
		System.out.println(result);
	}
	

	public int lastStoneWeightII(int[] stones) {
		/**
		 * 选一个石头，可以现在碰，也可以当下不碰， 碰的话就是sum = Math.abs(sum - stones); 不碰sum +=
		 * stone 所以是背包问题； 我们要算这个index 和给入 sum 时候的最小值，这个值可能已经算过了，所以我们把这个计算过的存起来,
		 * 所以我们可以用 index + sum 当作key
		 */
		Map<String, Integer> map = new HashMap<>();
		return find(stones, 0, 0, map);

	}

	private int find(int[] stones, int index, int sum, Map<String, Integer> map) {
		if (index == stones.length) {
			return sum;
		}

		String key = index + " " + sum; // use index + sum as key
		if (map.containsKey(key)) {
			return map.get(key);
		}
		// not crash
		int sumNo = find(stones, index + 1, sum + stones[index], map);
		// crash
		int sumCrash = find(stones, index + 1, Math.abs(sum - stones[index]), map);
		int minValue = Math.min(sumNo, sumCrash);
		map.put(index + " " + sum, minValue);
		return minValue;
	}
}
