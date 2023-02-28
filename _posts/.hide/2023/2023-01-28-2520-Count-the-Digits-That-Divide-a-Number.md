---
layout: post
title: 2520. Count the Digits That Divide a Number
categories: [leetcode]
---
#### QUESTION:
Given an integer num, return the number of digits in num that divide num.

An integer val divides nums if nums % val == 0.

 

__Example 1:__
```
Input: num = 7
Output: 1
Explanation: 7 divides itself, hence the answer is 1.
```
__Example 2:__
```
Input: num = 121
Output: 2
Explanation: 121 is divisible by 1, but not 2. Since 1 occurs twice as a digit, we return 2.
```
__Example 3:__
```
Input: num = 1248
Output: 4
Explanation: 1248 is divisible by all of its digits, hence the answer is 4.
```
 

__Constraints:__
```
1 <= num <= 109
num does not contain 0 as one of its digits.
```
#### EXPLANATION:

easy的题目, 直接除即可.

#### SOLUTION:
```swift
class Solution {
    func countDigits(_ num: Int) -> Int {
        var count = 0
        var mutableNum = num
        while mutableNum % 10 != 0 {
            let digit = mutableNum % 10
            if num % digit == 0 {
                count += 1
            }
            mutableNum /= 10
        }
        return count
    }
}
```
