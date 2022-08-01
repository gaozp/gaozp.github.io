---
layout: post
title: 2124. Check if All A's Appears Before All B's
categories: [leetcode]
---
#### QUESTION:
Given a string s consisting of only the characters 'a' and 'b', return true if every 'a' appears before every 'b' in the string. Otherwise, return false.

 

__Example 1:__
```
Input: s = "aaabbb"
Output: true
Explanation:
The 'a's are at indices 0, 1, and 2, while the 'b's are at indices 3, 4, and 5.
Hence, every 'a' appears before every 'b' and we return true.
```
__Example 2:__
```
Input: s = "abab"
Output: false
Explanation:
There is an 'a' at index 2 and a 'b' at index 1.
Hence, not every 'a' appears before every 'b' and we return false.
```
__Example 3:__
```
Input: s = "bbb"
Output: true
Explanation:
There are no 'a's, hence, every 'a' appears before every 'b' and we return true.
```
 

__Constraints:__
```
1 <= s.length <= 100
s[i] is either 'a' or 'b'.
```
#### EXPLANATION:

easy的题目, 一个for循环就行.

#### SOLUTION:
```swift
class Solution {
    func checkString(_ s: String) -> Bool {
        var result:Bool = true
        var arr:[Character] = Array(s)
        for index in stride(from: 1, to: s.count, by: 1) {
            if arr[index].asciiValue! < arr[index-1].asciiValue! {
                return false
            }
        }
        return result
    }
}
```
