---
layout: post
title: 1897. Redistribute Characters to Make All Strings Equal
categories: [leetcode]
---
#### QUESTION:
You are given an array of strings words (0-indexed).

In one operation, pick two distinct indices i and j, where words[i] is a non-empty string, and move any character from words[i] to any position in words[j].

Return true if you can make every string in words equal using any number of operations, and false otherwise.

 

__Example 1:__
```
Input: words = ["abc","aabc","bc"]
Output: true
Explanation: Move the first 'a' in words[1] to the front of words[2],
to make words[1] = "abc" and words[2] = "abc".
All the strings are now equal to "abc", so return true.
```
__Example 2:__
```
Input: words = ["ab","a"]
Output: false
Explanation: It is impossible to make all the strings equal using the operation.
```
 

__Constraints:__
```
1 <= words.length <= 100
1 <= words[i].length <= 100
words[i] consists of lowercase English letters.
```
#### EXPLANATION:

将所有word种的字符的数量都算出来, 再看看能不能整除当前words的count.

#### SOLUTION:
```swift
class Solution {
    func makeEqual(_ words: [String]) -> Bool {
        var dic: Dictionary<Character,Int> = [:]
        for word in words {
            for ch in word {
                dic[ch] = dic[ch] == nil ? 1 : dic[ch]! + 1
            }
        }
        
        for value in dic.values {
            if (value % words.count != 0) {
                return false
            }
        }
        return true
    }
}
```
