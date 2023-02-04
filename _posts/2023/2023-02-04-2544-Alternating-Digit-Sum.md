---
layout: post
title: 2544. Alternating Digit Sum
categories: [leetcode]
---
#### QUESTION:
You are given a positive integer n. Each digit of n has a sign according to the following rules:

The most significant digit is assigned a positive sign.
Each other digit has an opposite sign to its adjacent digits.
Return the sum of all digits with their corresponding sign.

 

__Example 1:__
```
Input: n = 521
Output: 4
Explanation: (+5) + (-2) + (+1) = 4.
```
__Example 2:__
```
Input: n = 111
Output: 1
Explanation: (+1) + (-1) + (+1) = 1.
```
__Example 3:__
```
Input: n = 886996
Output: 0
Explanation: (+8) + (-8) + (+6) + (-9) + (+9) + (-6) = 0.
```
 

__Constraints:__
```
1 <= n <= 109
```
#### EXPLANATION:

easy的题目, 一个for循环就可以解决.

#### SOLUTION:
```swift
class Solution {
    func alternateDigitSum(_ n: Int) -> Int {
        var result: Int = 0
        var flag = true
        for ch in String(n) {
            if flag {
                result += Int(String(ch))!
            } else {
                result -= Int(String(ch))!
            }
            flag = !flag
        }
        return result
    }
}
```
