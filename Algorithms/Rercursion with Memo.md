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
- 这样可以避免重复计算,由于排列不管顺序，所以count (1) + count(2) = count(3)


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


### 3. Coin Change 2 (combination sum)
> https://leetcode.com/problems/coin-change-2/

> You are given coins of different denominations and a total amount of money. Write a function to compute the number of combinations that make up that amount. You may assume that you have infinite number of each kind of coin.

``` 
Input: amount = 5, coins = [1, 2, 5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1

``` 
- 不同于 Permutation Sum， combination 顺序不同，但是结果算相同 f(3) != f(2) + f(1), 
- 不可以直接map.put(target, count); 会重复计算结果 例如 3 = 2 + 1,  2 -> 1 + 1 and 2; 1 -> 1; 但是f(3) 在选取f(2)的 1+1 + f（1）就和f(1) + f(1) + f(1) 重复考虑了
- 只有在start index 相同的amount,才可以共用结果
- int[][] cache = new int[coins.length][amount+1] 进行二维cache

``` 

class Solution {
    public int change(int amount, int[] coins) {
        if (coins.length == 0 && amount != 0) return 0;
        int[][] cache = new int[coins.length][amount+1]; // start index and amount as cache;
        return dfs(0, amount, coins, cache);
    }
    
    private int dfs(int start, int amount, int[] coins, int[][] cache) {
        if (amount < 0) {
            return 0;
        }
        if (amount == 0) {
            return 1;
        }
        
        if (cache[start][amount] != 0) {
            return cache[start][amount];
        }
        
        int count = 0;
        for (int i = start; i < coins.length; i++) {
            count += dfs(i, amount - coins[i], coins, cache);
        }
        cache[start][amount] = count; 
        return count;
    }
}



```

- DP 解法： 从每一个coin开始算起，每一轮从 amount = coin 开始叠加， dp[i] += dp[i-coin];
- 也就是说当一个coin可选的时候，这个amount的个数，可以通过 dp[amout-coin] 转化而来 -> dp[amount] += dp[amout-coin];
- 为什么可以避免重复呢：
- 例如当我们有coin [2,3] 要计算target = 5
- 那么当拿coin  = 2, 由于dp[0] = 1, dp[2] -> 1; 当计算 i = 5 coin = 2, dp[5] += dp[3], 这个时候dp[3] = 0， 因为我们coin一个一个拿，所以还没有计算到；
- 同理当拿coin = 3， dp[0] = 1, dp[3] - > 1; 当计算 i = 5, coin = 3, dp[5] += dp[3]; 
- 所以我们只计算了 （3，2），没有计算（2，3）

``` 
class Solution {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i-coin];
            }
        }   
       return dp[amount];
    }
}
``` 