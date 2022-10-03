---
layout: post
title: 2427. Number of Common Factors
categories: [leetcode]
---
#### QUESTION:
Given two positive integers a and b, return the number of common factors of a and b.

An integer x is a common factor of a and b if x divides both a and b.

 

__Example 1:__
```
Input: a = 12, b = 6
Output: 4
Explanation: The common factors of 12 and 6 are 1, 2, 3, 6.
```
__Example 2:__
```
Input: a = 25, b = 30
Output: 2
Explanation: The common factors of 25 and 30 are 1, 5.
```
 

__Constraints:__
```
1 <= a, b <= 1000
```
#### EXPLANATION:

简单的题目, 一个for循环, 就可以获取到最终的结果.

#### SOLUTION:
```kotlin
import kotlin.math.min
class Solution {
    fun commonFactors(a: Int, b: Int): Int {
        var result:Int = 0
        var end:Int = Math.min(a,b)
        for (num in end downTo  1) {
            if (a % num == 0 && b % num == 0) {
                result++
            }
        }
        return result
    }
}
```
