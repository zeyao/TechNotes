
# Stack 问题


## 1. Monotone Stack 单调栈

- 所谓的单调栈Monotone Stack，就是栈内元素都是单调递增或者单调递减的。单调栈的一大优势就是线性的时间复杂度，所有的元素只会进栈一次，而且一旦出栈后就不会再进来了。


#### LC1063. Number of Valid Subarrays

> Given an array A of integers, return the number of non-empty continuous subarrays that satisfy the following condition:

> The leftmost element of the subarray is not larger than other elements in the subarray.

> Example 1:

- Input: [1,4,2,5,3]  Output: 11 Explanation: There are 11 valid subarrays: [1],[4],[2],[5],[3],[1,4],[2,5],[1,4,2],[2,5,3],[1,4,2,5],[1,4,2,5,3].


> 这道题可以单调栈的思路，因为要找到持续右边的元素，直到不比arr[startIndex]元素大, 那么再遇到这个较大元素前，保证递增元素入栈，遇到较大元素，开始出栈

> 例如[1,2,4,1] - 那么 4 -> 1 (【4】), 2 -> 1 （【2】【2，4】）, 1 -> 1（【1，2，4】，【1，2】【1】）

> 可看出，当遇到一个small index的时候， subarray 的个数就是 indexOf small number - index of larger number

> 于是我们可以用stack把元素的index add in, 保证对应的元素是递增的，当 arr[i] 小于等于 stack.peek() 说明到了临界值，可以出栈操作，计算count 

```
    public int validSubarrays(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                int lastIndex = stack.pop();
                count += i - lastIndex;
            }
            stack.add(i);
        }       
        while (!stack.isEmpty()) {
            count += nums.length - stack.pop();
        }
        return count;
    }
```