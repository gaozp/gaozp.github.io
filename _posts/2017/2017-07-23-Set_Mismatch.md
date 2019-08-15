---
layout: post
title: 645. Set Mismatch
categories: [leetcode]
---

#### QUESTION:

The set `S` originally contains numbers from 1 to `n`. But unfortunately, due to the data error, one of the numbers in the set got duplicated to **another** number in the set, which results in repetition of one number and loss of another number.

Given an array `nums` representing the data status of this set after the error. Your task is to firstly find the number occurs twice and then find the number that is missing. Return them in the form of an array.

**Example 1:**

```
Input: nums = [1,2,2,4]
Output: [2,3]

```

**Note:**

1. The given array size will in the range [2, 10000].
2. The given array's numbers won't have any order.

#### EXPLANATION:

关键点在于数组是包含了1-n这个条件，那么就可以建立一个标志位来用空间换取时间，相同的数据出现了，那么就说明是错误的那个，然后再检查哪个数字没有出现，就可以知道应该出现的数字是什么了。

时间复杂度就是O(n);

#### SOLUTION:

```JAVA
public class Solution {
    public int[] findErrorNums(int[] nums) {
        int[] result = new int[2];
        boolean[] flag = new boolean[nums.length];
        for(int i = 0;i<nums.length;i++){
            int key = nums[i];
            if(flag[key-1]==true){
                result[0] = key;
            }else{
                flag[key-1] = true;
            }
        }

        for(int i = 0;i<flag.length;i++){
            if(!flag[i]) result[1] = i+1;
        }
        return result;
    }
}
```

