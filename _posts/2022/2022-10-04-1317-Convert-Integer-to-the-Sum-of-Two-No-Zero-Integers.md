---
layout: post
title: 1317. Convert Integer to the Sum of Two No-Zero Integers
categories: [leetcode]
---
#### QUESTION:
No-Zero integer is a positive integer that does not contain any 0 in its decimal representation.

Given an integer n, return a list of two integers [A, B] where:

A and B are No-Zero integers.
A + B = n
The test cases are generated so that there is at least one valid solution. If there are many valid solutions you can return any of them.

 

__Example 1:__
```
Input: n = 2
Output: [1,1]
Explanation: A = 1, B = 1. A + B = n and both A and B do not contain any 0 in their decimal representation.
```
__Example 2:__
```
Input: n = 11
Output: [2,9]
```
 

__Constraints:__
```
2 <= n <= 104
```
#### EXPLANATION:

easy的题目, 一个for循环加上判断即可.

#### SOLUTION:
```kotlin
class Solution {
    fun getNoZeroIntegers(n: Int): IntArray {
        var result:IntArray = IntArray(2)
        for (i in n - 1 downTo 1) {
            if (!i.toString().contains('0') && !(n-i).toString().contains('0')) {
                result[0] = i
                result[1] = n - i
                return result
            }
        }
        return result
    }
}
```
