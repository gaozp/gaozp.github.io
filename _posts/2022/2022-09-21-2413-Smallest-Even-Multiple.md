---
layout: post
title: 2413. Smallest Even Multiple
categories: [leetcode]
---
#### QUESTION:
Given a positive integer n, return the smallest positive integer that is a multiple of both 2 and n.
 

__Example 1:__
```
Input: n = 5
Output: 10
Explanation: The smallest multiple of both 5 and 2 is 10.
```
__Example 2:__
```
Input: n = 6
Output: 6
Explanation: The smallest multiple of both 6 and 2 is 6. Note that a number is a multiple of itself.
```
 

__Constraints:__
```
1 <= n <= 150
```
#### EXPLANATION:

这道题目就比较简单了. 如果能整除2, 那么就是当前n, 如果不能就需要*2就行.

#### SOLUTION:
```swift
class Solution {
    func smallestEvenMultiple(_ n: Int) -> Int {
        if n % 2 == 0 {
            return n
        } else {
            return n * 2
        }
    }
}
```
