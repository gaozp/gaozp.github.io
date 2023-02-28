---
layout: post
title: 198. House Robber
categories: [leetcode]
---

#### QUESTION:

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and **it will automatically contact the police if two adjacent houses were broken into on the same night**.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight **without alerting the police**.

#### EXPLANATION:

其实就是一个循环然后求出结果，注意的是preNo的值是之前两个的最大值这个地方。

#### SOLUTION:

```java
public int rob(int[] nums) {
        if(nums==null || nums.length ==0 ) return 0;
        int preYes = 0;
        int preNo = 0;
        for(int n : nums){
            int temp = preNo;
            preNo = Math.max(preYes,preNo);
            preYes = n + temp;
        }
        return Math.max(preYes,preNo);
    }
```

