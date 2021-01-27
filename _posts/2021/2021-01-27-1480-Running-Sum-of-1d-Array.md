---
layout: post
title: 1480. Running Sum of 1d Array
categories: [tech]
---
#### QUESTION:
Given an array nums. We define a running sum of an array as runningSum[i] = sum(nums[0]…nums[i]).

Return the running sum of nums.
```
Example 1:
Input: nums = [1,2,3,4]
Output: [1,3,6,10]
```
Explanation: Running sum is obtained as follows: [1, 1+2, 1+2+3, 1+2+3+4].
```
Example 2:
Input: nums = [1,1,1,1,1]
Output: [1,2,3,4,5]
```
Explanation: Running sum is obtained as follows: [1, 1+1, 1+1+1, 1+1+1+1, 1+1+1+1+1].
```
Example 3:
Input: nums = [3,1,2,10,1]
Output: [3,4,6,16,17]
```
Constraints:
```
1 <= nums.length <= 1000
-10^6 <= nums[i] <= 10^6
```
#### EXPLANATION:
今天开始,打算用kotlin来进行leetcode的编写.  
这道题目比较简单, 其实就是一个连环乘法的概念.有两种方法,一种是直接在原数组上进行修改,另外一种就是新建数组进行修改.   
步骤: 
1. 第一位无法加,所以直接填上就可以
2. 第二位以及后面的位置可以直接与前面一位相加即可.
#### SOLUTION:
```kotlin
class Solution {
    fun runningSum(nums: IntArray): IntArray {
        for ((index, value) in nums.withIndex()) {
            if (index == 0) continue
            else nums[index] = nums[index] + nums[index - 1]
        }
        return nums
    }
}
```
