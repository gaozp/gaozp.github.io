---
layout: post
title: 485. Max Consecutive Ones
---

#### QUESTION:

Given a binary array, find the maximum number of consecutive 1s in this array.

**Example 1:**

**Input:** [1,1,0,1,1,1]

**Output:** 3

**Explanation:** The first two digits or the last three digits are consecutive 1s.

    The maximum number of consecutive 1s is 3.

**Note:**

- The input array will only contain 0 and 1.
- The length of input array is a positive integer and will not exceed 10,000



#### EXPLANATION:

完全觉得没有什么可以解释的，直接循环然后每次对比就可以了。

#### SOLUTION:

```java
public class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        if(nums==null || nums.length<=0) return 0;
        int result = 0,tmp = 0;
        for(int i = 0;i<nums.length;i++){
            if(nums[i]==1){
                tmp+=1;
            }else{
                tmp = 0;
            }
            result = Math.max(result,tmp);
        }
        return result;
    }
}
```

