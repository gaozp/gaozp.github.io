---
layout: post
title: 1446. Consecutive Characters
categories: [leetcode]
---
#### QUESTION:
The power of the string is the maximum length of a non-empty substring that contains only one unique character.

Given a string s, return the power of s.

 

__Example 1:__
```
Input: s = "leetcode"
Output: 2
Explanation: The substring "ee" is of length 2 with the character 'e' only.
```
__Example 2:__
```
Input: s = "abbcccddddeeeeedcba"
Output: 5
Explanation: The substring "eeeee" is of length 5 with the character 'e' only.
```
__Example 3:__
```
Input: s = "triplepillooooow"
Output: 5
```
__Example 4:__
```
Input: s = "hooraaaaaaaaaaay"
Output: 11
```
__Example 5:__
```
Input: s = "tourist"
Output: 1
```

__Constraints:__
```
1 <= s.length <= 500
s consists of only lowercase English letters.
```
#### EXPLANATION:
比较简单的和前一个位置的比较和替换, 就不多说了.
#### SOLUTION:
```swift
class Solution {
    func maxPower(_ s: String) -> Int {
        var arrayS = Array(s)
        var result: Int = 0
        var tmp = 0;
        var pre: Character = "1"
        for ch in arrayS {
            if ch == pre {
                tmp += 1;
            } else {
                pre = ch
                tmp = 1;
            }
            result = result > tmp ? result : tmp
        }
        return result;
    }
}
```
