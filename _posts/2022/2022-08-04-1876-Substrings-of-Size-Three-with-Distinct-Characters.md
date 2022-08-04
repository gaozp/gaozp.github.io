---
layout: post
title: 1876. Substrings of Size Three with Distinct Characters
categories: [leetcode]
---
#### QUESTION:
A string is good if there are no repeated characters.

Given a string s​​​​​, return the number of good substrings of length three in s​​​​​​.

Note that if there are multiple occurrences of the same substring, every occurrence should be counted.

A substring is a contiguous sequence of characters in a string.

 

__Example 1:__
```
Input: s = "xyzzaz"
Output: 1
Explanation: There are 4 substrings of size 3: "xyz", "yzz", "zza", and "zaz". 
The only good substring of length 3 is "xyz".
```
__Example 2:__
```
Input: s = "aababcabc"
Output: 4
Explanation: There are 7 substrings of size 3: "aab", "aba", "bab", "abc", "bca", "cab", and "abc".
The good substrings are "abc", "bca", "cab", and "abc".
```
 

__Constraints:__
```
1 <= s.length <= 100
s​​​​​​ consists of lowercase English letters.
```
#### EXPLANATION:

easy的题目,一个for循环就能搞定.

#### SOLUTION:
```swift
class Solution {
    func countGoodSubstrings(_ s: String) -> Int {
        var result:Int = 0
        var arr:[Character] = Array(s)
        if arr.count < 3 {
            return result
        }
        for index in stride(from: 0, to: s.count - 2, by: 1) {
            if arr[index] != arr[index + 1] && arr[index] != arr[index+2] && arr[index+1] != arr[index+2] {
                result += 1
            }
        }
        return result
    }
}
```
