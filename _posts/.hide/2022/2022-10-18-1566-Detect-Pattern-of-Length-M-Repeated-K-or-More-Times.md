---
layout: post
title: 1566. Detect Pattern of Length M Repeated K or More Times
categories: [leetcode]
---
#### QUESTION:
Given an array of positive integers arr, find a pattern of length m that is repeated k or more times.

A pattern is a subarray (consecutive sub-sequence) that consists of one or more values, repeated multiple times consecutively without overlapping. A pattern is defined by its length and the number of repetitions.

Return true if there exists a pattern of length m that is repeated k or more times, otherwise return false.

 

__Example 1:__
```
Input: arr = [1,2,4,4,4,4], m = 1, k = 3
Output: true
Explanation: The pattern (4) of length 1 is repeated 4 consecutive times. Notice that pattern can be repeated k or more times but not less.
```
__Example 2:__
```
Input: arr = [1,2,1,2,1,1,1,3], m = 2, k = 2
Output: true
Explanation: The pattern (1,2) of length 2 is repeated 2 consecutive times. Another valid pattern (2,1) is also repeated 2 times.
```
__Example 3:__
```
Input: arr = [1,2,1,2,1,3], m = 2, k = 3
Output: false
Explanation: The pattern (1,2) is of length 2 but is repeated only 2 times. There is no pattern of length 2 that is repeated 3 or more times.
```
 

__Constraints:__
```
2 <= arr.length <= 100
1 <= arr[i] <= 100
1 <= m <= 100
2 <= k <= 100
```
#### EXPLANATION:

easy的题目, 只要进行for循环, 判断是否有k个相同的数值即可.

#### SOLUTION:
```kotlin
class Solution {
    fun containsPattern(arr: IntArray, m: Int, k: Int): Boolean {
        if (arr.size < m || arr.size.div(m) < k ) return false

        for (i in 0..arr.size - m * k) {
            var count: Int = 1
            var j = i + m
            while (j < arr.size - m + 1) {
                if (containsPatternHelper(arr, i, j, m)) {
                    count++
                    j += m -1
                } else count = 0
                
                if (count >= k) return true
                j++
            } 
        }
        return false
    }
    
    fun containsPatternHelper(arr: IntArray, i: Int, j: Int, m: Int): Boolean {
        for (p in 0 until m) {
            if (arr[i+p] != arr[j+p]) return false
        }
        return true
    }
}
```
