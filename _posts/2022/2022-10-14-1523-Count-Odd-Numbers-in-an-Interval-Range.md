---
layout: post
title: 1523. Count Odd Numbers in an Interval Range
categories: [leetcode]
---
#### QUESTION:
Given two non-negative integers low and high. Return the count of odd numbers between low and high (inclusive).

 

__Example 1:__
```
Input: low = 3, high = 7
Output: 3
Explanation: The odd numbers between 3 and 7 are [3,5,7].
```
__Example 2:__
```
Input: low = 8, high = 10
Output: 1
Explanation: The odd numbers between 8 and 10 are [9].
```
 

__Constraints:__
```
0 <= low <= high <= 10^9
```
#### EXPLANATION:

这道题目比较简单, 其实只要找到规律即可.

#### SOLUTION:
```kotlin
class Solution {
    fun countOdds(low: Int, high: Int): Int {
        return if ((high - low + 1) % 2 == 0) {
            (high - low + 1) / 2
        } else {
            if (high % 2 != 0 && low % 2 != 0) {
                (high - low) / 2 + 1
            } else {
                (high - low) / 2
            }
        }
    }
}

```
