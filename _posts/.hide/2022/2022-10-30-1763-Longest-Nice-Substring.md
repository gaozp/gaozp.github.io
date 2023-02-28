---
layout: post
title: 1763. Longest Nice Substring
categories: [leetcode]
---
#### QUESTION:
A string s is nice if, for every letter of the alphabet that s contains, it appears both in uppercase and lowercase. For example, "abABB" is nice because 'A' and 'a' appear, and 'B' and 'b' appear. However, "abA" is not because 'b' appears, but 'B' does not.

Given a string s, return the longest substring of s that is nice. If there are multiple, return the substring of the earliest occurrence. If there are none, return an empty string.

 

__Example 1:__
```
Input: s = "YazaAay"
Output: "aAa"
Explanation: "aAa" is a nice string because 'A/a' is the only letter of the alphabet in s, and both 'A' and 'a' appear.
"aAa" is the longest nice substring.
```
__Example 2:__
```
Input: s = "Bb"
Output: "Bb"
Explanation: "Bb" is a nice string because both 'B' and 'b' appear. The whole string is a substring.
```
__Example 3:__
```
Input: s = "c"
Output: ""
Explanation: There are no nice substrings.
```
 

__Constraints:__
```
1 <= s.length <= 100
s consists of uppercase and lowercase English letters.
```
#### EXPLANATION:

使用windows的方式, 通过i,j来进行移动, 将截取出来的字符串判断是否是nice, 如果是nice就进行比对即可.

#### SOLUTION:
```swift
class Solution {
    func longestNiceSubstring(_ s: String) -> String {
        var result = ""
        for i in s.indices {
            for j in s.indices {
                if (i < j) {
                    var tmpString = String(s[i...j])
                    if (isNiceString(tmpString)) {
                        result = result.count >= tmpString.count ? result : tmpString
                    }
                }
            }
        }
        return result
    }
    
    func isNiceString(_ str: String) -> Bool {
        return Double(Set(str.lowercased()).count) == (Double(Set(str).count) / 2.0)
    }
}
```
