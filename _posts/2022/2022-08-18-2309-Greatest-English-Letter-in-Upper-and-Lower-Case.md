---
layout: post
title: 2309. Greatest English Letter in Upper and Lower Case
categories: [leetcode]
---
#### QUESTION:
Given a string of English letters s, return the greatest English letter which occurs as both a lowercase and uppercase letter in s. The returned letter should be in uppercase. If no such letter exists, return an empty string.

An English letter b is greater than another letter a if b appears after a in the English alphabet.

 

__Example 1:__
```
Input: s = "lEeTcOdE"
Output: "E"
Explanation:
The letter 'E' is the only letter to appear in both lower and upper case.
```
__Example 2:__
```
Input: s = "arRAzFif"
Output: "R"
Explanation:
The letter 'R' is the greatest letter to appear in both lower and upper case.
Note that 'A' and 'F' also appear in both lower and upper case, but 'R' is greater than 'F' or 'A'.
```
__Example 3:__
```
Input: s = "AbCdEfGhIjK"
Output: ""
Explanation:
There is no letter that appears in both lower and upper case.
```
 

__Constraints:__
```
1 <= s.length <= 1000
s consists of lowercase and uppercase English letters.
```
#### EXPLANATION:

用一个二维数组来标记是否大小写都有.然后for循环拿到最后一个出现的.

#### SOLUTION:
```swift
class Solution {
    func greatestLetter(_ s: String) -> String {
        var alphaBeta:[[Bool]] = Array(repeating: [false,false] , count: 26)
        for ch in s {
            if ch.asciiValue! >= 97 {
                alphaBeta[Int(ch.asciiValue!) - 97][0] = true
            } else if ch.asciiValue! <= 90 {
                alphaBeta[Int(ch.asciiValue!) - 65][1] = true
            }
        }
        var result:String = ""
        for index in alphaBeta.indices {
            if alphaBeta[index][0] && alphaBeta[index][1] {
                result = String(UnicodeScalar(65 + index)!)
            }
        }
        
        return result
    }
}
```
