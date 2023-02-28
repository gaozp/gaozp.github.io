---
layout: post
title: 2485. Find the Pivot Integer
categories: [leetcode]
---
#### QUESTION:
Given a positive integer n, find the pivot integer x such that:

The sum of all elements between 1 and x inclusively equals the sum of all elements between x and n inclusively.
Return the pivot integer x. If no such integer exists, return -1. It is guaranteed that there will be at most one pivot index for the given input.

 

__Example 1:__
```
Input: n = 8
Output: 6
Explanation: 6 is the pivot integer since: 1 + 2 + 3 + 4 + 5 + 6 = 6 + 7 + 8 = 21.
```
__Example 2:__
```
Input: n = 1
Output: 1
Explanation: 1 is the pivot integer since: 1 = 1.
```
__Example 3:__
```
Input: n = 4
Output: -1
Explanation: It can be proved that no such integer exist.
```
 

__Constraints:__
```
1 <= n <= 1000
```
#### EXPLANATION:

只要一个从头开始算和, 另外一个开始减和即可. 如果两者相等, 就说明找到了.

#### SOLUTION:
```swift
class Solution {
    func pivotInteger(_ n: Int) -> Int {
        var result = -1
        var preSum = 0
        var postSum = 0
        for index in 1...n {
            postSum += index
        }
        for index in 1...n {
            preSum += index
            postSum -= index-1
            if (preSum == postSum) {
                return index
            }
        }
        return result
    }
}
```
