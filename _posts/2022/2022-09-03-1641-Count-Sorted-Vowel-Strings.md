---
layout: post
title: 1641. Count Sorted Vowel Strings
categories: [leetcode]
---
#### QUESTION:
Given an integer n, return the number of strings of length n that consist only of vowels (a, e, i, o, u) and are lexicographically sorted.

A string s is lexicographically sorted if for all valid i, s[i] is the same as or comes before s[i+1] in the alphabet.

 

__Example 1:__
```
Input: n = 1
Output: 5
Explanation: The 5 sorted strings that consist of vowels only are ["a","e","i","o","u"].
```
__Example 2:__
```
Input: n = 2
Output: 15
Explanation: The 15 sorted strings that consist of vowels only are
["aa","ae","ai","ao","au","ee","ei","eo","eu","ii","io","iu","oo","ou","uu"].
Note that "ea" is not a valid string since 'e' comes after 'a' in the alphabet.
```
__Example 3:__
```
Input: n = 33
Output: 66045
```
 

__Constraints:__
```
1 <= n <= 50
```
#### EXPLANATION:

需要使用dp的方式来做, 首先我们知道, a后面可以跟着eiou , 所以那么就可以获取到, 每个字母后面的数量都是可以累计的. 比如a字母后面就是后面每个字母的可能, 那么只要一个for循环就可以获取到最终结果.

#### SOLUTION:
```swift
class Solution {
    func countVowelStrings(_ n: Int) -> Int {
        var a = 1, e = 1 , i = 1 , o = 1 , u = 1
        for index in 1..<n {
            a = a + e + i + o + u
            e = e + i + o + u
            i = i + o + u
            o = o + u
        }
        return a + e + i + o + u
    }
}
```
