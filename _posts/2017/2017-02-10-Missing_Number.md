---
layout: post
title: 268. Missing Number
categories: [leetcode]
---

#### QUESTION:

Given an array containing *n* distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

For example,

Given *nums* = [0, 1, 3] return 2.

**Note**:

Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?

**Credits:**

Special thanks to [@jianchao.li.fighter](https://leetcode.com/discuss/user/jianchao.li.fighter) for adding this problem and creating all test cases.

#### EXPLANATION:

自己写的也是ac的，但是感觉效率还是太低了，于是就翻了下别人的解决办法，发现还是使用了之前的异或自己等于把自己置为0；

所以在result = result^num[i]^i最终的结果相当于异或了最后一个数和缺少的那个数，这个时候再异或最后一个数，就只剩下i了，就是缺少的那个数。

#### SOLUTION:

```java
public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        for(int i = 0;i<nums.length;i++){
            if(nums[i]!=i)
                return i;
        }
        return nums[nums.length-1]+1;
    }


public static int missingNumber(int[] nums) {
        int result = 0;
        int i = 0;
        for(i =0;i<nums.length;i++){
            result = result^nums[i]^i;
        }
        return result^i;
    }
```

