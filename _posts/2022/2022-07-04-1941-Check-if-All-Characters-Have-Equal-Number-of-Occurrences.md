---
layout: post
title: 1941. Check if All Characters Have Equal Number of Occurrences
categories: [leetcode]
---
#### QUESTION:
Given a string s, return true if s is a good string, or false otherwise.

A string s is good if all the characters that appear in s have the same number of occurrences (i.e., the same frequency).

 

__Example 1:__
```
Input: s = "abacbc"
Output: true
Explanation: The characters that appear in s are 'a', 'b', and 'c'. All characters occur 2 times in s.
```
__Example 2:__
```
Input: s = "aaabb"
Output: false
Explanation: The characters that appear in s are 'a' and 'b'.
'a' occurs 3 times while 'b' occurs 2 times, which is not the same number of times.
 ```

__Constraints:__
```
1 <= s.length <= 1000
s consists of lowercase English letters.
```
#### EXPLANATION:

easy的题目, 一个for循环就可以了.

#### SOLUTION:
```swift
class Solution {
    func areOccurrencesEqual(_ s: String) -> Bool {
        var array:[Int] = Array(repeating: 0, count: 26)
        var maxCount:Int = -1
        for ch in s {
            array[Int(ch.asciiValue! - 97)] += 1
            maxCount = max(maxCount, array[Int(ch.asciiValue! - 97)])
        }
        var flag:Bool = true
        for count in array {
            if count != 0 && count != maxCount {
                return false
            }
        }
        return flag
    }
}
```
