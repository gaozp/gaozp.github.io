---
layout: post
title: 189. Rotate Array
categories: [leetcode]
---

#### QUESTION:

Rotate an array of *n* elements to the right by *k* steps.

For example, with *n* = 7 and *k* = 3, the array `[1,2,3,4,5,6,7]` is rotated to `[5,6,7,1,2,3,4]`.

**Note:**
Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.

#### EXPLANATION:

题目的大意是：进行k的步的rotate，就是把最后一位换到第一位的操作。这样思路其实就很简单了。

首先从最后copy一个k长度的数组作为头部，然后再将0-k的数copy到后面即可。

具体看代码就可以。

有一个问题就是，我用到了Arrays这个工具类。但是在最快的提交中，System中有一个arraycopy的方法。

#### SOLUTION:

```JAVA
public class Solution {
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        if(k == 0) return;
        int[] copys = Arrays.copyOfRange(nums, nums.length-k, nums.length);
        int[] original = Arrays.copyOf(copys, nums.length);
        for (int i = 0; i <= nums.length-k && k+i <nums.length; i++) {
            original[k + i] = nums[i];
        }
        for(int i = 0;i<nums.length;i++)
            nums[i] = original[i];
    }
}
```

