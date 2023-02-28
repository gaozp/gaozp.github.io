---
layout: post
title: 1464. Maximum Product of Two Elements in an Array
categories: [leetcode]
---
#### QUESTION:
Given the array of integers nums, you will choose two different indices i and j of that array. Return the maximum value of (nums[i]-1)\*(nums[j]-1).
 

**Example 1:**
```
Input: nums = [3,4,5,2]
Output: 12 
Explanation: If you choose the indices i=1 and j=2 (indexed from 0), you will get the maximum value, that is, (nums[1]-1)*(nums[2]-1) = (4-1)*(5-1) = 3*4 = 12. 
```
**Example 2:**
```
Input: nums = [1,5,4,5]
Output: 16
Explanation: Choosing the indices i=1 and j=3 (indexed from 0), you will get the maximum value of (5-1)*(5-1) = 16.
```
**Example 3:**
```
Input: nums = [3,7]
Output: 12
 
```

**Constraints:**
```
2 <= nums.length <= 500
1 <= nums[i] <= 10^3
```
#### EXPLANATION:
题目比较简单，思路：
1. 找到最大值
2. 找到第二大的值
3. 将两者相乘

至于如何找到最大和第二大的值，可以直接排序，也可以采用for循环的方式。
#### SOLUTION:
```java
class Solution {
    public int maxProduct(int[] nums) {
        Arrays.sort(nums);
        return (nums[nums.length-1]-1)*(nums[nums.length-2]-1);
    }
}
```
