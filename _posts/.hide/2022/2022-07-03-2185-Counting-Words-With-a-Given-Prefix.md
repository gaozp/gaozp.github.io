---
layout: post
title: 2185. Counting Words With a Given Prefix
categories: [leetcode]
---
#### QUESTION:
You are given an array of strings words and a string pref.

Return the number of strings in words that contain pref as a prefix.

A prefix of a string s is any leading contiguous substring of s.

 

__Example 1:__
```
Input: words = ["pay","attention","practice","attend"], pref = "at"
Output: 2
Explanation: The 2 strings that contain "at" as a prefix are: "attention" and "attend".
```
__Example 2:__
```
Input: words = ["leetcode","win","loops","success"], pref = "code"
Output: 0
Explanation: There are no strings that contain "code" as a prefix.
```
 

__Constraints:__
```
1 <= words.length <= 100
1 <= words[i].length, pref.length <= 100
words[i] and pref consist of lowercase English letters.
```
#### EXPLANATION:

easy的题目, 直接一个for循环判断就可以.

#### SOLUTION:
```swift
class Solution {
    func prefixCount(_ words: [String], _ pref: String) -> Int {
        var result:Int = 0
        for word in words {
            if word.hasPrefix(pref) {
                result += 1
            }
        }
        return result
    }
}
```
