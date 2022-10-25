---
layout: post
title: 1668. Maximum Repeating Substring
categories: [leetcode]
---
#### QUESTION:
For a string sequence, a string word is k-repeating if word concatenated k times is a substring of sequence. The word's maximum k-repeating value is the highest value k where word is k-repeating in sequence. If word is not a substring of sequence, word's maximum k-repeating value is 0.

Given strings sequence and word, return the maximum k-repeating value of word in sequence.

 

__Example 1:__
```
Input: sequence = "ababc", word = "ab"
Output: 2
Explanation: "abab" is a substring in "ababc".
```
__Example 2:__
```
Input: sequence = "ababc", word = "ba"
Output: 1
Explanation: "ba" is a substring in "ababc". "baba" is not a substring in "ababc".
```
__Example 3:__
```
Input: sequence = "ababc", word = "ac"
Output: 0
Explanation: "ac" is not a substring in "ababc". 
```
 

__Constraints:__
```
1 <= sequence.length <= 100
1 <= word.length <= 100
sequence and word contains only lowercase English letters.
```
#### EXPLANATION:

按照题意模拟出来即可.

#### SOLUTION:
```swift
class Solution {
    func maxRepeating(_ sequence: String, _ word: String) -> Int {
        var count = 0
        var newStr = word
        while (sequence.contains(newStr)) {
            count += 1
            newStr += word
        }
        return count
    }
}
```
