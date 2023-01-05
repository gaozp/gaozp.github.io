---
layout: post
title: 1529. Minimum Suffix Flips
categories: [leetcode]
---
#### QUESTION:
You are given a 0-indexed binary string target of length n. You have another binary string s of length n that is initially set to all zeros. You want to make s equal to target.

In one operation, you can pick an index i where 0 <= i < n and flip all bits in the inclusive range [i, n - 1]. Flip means changing '0' to '1' and '1' to '0'.

Return the minimum number of operations needed to make s equal to target.

 

__Example 1:__
```
Input: target = "10111"
Output: 3
Explanation: Initially, s = "00000".
Choose index i = 2: "00000" -> "00111"
Choose index i = 0: "00111" -> "11000"
Choose index i = 1: "11000" -> "10111"
We need at least 3 flip operations to form target.
```
__Example 2:__
```
Input: target = "101"
Output: 3
Explanation: Initially, s = "000".
Choose index i = 0: "000" -> "111"
Choose index i = 1: "111" -> "100"
Choose index i = 2: "100" -> "101"
We need at least 3 flip operations to form target.
```
__Example 3:__
```
Input: target = "00000"
Output: 0
Explanation: We do not need any operations since the initial s already equals target.
```
 

__Constraints:__
```
n == target.length
1 <= n <= 105
target[i] is either '0' or '1'.
```
#### EXPLANATION:

因为每次都是统一变换, 那么就可以直接一个for循环, 用一个tmp来表示当前剩余的字符现状, 如果不相等就切换, 否则就继续向前即可.

#### SOLUTION:
```swift
class Solution {
    func minFlips(_ target: String) -> Int {
        var result = 0
        var ch:Character = "0"
        for ta in target {
            if ta != ch {
                ch = ta
                result += 1
            }
        }
        return result
    }
}
```
