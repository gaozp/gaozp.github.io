---
layout: post
title: 598. Range Addition II
categories: [leetcode]
---

#### QUESTION:

Given an m * n matrix **M** initialized with all **0**'s and several update operations.

Operations are represented by a 2D array, and each operation is represented by an array with two **positive** integers **a** and **b**, which means **M[i][j]** should be **added by one** for all **0 <= i < a** and **0 <= j < b**.

You need to count and return the number of maximum integers in the matrix after performing all the operations.

**Example 1:**

```
Input: 
m = 3, n = 3
operations = [[2,2],[3,3]]
Output: 4
Explanation: 
Initially, M = 
[[0, 0, 0],
 [0, 0, 0],
 [0, 0, 0]]

After performing [2,2], M = 
[[1, 1, 0],
 [1, 1, 0],
 [0, 0, 0]]

After performing [3,3], M = 
[[2, 2, 1],
 [2, 2, 1],
 [1, 1, 1]]

So the maximum integer in M is 2, and there are four of it in M. So return 4.

```

**Note:**

1. The range of m and n is [1,40000].
2. The range of a is [1,m], and the range of b is [1,n].
3. The range of operations size won't exceed 10,000.

#### EXPLANATION:

其实倒是很容易想到的，2\*2其实就就是这样，然后再3\*3，这样的操作其实只有2\*2的格子是进行过两次操作的。那么这个问题就变成了，哪些格子是进行过最多次的加法操作。那么问题就很简单了，就是求行和列的最小值，这个最小值就是经过+1次数最多的数了。

1.求出行的最小值

2.求出列的最小值

3.进行乘法运算，算出总个数。

#### SOLUTION:

```JAVA
public class Solution {
    public int maxCount(int m, int n, int[][] ops) {
        int row = Integer.MAX_VALUE;int colum = Integer.MAX_VALUE;
        for(int i= 0;i<ops.length;i++){
            row = Math.min(ops[i][0],row);
            colum = Math.min(ops[i][1],colum);
        }
        row = Math.min(m,row);
        colum = Math.min(n,colum);
        return row*colum;
    }
}
```

