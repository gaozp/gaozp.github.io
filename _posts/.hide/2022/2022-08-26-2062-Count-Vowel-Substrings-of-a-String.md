---
layout: post
title: 2062. Count Vowel Substrings of a String
categories: [leetcode]
---
#### QUESTION:
A substring is a contiguous (non-empty) sequence of characters within a string.

A vowel substring is a substring that only consists of vowels ('a', 'e', 'i', 'o', and 'u') and has all five vowels present in it.

Given a string word, return the number of vowel substrings in word.

 

__Example 1:__
```
Input: word = "aeiouu"
Output: 2
Explanation: The vowel substrings of word are as follows (underlined):
- "aeiouu"
- "aeiouu"
```
__Example 2:__
```
Input: word = "unicornarihan"
Output: 0
Explanation: Not all 5 vowels are present, so there are no vowel substrings.
```
__Example 3:__
```
Input: word = "cuaieuouac"
Output: 7
Explanation: The vowel substrings of word are as follows (underlined):
- "cuaieuouac"
- "cuaieuouac"
- "cuaieuouac"
- "cuaieuouac"
- "cuaieuouac"
- "cuaieuouac"
- "cuaieuouac"
```
 

__Constraints:__
```
1 <= word.length <= 100
word consists of lowercase English letters only.
```
#### EXPLANATION:

也是比较容易的, 只要用一个数组去记录当前是否有原因字母被添加就可以.

#### SOLUTION:
```swift
class Solution {
    func countVowelSubstrings(_ word: String) -> Int {
        if word.count < 5 {
            return 0
        }
        var wordArr:[Character] = Array(word)
        var arr:[Character] = ["a","i","e","o","u"]
        var arrTmp:[Bool] = Array(repeating: false, count: 5)
        var result:Int = 0
        for indexI in 0...wordArr.count - 5 {
            if (arr.contains(wordArr[indexI])) {
                arrTmp[arr.firstIndex(of: wordArr[indexI])!] = true
                var indexJ = indexI + 1
                while indexJ < wordArr.count && arr.contains(wordArr[indexJ]) {
                    arrTmp[arr.firstIndex(of: wordArr[indexJ])!] = true
                    if arrTmp[0] && arrTmp[1] && arrTmp[2] && arrTmp[3] && arrTmp[4] {
                        result += 1
                    }
                    indexJ += 1
                }
                arrTmp = Array(repeating: false, count: 5)
            }
        }
        return result
    }
}
```
