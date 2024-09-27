---
layout: post
title: 3146. Permutation Difference between Two Strings
categories: [leetcode]
---
#### QUESTION:
You are given two strings s and t such that every character occurs at most once in s and t is a permutation of s.

The permutation difference between s and t is defined as the sum of the absolute difference between the index of the occurrence of each character in s and the index of the occurrence of the same character in t.

Return the permutation difference between s and t.

 

__Example 1:__
```
Input: s = "abc", t = "bac"

Output: 2

Explanation:

For s = "abc" and t = "bac", the permutation difference of s and t is equal to the sum of:

The absolute difference between the index of the occurrence of "a" in s and the index of the occurrence of "a" in t.
The absolute difference between the index of the occurrence of "b" in s and the index of the occurrence of "b" in t.
The absolute difference between the index of the occurrence of "c" in s and the index of the occurrence of "c" in t.
That is, the permutation difference between s and t is equal to |0 - 1| + |1 - 0| + |2 - 2| = 2.
```
__Example 2:__
```
Input: s = "abcde", t = "edbac"

Output: 12

Explanation: The permutation difference between s and t is equal to |0 - 3| + |1 - 2| + |2 - 4| + |3 - 1| + |4 - 0| = 12.
```
 

__Constraints:__
```
1 <= s.length <= 26
Each character occurs at most once in s.
t is a permutation of s.
s consists only of lowercase English letters.
```
#### EXPLANATION:

用两个数组来装每个字母对应的坐标, 然后将两个数组进行绝对值相减, 就能得到最终的结果了.

#### SOLUTION:
```swift
class Solution {
    func findPermutationDifference(_ s: String, _ t: String) -> Int {
        var indexS: [Int] = Array(repeating: 0, count: 26)
        var indexT: [Int] = Array(repeating: 0, count: 26)
        var tmpArrS: [Character] = Array(s)
        var tmpArrT: [Character] = Array(t)
        for index in 0...tmpArrS.count-1 {
            indexS[Int(tmpArrS[index].asciiValue!) - 97] = index
        }
        for index in 0...tmpArrT.count-1 {
            indexT[Int(tmpArrT[index].asciiValue!) - 97] = index
        }
        var result: Int = 0
        for index in 0...25 {
            result += abs(indexS[index] - indexT[index])
        }
        return result
    }
}
```
