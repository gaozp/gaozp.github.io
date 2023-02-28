---
layout: post
title: 2186. Minimum Number of Steps to Make Two Strings Anagram II
categories: [leetcode]
---
#### QUESTION:
You are given two strings s and t. In one step, you can append any character to either s or t.

Return the minimum number of steps to make s and t anagrams of each other.

An anagram of a string is a string that contains the same characters with a different (or the same) ordering.

 

__Example 1:__
```
Input: s = "leetcode", t = "coats"
Output: 7
Explanation: 
- In 2 steps, we can append the letters in "as" onto s = "leetcode", forming s = "leetcodeas".
- In 5 steps, we can append the letters in "leede" onto t = "coats", forming t = "coatsleede".
"leetcodeas" and "coatsleede" are now anagrams of each other.
We used a total of 2 + 5 = 7 steps.
It can be shown that there is no way to make them anagrams of each other with less than 7 steps.
```
__Example 2:__
```
Input: s = "night", t = "thing"
Output: 0
Explanation: The given strings are already anagrams of each other. Thus, we do not need any further steps.
```
 

__Constraints:__
```
1 <= s.length, t.length <= 2 * 105
s and t consist of lowercase English letters.
```
#### EXPLANATION:

这就比较简单了, 直接拿到对应的数量, 然后看看两者的差异即可.

#### SOLUTION:
```swift
class Solution {
    func minSteps(_ s: String, _ t: String) -> Int {
        var arrS: [Int] = Array(repeating: 0, count: 26)
        var arrT: [Int] = Array(repeating: 0, count: 26)
        for str in s {
            arrS[Int(str.asciiValue!) - 97] += 1
        }
        for str in t {
            arrT[Int(str.asciiValue!) - 97] += 1
        }
        var result = 0
        for i in 0...25 {
            result += abs(arrS[i] - arrT[i])
        }
        return result
    }
}
```
