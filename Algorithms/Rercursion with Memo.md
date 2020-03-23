# Recursion with Memo


一般来讲和backtracking类似，但是求总和的题可以用recursion + memo, 因为不像backtracking那样需要罗列出所有的可能性，所以可以公用很多

### 1. Permutation Sum
> LC 377 https://leetcode.com/problems/combination-sum-iv/

``` 
Given an integer array with all positive numbers and no duplicates, 
find the number of possible combinations that add up to
a positive integer target.
nums = [1, 2, 3]
target = 4

The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

Note that different sequences are counted as different combinations.

Therefore the output is 7.

``` 

- 朴素递归思路类似于找所有的排列, bottom up, 但是bottom
- 用Map来储存已经找到的sum和count
- 这样可以避免重复计算


``` 

class Solution {
    public int PermutationSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        return find(nums, target, map);
    }
    
    private int find(int[] nums, int target, Map<Integer, Integer> map) {
        if (map.containsKey(target)) {
            return map.get(target);
        }
        
        int count = 0;
        if (target == 0) {
            return 1;
        }       
        if (target < 0) {
            return 0;
        }
        for (int i = 0 ; i < nums.length; i++) {
            count += find(nums, target - nums[i], map);
        }
        map.put(target, count);
        return count;
    }
}

``` 

### 2. Integer Break
> LC 343. Integer Break

> Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. Return the maximum product you can get.

``` 
Input: 10
Output: 36
Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.

```
- 从1 - n 开始选取每一个数 i ， 加入sum, 当sum = n, 说明找到和为target的，return 1；
- 由于我们是bottom up, 所以完成dfs 递归之后， 乘积就是 return result * i;
- 由于我们要选择最大乘积， max = Math.max(max, res * i);
- 同样可以用map存下每一个sum 计算过的对应的最大乘积，因为我们是bottom up, 存在很多重复计算

``` 
class Solution {
    public int integerBreak(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        return dfs(n, 0, map);
    }
    
    public int dfs(int n, int sum, Map<Integer, Integer> map) {
        if (map.containsKey(sum)) {
            return map.get(sum);
        }
        if (sum > n) {
            return -1;
        }
        if (sum == n) {
            return 1;
        }
        
        int max = 1;
        for (int i = 1; i < n; i++) {
            int res = dfs(n, sum + i, map);
            if (res == -1) {
                continue;
            }
            max = Math.max(max, res * i);
        }
        map.put(sum, max);
        return max;
    }
}
``` 
### 3. Coin Change

> https://leetcode.com/problems/coin-change/
>
> You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

``` 
Input: coins = [1, 2, 5], amount = 11
Output: 3 
Explanation: 11 = 5 + 5 + 1

``` 

``` 
class Solution {
    public int coinChange(int[] coins, int amount) {
        Map<Integer, Integer> map = new HashMap<>();
        return dfs(coins, amount, map);
    }
    
    private int dfs(int[] coins, int amount, Map<Integer, Integer> map) {
        if (map.containsKey(amount)) {
            return map.get(amount);
        }
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int count = dfs(coins, amount - coin, map);
            if (count != -1) {
                min = Math.min(count + 1, min);
            }
        }
        if (min == Integer.MAX_VALUE) {
            min = -1;
        }
        map.put(amount, min);
        return min;
    }
}
``` 