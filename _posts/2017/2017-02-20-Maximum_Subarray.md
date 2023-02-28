---
layout: post
title: 53. Maximum Subarray
categories: [leetcode]
---

#### QUESTION:

Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array `[-2,1,-3,4,-1,2,1,-5,4]`,
the contiguous subarray `[4,-1,2,1]` has the largest sum = `6`.



#### EXPLANATION:

这是一个之前就有的算法，具体可以查看[这个链接](http://baike.baidu.com/link?url=SGJ6SSC_t6wIpCXwydCRYRsd6byYohBLWJk5lTIvYnXynOqDCBpDMhuEDNJM4YMHfJ_-y5KxyFOe06F3gVb1Bga0F7QZbiiE2nxsC0m2m3CDaCrT2wgXnAI4ASeGd0xn).

#### SOLUTION:

```java
public int maxSubArray(int[] nums) {//Time limit 
        int max = nums[0];
        for(int i = 0 ;i<nums.length;i++){
            for(int j = 0;j<nums.length;j++){
                if(i+j<nums.length){
                    int sum = 0;
                    for(int k = i; k<=i+j;k++){
                        sum = sum + nums[k];
                    }
                    max = Math.max(max,sum);
                }
            }
        }
        return max;
    }

public int maxSubArray(int[] nums) {
        int global = nums[0];
        int local = nums[0];
        for(int i=1;i<nums.length;i++)
        {
            local = Math.max(nums[i],local+nums[i]);
            global = Math.max(local,global);
        }
        return global;
    }
```

