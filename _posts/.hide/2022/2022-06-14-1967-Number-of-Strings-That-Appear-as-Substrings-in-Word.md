---
layout: post
title: 1967. Number of Strings That Appear as Substrings in Word
categories: [leetcode]
---
#### QUESTION:
Given an array of strings patterns and a string word, return the number of strings in patterns that exist as a substring in word.

A substring is a contiguous sequence of characters within a string.

 

__Example 1:__
```
Input: patterns = ["a","abc","bc","d"], word = "abc"
Output: 3
Explanation:
- "a" appears as a substring in "abc".
- "abc" appears as a substring in "abc".
- "bc" appears as a substring in "abc".
- "d" does not appear as a substring in "abc".
3 of the strings in patterns appear as a substring in word.
```
__Example 2:__
```
Input: patterns = ["a","b","c"], word = "aaaaabbbbb"
Output: 2
Explanation:
- "a" appears as a substring in "aaaaabbbbb".
- "b" appears as a substring in "aaaaabbbbb".
- "c" does not appear as a substring in "aaaaabbbbb".
2 of the strings in patterns appear as a substring in word.
```
__Example 3:__
```
Input: patterns = ["a","a","a"], word = "ab"
Output: 3
Explanation: Each of the patterns appears as a substring in word "ab".
 ```

__Constraints:__
```
1 <= patterns.length <= 100
1 <= patterns[i].length <= 100
1 <= word.length <= 100
patterns[i] and word consist of lowercase English letters.
```
#### EXPLANATION:

这道题目就太过简单了, for循环遍历即可.

#### SOLUTION:
```swift
class Solution {
    func numOfStrings(_ patterns: [String], _ word: String) -> Int {
        var result:Int = 0
        for pattern in patterns {
            if word.contains(pattern) {
                result += 1
            }
        }
        return result
    }
}
```
