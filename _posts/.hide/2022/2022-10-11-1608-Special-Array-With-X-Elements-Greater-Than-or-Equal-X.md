---
layout: post
title: 1608. Special Array With X Elements Greater Than or Equal X
categories: [leetcode]
---
#### QUESTION:
You are given an array nums of non-negative integers. nums is considered special if there exists a number x such that there are exactly x numbers in nums that are greater than or equal to x.

Notice that x does not have to be an element in nums.

Return x if the array is special, otherwise, return -1. It can be proven that if nums is special, the value for x is unique.

 

__Example 1:__
```
Input: nums = [3,5]
Output: 2
Explanation: There are 2 values (3 and 5) that are greater than or equal to 2.
```
__Example 2:__
```
Input: nums = [0,0]
Output: -1
Explanation: No numbers fit the criteria for x.
If x = 0, there should be 0 numbers >= x, but there are 2.
If x = 1, there should be 1 number >= x, but there are 0.
If x = 2, there should be 2 numbers >= x, but there are 0.
x cannot be greater since there are only 2 numbers in nums.
```
__Example 3:__
```
Input: nums = [0,4,3,0,4]
Output: 3
Explanation: There are 3 values that are greater than or equal to 3.
```
 

__Constraints:__
```
1 <= nums.length <= 100
0 <= nums[i] <= 1000
```
#### EXPLANATION:

easy的题目, 一个for循环就可以搞定.

#### SOLUTION:
```kotlin
class Solution {
    fun specialArray(nums: IntArray): Int {
        for (i in 0..nums.count()) {
            if (i == nums.count { it >= i }) {
                return i
            }
        }
        return -1
    }
}
```
