---
layout: post
title: 1374. Generate a String With Characters That Have Odd Counts
categories: [leetcode]
---
#### QUESTION:
Given an integer n, return a string with n characters such that each character in such string occurs an odd number of times.

The returned string must contain only lowercase English letters. If there are multiples valid strings, return any of them.  

 

__Example 1:__
```
Input: n = 4
Output: "pppz"
Explanation: "pppz" is a valid string since the character 'p' occurs three times and the character 'z' occurs once. Note that there are many other valid strings such as "ohhh" and "love".
```
__Example 2:__
```
Input: n = 2
Output: "xy"
Explanation: "xy" is a valid string since the characters 'x' and 'y' occur once. Note that there are many other valid strings such as "ag" and "ur".
```
__Example 3:__
```
Input: n = 7
Output: "holasss"
 ```

__Constraints:__
```
1 <= n <= 500
```
#### EXPLANATION:

这道题目怪不得那么多人点踩, 确实没有任何意义.

#### SOLUTION:
```swift
class Solution {
    func generateTheString(_ n: Int) -> String {
        if n == 1 {
            return "a"
        }
        if n == 2 {
            return "xy"
        }
        if n == 3 {
            return "xyz"
        }
        if n % 2 == 0 {
            return String (Array(repeating: "a", count: n - 1))+"b"
        } else {
            return String(Array(repeating: "a", count: n-2)) + "yz"
        }
    }
}
```
