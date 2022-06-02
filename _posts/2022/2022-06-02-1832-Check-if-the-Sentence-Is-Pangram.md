---
layout: post
title: 1832. Check if the Sentence Is Pangram
categories: [leetcode]
---
#### QUESTION:
A pangram is a sentence where every letter of the English alphabet appears at least once.

Given a string sentence containing only lowercase English letters, return true if sentence is a pangram, or false otherwise.

 

__Example 1:__
```
Input: sentence = "thequickbrownfoxjumpsoverthelazydog"
Output: true
Explanation: sentence contains at least one of every letter of the English alphabet.
```
__Example 2:__
```
Input: sentence = "leetcode"
Output: false
 ```

__Constraints:__
```
1 <= sentence.length <= 1000
sentence consists of lowercase English letters.
```
#### EXPLANATION:

简单题目,就不多说了. 用数字的ascii码值去进行匹配即可. 

#### SOLUTION:
```swift
class Solution {
    func checkIfPangram(_ sentence: String) -> Bool {
        
        for i in 97...122 {
            if !sentence.contains(Character(UnicodeScalar(i)!)){
                return false 
            }
        }
        
        return true
    }}
```
