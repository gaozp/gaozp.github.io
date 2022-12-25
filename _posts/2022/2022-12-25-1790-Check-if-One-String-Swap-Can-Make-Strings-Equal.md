---
layout: post
title: 1790. Check if One String Swap Can Make Strings Equal
categories: [leetcode]
---
#### QUESTION:
You are given two strings s1 and s2 of equal length. A string swap is an operation where you choose two indices in a string (not necessarily different) and swap the characters at these indices.

Return true if it is possible to make both strings equal by performing at most one string swap on exactly one of the strings. Otherwise, return false.

 

__Example 1:__
```
Input: s1 = "bank", s2 = "kanb"
Output: true
Explanation: For example, swap the first character with the last character of s2 to make "bank".
```
__Example 2:__
```
Input: s1 = "attack", s2 = "defend"
Output: false
Explanation: It is impossible to make them equal with one string swap.
```
__Example 3:__
```
Input: s1 = "kelb", s2 = "kelb"
Output: true
Explanation: The two strings are already equal, so no string swap operation is required.
```
 

__Constraints:__
```
1 <= s1.length, s2.length <= 100
s1.length == s2.length
s1 and s2 consist of only lowercase English letters.
```
#### EXPLANATION:

首先对比两个字符串的字符数是否相同, 如果相同就需要判断不相同位置的数量, 只能是0或者是2即可.

#### SOLUTION:
```swift
class Solution {
    func areAlmostEqual(_ s1: String, _ s2: String) -> Bool {
        var arr1: [Int] = Array(repeating: 0, count: 26)
        var arr2: [Int] = Array(repeating: 0, count: 26)
        s1.forEach { ch in
            arr1[Int(ch.asciiValue!) - 97] += 1
        }
        s2.forEach { ch in
            arr2[Int(ch.asciiValue!) - 97] += 1
        }
        if arr1 != arr2 {
            return false
        }
        var count = 0
        for index in s1.indices {
            if s1[index] != s2[index] {
                count += 1
            }
        }
        return count == 2 || count == 0
    }
}
```
