---
layout: post
title: 2942. Find Words Containing Character
categories: [leetcode]
---
#### QUESTION:
You are given a 0-indexed array of strings words and a character x.

Return an array of indices representing the words that contain the character x.

Note that the returned array may be in any order.

 

__Example 1:__
```
Input: words = ["leet","code"], x = "e"
Output: [0,1]
Explanation: "e" occurs in both words: "leet", and "code". Hence, we return indices 0 and 1.
```
__Example 2:__
```
Input: words = ["abc","bcd","aaaa","cbc"], x = "a"
Output: [0,2]
Explanation: "a" occurs in "abc", and "aaaa". Hence, we return indices 0 and 2.
```
__Example 3:__
```
Input: words = ["abc","bcd","aaaa","cbc"], x = "z"
Output: []
Explanation: "z" does not occur in any of the words. Hence, we return an empty array.
```
 

__Constraints:__
```
1 <= words.length <= 50
1 <= words[i].length <= 50
x is a lowercase English letter.
words[i] consists only of lowercase English letters.
```
#### EXPLANATION:

一个for循环就可以搞定

#### SOLUTION:
```swift
class Solution {
    func findWordsContaining(_ words: [String], _ x: Character) -> [Int] {
        var result: [Int] = []
        for i in 0..<words.count {
            if words[i] .contains(x) {
                result.append(i)
            }
        }
        return result
    }
}
```
