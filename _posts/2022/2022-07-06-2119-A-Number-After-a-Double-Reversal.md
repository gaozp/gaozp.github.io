---
layout: post
title: 2119. A Number After a Double Reversal
categories: [leetcode]
---
#### QUESTION:
Reversing an integer means to reverse all its digits.

For example, reversing 2021 gives 1202. Reversing 12300 gives 321 as the leading zeros are not retained.
Given an integer num, reverse num to get reversed1, then reverse reversed1 to get reversed2. Return true if reversed2 equals num. Otherwise return false.

 

__Example 1:__
```
Input: num = 526
Output: true
Explanation: Reverse num to get 625, then reverse 625 to get 526, which equals num.
```
__Example 2:__
```
Input: num = 1800
Output: false
Explanation: Reverse num to get 81, then reverse 81 to get 18, which does not equal num.
```
__Example 3:__
```
Input: num = 0
Output: true
Explanation: Reverse num to get 0, then reverse 0 to get 0, which equals num.
```
 

Constraints:
```
0 <= num <= 10^6
```
#### EXPLANATION:

easy的题目, 非0的情况下判断走后一位是不是0就可以.

#### SOLUTION:
```swift
class Solution {
    func isSameAfterReversals(_ num: Int) -> Bool {
        if num == 0 {
            return true
        } else {
            var numStr:String = String(num)
            var arr = Array(numStr)
            return Int(String(arr[arr.count-1])) != 0
        }
    }
}
```
