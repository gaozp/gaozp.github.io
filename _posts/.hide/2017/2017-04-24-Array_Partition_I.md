---
layout: post
title: 561. Array Partition I
categories: [leetcode]
---

#### QUESTION:

Given an array of **2n** integers, your task is to group these integers into **n** pairs of integer, say (a1, b1), (a2, b2), ..., (an, bn) which makes sum of min(ai, bi) for all i from 1 to n as large as possible.

**Example 1:**

```
Input: [1,4,3,2]

Output: 4
Explanation: n is 2, and the maximum sum of pairs is 4.

```

**Note:**

1. **n** is a positive integer, which is in the range of [1, 10000].
2. All the integers in the array will be in the range of [-10000, 10000].

#### EXPLANATION:

其实我一开始想到的是贪心算法，如果每一步获取到的都是最优的那么总和加起来那么也会是最优的。那么每一步怎么样才能获取到最优的呢，就是ai和bi两者的差是最小的。这样的话算法就显而易见了。

#### SOLUTION:

```java
public class Solution {
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int result = 0;
        for(int i = 0;i<nums.length;i+=2){
            result += nums[i];
        }
        return result;
    }
}
```

