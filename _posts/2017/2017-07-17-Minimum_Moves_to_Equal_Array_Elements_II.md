---
layout: post
title: 462. Minimum Moves to Equal Array Elements II
---

#### QUESTION:

------

Given a **non-empty** integer array, find the minimum number of moves required to make all array elements equal, where a move is incrementing a selected element by 1 or decrementing a selected element by 1.

You may assume the array's length is at most 10,000.

**Example:**

```
Input:
[1,2,3]

Output:
2

Explanation:
Only two moves are needed (remember each move increments or decrements one element):

[1,2,3]  =>  [2,2,3]  =>  [2,2,2]
```

#### EXPLANATION:

其实是一个求中位数的问题，只要能够求出中位数是多少，那么就可以得到需要改变成的数了。

为什么呢。可以这样想：

给出数组a1-an，并且是已经进行过排序的数组，那么a1和an到中间任一数的距离算出来都是 an-a1，这样一直算到中间，那么中间数需要改变的步数就成为了最关键的点。中位数不需要改变就是最短的距离。

#### SOLUTION:

```JAVA
public class Solution {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int i = 0;int j = nums.length-1;
        int result = 0;
        while (i<j){
            result+=nums[j]-nums[i];
            i++;
            j--;
        }
        return result;
    }
}
```

