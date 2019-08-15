---
layout: post
title: 646. Maximum Length of Pair Chain
categories: [leetcode]
---

#### QUESTION:

You are given `n` pairs of numbers. In every pair, the first number is always smaller than the second number.

Now, we define a pair `(c, d)` can follow another pair `(a, b)` if and only if `b < c`. Chain of pairs can be formed in this fashion.

Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the given pairs. You can select pairs in any order.

**Example 1:**

```
Input: [[1,2], [2,3], [3,4]]
Output: 2
Explanation: The longest chain is [1,2] -> [3,4]

```

**Note:**

1. The number of given pairs will be in the range [1, 1000].

#### EXPLANATION:

这道题目其实用的是贪心算法，先按照最末尾的数进行排序，然后再进行对比，如果某个数的0位置大于之前的，就进行添加。贪心算法的典型哈。

#### SOLUTION:

```JAVA
public class Solution {
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs,(a,b)->a[1]-b[1]);
        int count = 1;
        int end = pairs[0][1];
        for(int i = 1;i<pairs.length;i++){
            if(pairs[i][0]>end){
                count++;
                end = pairs[i][1];
            }
        }
        return count;
    }
}
```

