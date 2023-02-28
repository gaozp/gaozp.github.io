---
layout: post
title: 1470. Shuffle the Array
categories: [leetcode]
---
#### QUESTION:
Given the array nums consisting of 2n elements in the form [x1,x2,...,xn,y1,y2,...,yn].

Return the array in the form [x1,y1,x2,y2,...,xn,yn].

 

**Example 1:**
```
Input: nums = [2,5,1,3,4,7], n = 3
Output: [2,3,5,4,1,7] 
Explanation: Since x1=2, x2=5, x3=1, y1=3, y2=4, y3=7 then the answer is [2,3,5,4,1,7].
```
**Example 2:**
```
Input: nums = [1,2,3,4,4,3,2,1], n = 4
Output: [1,4,2,3,3,2,4,1]
```
**Example 3:**
```
Input: nums = [1,1,2,2], n = 2
Output: [1,2,1,2]
```
 

**Constraints:**
```
1 <= n <= 500
nums.length == 2n
1 <= nums[i] <= 10^3
```
#### EXPLANATION:
简单的题目，使用双指针，并用idx来判断奇偶。  
思路： 
1. 创建双指针，一个用来指向奇数，一个用来指向偶数。
2. 创建一个长度为2n的数组，用来摆放结果，同时创建一个idx用来指向此时应该摆放的位置。
3. 如果当前的idx能够被2整除，那么说明是x的，我们就将odd的值填入，同时将两者自增1.
4. 如果当前的idx是不能被2整除，那么说明是y的，我们就将even的值添加，同时将两者自增1.

#### SOLUTION:
```java
class Solution {
    public int[] shuffle(int[] nums, int n) {
        int[] result = new int[2*n];
        int idx = 0;
        int odd = 0;
        int even = n;
        while (idx<nums.length){
            if(idx % 2 == 0)
                result[idx++] = nums[odd++];
            else
                result[idx++] = nums[even++];
        }
        return result;
    }
}
```
