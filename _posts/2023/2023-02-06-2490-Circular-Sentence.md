---
layout: post
title: 2490. Circular Sentence
categories: [leetcode]
---
#### QUESTION:
A sentence is a list of words that are separated by a single space with no leading or trailing spaces.

For example, "Hello World", "HELLO", "hello world hello world" are all sentences.
Words consist of only uppercase and lowercase English letters. Uppercase and lowercase English letters are considered different.

A sentence is circular if:

The last character of a word is equal to the first character of the next word.
The last character of the last word is equal to the first character of the first word.
For example, "leetcode exercises sound delightful", "eetcode", "leetcode eats soul" are all circular sentences. However, "Leetcode is cool", "happy Leetcode", "Leetcode" and "I like Leetcode" are not circular sentences.

Given a string sentence, return true if it is circular. Otherwise, return false.

 

__Example 1:__
```
Input: sentence = "leetcode exercises sound delightful"
Output: true
Explanation: The words in sentence are ["leetcode", "exercises", "sound", "delightful"].
- leetcode's last character is equal to exercises's first character.
- exercises's last character is equal to sound's first character.
- sound's last character is equal to delightful's first character.
- delightful's last character is equal to leetcode's first character.
The sentence is circular.
```
__Example 2:__
```
Input: sentence = "eetcode"
Output: true
Explanation: The words in sentence are ["eetcode"].
- eetcode's last character is equal to eetcode's first character.
The sentence is circular.
```
__Example 3:__
```
Input: sentence = "Leetcode is cool"
Output: false
Explanation: The words in sentence are ["Leetcode", "is", "cool"].
- Leetcode's last character is not equal to is's first character.
The sentence is not circular.
```
 

__Constraints:__
```
1 <= sentence.length <= 500
sentence consist of only lowercase and uppercase English letters and spaces.
The words in sentence are separated by a single space.
There are no leading or trailing spaces.
```
#### EXPLANATION:

easy的题目, 直接将句子转换成数组再进行匹配. 主要是当前是index, 那么下一个是index+1, 那么最后一个也就是(index+1)%count.   

#### SOLUTION:
```swift
class Solution {
    func isCircularSentence(_ sentence: String) -> Bool {
        var senten = sentence.split(separator: " ")
        for index in 0..<senten.count {
            var tmpA = senten[index]
            var tmpB = senten[(index+1)%senten.count]
            if tmpA.last != tmpB.first {
                return false
            }
        }
        return true
    }
}
```
