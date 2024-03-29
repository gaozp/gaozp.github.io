---
layout: post
title: 1332. Remove Palindromic Subsequences
categories: [leetcode]
---
#### QUESTION:
You are given a string s consisting only of letters 'a' and 'b'. In a single step you can remove one palindromic subsequence from s.

Return the minimum number of steps to make the given string empty.

A string is a subsequence of a given string if it is generated by deleting some characters of a given string without changing its order. Note that a subsequence does not necessarily need to be contiguous.

A string is called palindrome if is one that reads the same backward as well as forward.

 

__Example 1:__
```
Input: s = "ababa"
Output: 1
Explanation: s is already a palindrome, so its entirety can be removed in a single step.
```
__Example 2:__
```
Input: s = "abb"
Output: 2
Explanation: "abb" -> "bb" -> "". 
Remove palindromic subsequence "a" then "bb".
```
__Example 3:__
```
Input: s = "baabb"
Output: 2
Explanation: "baabb" -> "b" -> "". 
Remove palindromic subsequence "baab" then "b".
```
 

__Constraints:__
```
1 <= s.length <= 1000
s[i] is either 'a' or 'b'.
```
#### EXPLANATION:

因为只有a和b, 所以如果一开始不是回环的话, 那么我们可以先去除a, 然后再去除b. 那么最多也就是2步而已.

#### SOLUTION:
```swift
class Solution {
    func removePalindromeSub(_ s: String) -> Int {
        return s == String(s.reversed()) ? 1 : 2
    }
}
```
