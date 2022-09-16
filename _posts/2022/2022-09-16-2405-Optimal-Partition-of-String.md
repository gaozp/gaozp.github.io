---
layout: post
title: 2405. Optimal Partition of String
categories: [leetcode]
---
#### QUESTION:
Given a string s, partition the string into one or more substrings such that the characters in each substring are unique. That is, no letter appears in a single substring more than once.

Return the minimum number of substrings in such a partition.

Note that each character should belong to exactly one substring in a partition.

 

__Example 1:__
```
Input: s = "abacaba"
Output: 4
Explanation:
Two possible partitions are ("a","ba","cab","a") and ("ab","a","ca","ba").
It can be shown that 4 is the minimum number of substrings needed.
```
__Example 2:__
```
Input: s = "ssssss"
Output: 6
Explanation:
The only valid partition is ("s","s","s","s","s","s").
```
 

__Constraints:__
```
1 <= s.length <= 105
s consists of only English lowercase letters.
```
#### EXPLANATION:

贪心算法, 两个while循环即可获取到最终结果.

#### SOLUTION:
```swift
class Solution {
    func partitionString(_ s: String) -> Int {
        var result:[String] = []
        var index:Int = 0
        var arr:[Character] = Array(s)
        while index < arr.count {
            var tmp:[Character] = []
            while index < arr.count && !tmp.contains(arr[index]) {
                tmp.append(arr[index])
                index += 1
            }
            result.append(String(tmp))
        }
        return result.count
    }
}
```
