---
layout: post
title: 1961. Check If String Is a Prefix of Array
categories: [leetcode]
---
#### QUESTION:
Given a string s and an array of strings words, determine whether s is a prefix string of words.

A string s is a prefix string of words if s can be made by concatenating the first k strings in words for some positive k no larger than words.length.

Return true if s is a prefix string of words, or false otherwise.

 

__Example 1:__
```
Input: s = "iloveleetcode", words = ["i","love","leetcode","apples"]
Output: true
Explanation:
s can be made by concatenating "i", "love", and "leetcode" together.
```
__Example 2:__
```
Input: s = "iloveleetcode", words = ["apples","i","love","leetcode"]
Output: false
Explanation:
It is impossible to make s using a prefix of arr.
```
 

__Constraints:__
```
1 <= words.length <= 100
1 <= words[i].length <= 20
1 <= s.length <= 1000
words[i] and s consist of only lowercase English letters.
```
#### EXPLANATION:

比较简单的题目, 直接模拟题意即可.

#### SOLUTION:
```swift
class Solution {
    func isPrefixString(_ s: String, _ words: [String]) -> Bool {
        var tmpString = ""
        for word in words {
            tmpString += word
            if !s.hasPrefix(tmpString) {
                return false
            }
            if s == tmpString {
                return true
            }
        }
        return false
    }
}
```
