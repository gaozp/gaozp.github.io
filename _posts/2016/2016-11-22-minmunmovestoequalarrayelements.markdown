---
layout: post
title: 453. Minimum Moves to Equal Array Elements
---

####QUESTION:
Given a non-empty integer array of size n, find the minimum number of moves required to make all array elements equal, where a move is incrementing n - 1 elements by 1.

Example:

Input:
[1,2,3]

Output:
3

Explanation:
Only three moves are needed (remember each move increments two elements):

[1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]

  
####EXPLANATION:  
本来一开始想到的解法是首先排序数组，然后n-1的元素+1，排序数组，判断第一个和最后一个是否相等。但是提交上去之后发现还有timelimit。所以只能想其他的方式。  
当我们将n-1的元素都加1，其实就是相当于把最大的元素-1。这样只要将所有的元素都减少成与最小的元素相等，那么就能确保这个数组都相等。那么问题就可以简化成，该数组中所有元素与最小元素的差的和。

其实这样也就有另外一种方式来计算了，就是先求整个数组的和，然后减去对应的最小的数乘以n。

####SOLUTION:  

    public int minMoves(int[] nums) {
        int mn = Integer.MAX_VALUE;
        int res = 0;
        for (int num : nums) mn = Math.min(mn, num);
        for (int num : nums) res += num - mn;
        return res;
    }
    
    public int minMoves(int[] nums) {
        return IntStream.of(nums).sum()-IntStream.of(nums).min().getAsInt()*nums.length;
    }


