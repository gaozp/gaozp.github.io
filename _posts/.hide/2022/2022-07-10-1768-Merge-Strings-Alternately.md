---
layout: post
title: 1768. Merge Strings Alternately
categories: [leetcode]
---
#### QUESTION:
You are given two strings word1 and word2. Merge the strings by adding letters in alternating order, starting with word1. If a string is longer than the other, append the additional letters onto the end of the merged string.

Return the merged string.

 

__Example 1:__
```
Input: word1 = "abc", word2 = "pqr"
Output: "apbqcr"
Explanation: The merged string will be merged as so:
word1:  a   b   c
word2:    p   q   r
merged: a p b q c r
```
__Example 2:__
```
Input: word1 = "ab", word2 = "pqrs"
Output: "apbqrs"
Explanation: Notice that as word2 is longer, "rs" is appended to the end.
word1:  a   b 
word2:    p   q   r   s
merged: a p b q   r   s
```
__Example 3:__
```
Input: word1 = "abcd", word2 = "pq"
Output: "apbqcd"
Explanation: Notice that as word1 is longer, "cd" is appended to the end.
word1:  a   b   c   d
word2:    p   q 
merged: a p b q c   d
```
 

__Constraints:__

```
1 <= word1.length, word2.length <= 100
word1 and word2 consist of lowercase English letters.
```
#### EXPLANATION:

easy的题目, 直接用一个flag进行for循环就行

#### SOLUTION:
```swift
class Solution {
    func mergeAlternately(_ word1: String, _ word2: String) -> String {
        var result:String = ""
        var flag:Bool = false
        var index:Int = 0
        var arr1 = Array(word1)
        var arr2 = Array(word2)
        while index < arr1.count || index < arr2.count {
            if flag {
                result += index > arr2.count - 1 ? "" : String(arr2[index])
                index += 1
            } else {
                result += index > arr1.count - 1 ? "" : String(arr1[index])
            }
            flag = !flag
        }
        return result
    }
}
```
