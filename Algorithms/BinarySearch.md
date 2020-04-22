
# BinarySearch 问题


### 1. Leftmost Column with at Least a One

A binary matrix means that all elements are 0 or 1. For each individual row of the matrix, this row is sorted in non-decreasing order.

Given a row-sorted binary matrix binaryMatrix, return leftmost column index(0-indexed) with at least a 1 in it. If such index doesn't exist, return -1.

You can't access the Binary Matrix directly.  You may only access the matrix using a BinaryMatrix interface:

BinaryMatrix.get(x, y) returns the element of the matrix at index (x, y) (0-indexed).
BinaryMatrix.dimensions() returns a list of 2 elements [m, n], which means the matrix is m * n.
Submissions making more than 1000 calls to BinaryMatrix.get will be judged Wrong Answer.  Also, any solutions that attempt to circumvent the judge will result in disqualification.

For custom testing purposes you're given the binary matrix mat as input in the following four examples. You will not have access the binary matrix directly.

Eg: 

 [0,0,0,1]
 
 [0,0,1,1]
 
 [0,1,1,1]
 
Output : 1

- 二分查找每一行， 由于每一行都是升序排列， 二分查找找到第一个1；
- 在所有行数里面找懂最小的
- n log M

``` 
/**
 * // This is the BinaryMatrix's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface BinaryMatrix {
 *     public int get(int x, int y) {}
 *     public List<Integer> dimensions {}
 * };
 */

class Solution {
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> list = binaryMatrix.dimensions();
        int row = list.get(0);
        int col = list.get(1);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < row; i++) {
            int first = binarySearch(i, col, binaryMatrix);
            min = Math.min(min, first);
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
    
    private int binarySearch(int row, int col, BinaryMatrix binaryMatrix) {
        int left = 0;
        int right = col - 1;
        while (left < right) {
            int mid = (right - left) /2 + left;
            if (binaryMatrix.get(row, mid) >= 1) {
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }
        if (binaryMatrix.get(row, left) == 0) {
            return Integer.MAX_VALUE;
        }
        return left;
    }
}
``` 