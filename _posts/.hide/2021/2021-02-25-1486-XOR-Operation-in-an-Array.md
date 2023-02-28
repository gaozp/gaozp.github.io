---
layout: post
title: 1486. XOR Operation in an Array
categories: [leetcode]
---
#### QUESTION:
Given an integer n and an integer start.

Define an array nums where nums[i] = start + 2*i (0-indexed) and n == nums.length.

Return the bitwise XOR of all elements of nums.

 

__Example 1:__
```
Input: n = 5, start = 0
Output: 8
Explanation: Array nums is equal to [0, 2, 4, 6, 8] where (0 ^ 2 ^ 4 ^ 6 ^ 8) = 8.
Where "^" corresponds to bitwise XOR operator.
```
__Example 2:__
```
Input: n = 4, start = 3
Output: 8
Explanation: Array nums is equal to [3, 5, 7, 9] where (3 ^ 5 ^ 7 ^ 9) = 8.
```
__Example 3:__
```
Input: n = 1, start = 7
Output: 7
```
__Example 4:__
```
Input: n = 10, start = 5
Output: 2
 ```

__Constraints:__
```
1 <= n <= 1000
0 <= start <= 1000
n == nums.length
```
#### EXPLANATION:

这道题目就比较简单了. 直接根据题意, 然后按照对应的方法写出来即可. 先把数组列出来, 然后再把每一位进行异或就行.

#### SOLUTION:
```kotlin
class Solution {
    fun xorOperation(n: Int, start: Int): Int {
        if (n ==0) return 0
        var array : IntArray = IntArray(n)
        for (i in array.indices) {
            array[i] = start + i * 2
        }
        var result = array[0];
        for (i in 1..array.size-1) {
            result = result xor array[i]
        }
        return result
    }
}
```
