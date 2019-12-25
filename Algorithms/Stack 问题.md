
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



#### LC84. Largest Rectangle in Histogram

> Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.


<img src="https://assets.leetcode.com/uploads/2018/10/12/histogram_area.png" style="height:200px" />

<img src="https://raw.githubusercontent.com/zeyao/TechNotes/master/Document/LC%2084.jpg" style="height:500px" />


```


    public int largestRectangleArea(int[] heights) {
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < heights.length; i++) {
            int width = 0;
            while (!stack.isEmpty() && stack.peek() > heights[i]) {
                int number = stack.pop();
                width++;
                max = Math.max(max, number * width);
            }
            for (int j = 0; j < width; j++) {
                stack.add(heights[i]);
                // smaller number can caculate both left and right, 
                // replace the pop element with smaller element
                //[2,1,2] - > [1,1,2]
            }
            stack.add(heights[i]);
        }
        //now everything in stack in 递增
        int width = 0;
        while (!stack.isEmpty()) {
            int number = stack.pop();
            width++;
            max = Math.max(max, number * width);
        }
        return max;
    }
    
```


-  如何优化，不需要重新把出栈元素替换为较小的 arr[i]

- 可以通过stack放入index 通过 当heights[stack.peek()] > heights[i] 出栈，栈对应的元素依旧是递增，那么每一个stack pop 元素右边界就是i，左边界就是stack.peek(), 如果 stack 空， 说明左边界是-1；

这样可以保证即使出栈掉一轮 例如 [2, 1, 5, 6, 2, 3] stack 出栈掉 index 2 index3 , 里的 index stack: [1, 4, 5] 计算index 4 时候 面积 = (6 - 1 - 1) * 2 = 8

<img src="https://raw.githubusercontent.com/zeyao/TechNotes/master/Document/LC84(2).jpg" style="height:500px" />



```
    public int largestRectangleArea(int[] heights) {
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                int currentIndex = stack.pop();
                int leftIndex = stack.isEmpty() ? -1 : stack.peek();
                max = Math.max(max, (i - leftIndex - 1) * heights[currentIndex]);
            }
            stack.add(i);
        }
        
        //now all递增
        if (stack.isEmpty()) return max;
        
        int rightIndex = stack.peek() + 1;
        while (!stack.isEmpty()) {
            int currentIndex = stack.pop();
            int leftIndex = stack.isEmpty() ? -1 : stack.peek();
            max = Math.max(max, (rightIndex - leftIndex - 1) * heights[currentIndex]);
        }        
        return max;
    }
    
```

