---
layout: post
title: 2108. Find First Palindromic String in the Array
categories: [leetcode]
---
#### QUESTION:
Given an array of strings words, return the first palindromic string in the array. If there is no such string, return an empty string "".

A string is palindromic if it reads the same forward and backward.

 

__Example 1:__
```
Input: words = ["abc","car","ada","racecar","cool"]
Output: "ada"
Explanation: The first string that is palindromic is "ada".
Note that "racecar" is also palindromic, but it is not the first.
```
__Example 2:__
```
Input: words = ["notapalindrome","racecar"]
Output: "racecar"
Explanation: The first and only string that is palindromic is "racecar".
```
__Example 3:__
```
Input: words = ["def","ghi"]
Output: ""
Explanation: There are no palindromic strings, so the empty string is returned.
```

__Constraints:__
```
1 <= words.length <= 100
1 <= words[i].length <= 100
words[i] consists only of lowercase English letters.
```
#### EXPLANATION:

这道题目就比较简单, 直接for循环拿出来进行reverse, 然后比对一下,如果结果相同, 那么就可以返回结果了. 

#### SOLUTION:
```swift
class Solution {
    func firstPalindrome(_ words: [String]) -> String {
        var result:String = ""
        for word in words {
            let palindromic = word.reversed()
            if String(palindromic)==word {
                return word
            }
        }
        return result
    }
}
```
