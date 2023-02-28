---
layout: post
title: 674. Longest Continuous Increasing Subsequence
categories: [leetcode]
---

#### QUESTION:

Given an unsorted array of integers, find the length of longest `continuous` increasing subsequence (subarray).

**Example 1:**

```
Input: [1,3,5,4,7]
Output: 3
Explanation: The longest continuous increasing subsequence is [1,3,5], its length is 3. 
Even though [1,3,5,7] is also an increasing subsequence, it's not a continuous one where 5 and 7 are separated by 4. 
```

**Example 2:**

```
Input: [2,2,2,2,2]
Output: 1
Explanation: The longest continuous increasing subsequence is [2], its length is 1. 
```

**Note:** Length of the array will not exceed 10,000.

#### EXPLANATION:

这个逻辑就很简单了，最近都在偷懒做一些简单的算法题，哈哈。

就是判断当前和前一个的大小对比。

#### SOLUTION:

```JAVA
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        if(nums == null || nums.length==0)return 0;
        int result = 1;
        int pre = nums[0];
        ArrayList<Integer> subset = new ArrayList<>();
        subset.add(pre);
        for(int i = 1;i<nums.length;i++){
            if(nums[i]<=pre)
                subset.clear();
            subset.add(nums[i]);
            result = Math.max(subset.size(),result);
            pre = nums[i];
        }
        return result;
    }
}
```

