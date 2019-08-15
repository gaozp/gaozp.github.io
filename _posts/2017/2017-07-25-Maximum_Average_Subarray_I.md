---
layout: post
title: 643. Maximum Average Subarray I
categories: [leetcode]
---

#### QUESTION:

Given an array consisting of `n` integers, find the contiguous subarray of given length `k` that has the maximum average value. And you need to output the maximum average value.

**Example 1:**

```
Input: [1,12,-5,-6,50,3], k = 4
Output: 12.75
Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75

```

**Note:**

1. 1 <= `k` <= `n` <= 30,000.
2. Elements of the given array will be in the range [-10,000, 10,000].

#### EXPLANATION:

需要注意的是consist关键字，说明数组的顺序是不可以修改的，那么其实就变成了和之前差不多的double-pointer问题。

计算出第一个k的和，紧接着窗口移动，加上新增加的，减去最后尾的。然后算出最大的值就可以。

返回结果是最大值除以k就成。

不能每次都进行求和运算，那样会time limit的。

#### SOLUTION:

```JAVA
public class Solution {
    public double findMaxAverage(int[] nums, int k) {
        double result = -Integer.MIN_VALUE;
        double sum = 0;
        for(int i = 0;i<k;i++){
            sum += nums[i];
        }
        result = Math.max(result,sum/k);
        for(int i = 1;i<=nums.length-k;i++){
            sum-=nums[i-1];
            sum+=nums[i+k-1];
            result = Math.max(result,sum/k);
        }
        return result;
    }
}
```

