---
layout: post
title: 1347. Minimum Number of Steps to Make Two Strings Anagram
categories: [leetcode]
---
#### QUESTION:
You are given two strings of the same length s and t. In one step you can choose any character of t and replace it with another character.

Return the minimum number of steps to make t an anagram of s.

An Anagram of a string is a string that contains the same characters with a different (or the same) ordering.

 

__Example 1:__
```
Input: s = "bab", t = "aba"
Output: 1
Explanation: Replace the first 'a' in t with b, t = "bba" which is anagram of s.
```
__Example 2:__
```
Input: s = "leetcode", t = "practice"
Output: 5
Explanation: Replace 'p', 'r', 'a', 'i' and 'c' from t with proper characters to make t anagram of s.
```
__Example 3:__
```
Input: s = "anagram", t = "mangaar"
Output: 0
Explanation: "anagram" and "mangaar" are anagrams. 
```
 

__Constraints:__
```
1 <= s.length <= 5 * 104
s.length == t.length
s and t consist of lowercase English letters only.
```
#### EXPLANATION:

这道题目虽然是一个medium的题目, 但是如果仔细观察就能发现, 只要将两个string, 相差的数量/2就能获得最终的结果. 比如example1中的情况, s[a] - t[a] = 1 ,  s[b]-t[b] = 1 , 那么我们就可以知道, 将其中一个b转换成a就可以得到相同的结果, 那么就是需要吧两者的差值/2即可.

#### SOLUTION:
```swift
class Solution {
    func minSteps(_ s: String, _ t: String) -> Int {
        var sArray:[Int] = Array(repeating: 0, count: 26)
        var tArray:[Int] = Array(repeating: 0, count: 26)
        for ch in s {
            sArray[Int(ch.asciiValue!) - 97] += 1
        }
        for ch in t {
            tArray[Int(ch.asciiValue!) - 97] += 1
        }
        var sum:Int = 0
        for index in 0...25 {
            sum += abs(sArray[index] - tArray[index])
        }
        return sum/2
    }
}
```
