---
layout: post
title: 628. Maximum Product of Three Numbers
---

#### QUESTION:

Given an integer array, find three numbers whose product is maximum and output the maximum product.

**Example 1:**

```
Input: [1,2,3]
Output: 6

```

**Example 2:**

```
Input: [1,2,3,4]
Output: 24

```

**Note:**

1. The length of the given array will be in range [3,104] and all elements are in the range [-1000, 1000].
2. Multiplication of any three numbers in the input won't exceed the range of 32-bit signed integer.

#### EXPLANATION:

其实就是排个序，然后计算下最值即可。

因为是3个数，所以其实只有4种情况 0，1，last。或者1，last-1，last但是如果1是正的话就不存在，1是负数的话也不存在。或者是last-2，last-1，last。或者是0，1，2，但是这样的话3个数肯定都是负数，那么出来的就是负数，所以也不存在，其实就只有两种比较 0，1，last以及last-2，last-1，last。

#### SOLUTION:

```java
public class Solution {
    public int maximumProduct(int[] nums) {
        Integer result = null;
        if(nums.length<=3){
            for(int num:nums){
                if(result == null)
                    result = num;
                else
                    result*=num;
            }
            return result;
        }
        Arrays.sort(nums);
        if (result == null)
			result = nums[nums.length-1]*nums[0]*nums[1];
        result = Math.max(result,nums[nums.length-3]*nums[nums.length-2]*nums[nums.length-1]);
        return result;
    }
}
```

