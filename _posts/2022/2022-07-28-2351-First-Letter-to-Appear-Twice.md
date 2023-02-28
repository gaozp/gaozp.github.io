---
layout: post
title: 2351. First Letter to Appear Twice
categories: [leetcode]
---
#### QUESTION:
Given a string s consisting of lowercase English letters, return the first letter to appear twice.

Note:

A letter a appears twice before another letter b if the second occurrence of a is before the second occurrence of b.
s will contain at least one letter that appears twice.
 

__Example 1:__
```
Input: s = "abccbaacz"
Output: "c"
Explanation:
The letter 'a' appears on the indexes 0, 5 and 6.
The letter 'b' appears on the indexes 1 and 4.
The letter 'c' appears on the indexes 2, 3 and 7.
The letter 'z' appears on the index 8.
The letter 'c' is the first letter to appear twice, because out of all the letters the index of its second occurrence is the smallest.
```
__Example 2:__
```
Input: s = "abcdd"
Output: "d"
Explanation:
The only letter that appears twice is 'd' so we return 'd'.
```
 

__Constraints:__
```
2 <= s.length <= 100
s consists of lowercase English letters.
s has at least one repeated letter.
```
#### EXPLANATION:

easy的题目,一个for循环就可以搞定.

#### SOLUTION:
```swift
class Solution {
    func repeatedCharacter(_ s: String) -> Character {
        var dic:Dictionary<Character,Int> = [:]
        for char in Array(s) {
            if dic[char] == nil {
                dic[char] = 1
            } else {
                dic[char] = dic[char]! + 1
            }
            if dic[char] == 2 {
                return char
            }
        }
        return " "
    }
}
```
