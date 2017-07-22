---
layout: post
title: 238. Product of Array Except Self
---

#### QUESTION:

Given an array of *n* integers where *n* > 1, `nums`, return an array `output` such that `output[i]` is equal to the product of all the elements of `nums` except `nums[i]`.

Solve it **without division** and in O(*n*).

For example, given `[1,2,3,4]`, return `[24,12,8,6]`.

**Follow up:**
Could you solve it with constant space complexity? (Note: The output array **does not** count as extra space for the purpose of space complexity analysis.)

#### EXPLANATION:

这道题目的迷惑的地方就是在于如何构造对应的数组了。如果可以采用对应的除法的话应该是很容易就可以搞定的，但是题目规定了不能够使用对应的除法。那么我们就可以这样构造这个数组。

a = {            1，                                      a[0],                          a[0]\*a[1],         a[0]\*a[1]\*a[2]}

b = {  a[1]\*a[2]*a[3]        ,              a[2]\*a[3]  ,                         a[3]     ,                     1          }

这两个数组相乘，就可以得到对应的值了，比如 result[0] = a[0]\*b[0]

那么这两个数组如何构建其实就很容易了。

#### SOLUTION:

```JAVA
public class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        result[0] = 1;
        for(int i = 1;i<nums.length;i++){
            result[i] = nums[i-1]*result[i-1];
        }
        int right = 1;
        for(int i = nums.length-1;i>=0;i--){
            result[i]*=right;
            right*=nums[i];
        }
        return result;   
    }
}
```

