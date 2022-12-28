---
layout: post
title: 1796. Second Largest Digit in a String
categories: [leetcode]
---
#### QUESTION:
Given an alphanumeric string s, return the second largest numerical digit that appears in s, or -1 if it does not exist.

An alphanumeric string is a string consisting of lowercase English letters and digits.

 

__Example 1:__
```
Input: s = "dfa12321afd"
Output: 2
Explanation: The digits that appear in s are [1, 2, 3]. The second largest digit is 2.
```
__Example 2:__
```
Input: s = "abc1111"
Output: -1
Explanation: The digits that appear in s are [1]. There is no second largest digit. 
```
 

__Constraints:__
```
1 <= s.length <= 500
s consists of only lowercase English letters and/or digits.
```
#### EXPLANATION:

将数字数出来放在set里去重, 然后排序后取倒数第二个即可.

#### SOLUTION:
```swift
class Solution {
    func secondHighest(_ s: String) -> Int {
        var set:Set<Int> = Set()
        for ch in s {
            if ch.asciiValue! >= Character("0").asciiValue!
                && ch.asciiValue! <= Character("9").asciiValue! {
                set.insert(Int(String(ch))!)
            }
        }
        let arr = set.sorted()
        if arr.count >= 2 {
            return arr[arr.count - 2]
        }
        return -1
    }
}
```
