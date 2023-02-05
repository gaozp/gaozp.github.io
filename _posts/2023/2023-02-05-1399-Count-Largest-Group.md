---
layout: post
title: 1399. Count Largest Group
categories: [leetcode]
---
#### QUESTION:
You are given an integer n.

Each number from 1 to n is grouped according to the sum of its digits.

Return the number of groups that have the largest size.

 

__Example 1:__
```
Input: n = 13
Output: 4
Explanation: There are 9 groups in total, they are grouped according sum of its digits of numbers from 1 to 13:
[1,10], [2,11], [3,12], [4,13], [5], [6], [7], [8], [9].
There are 4 groups with largest size.
```
__Example 2:__
```
Input: n = 2
Output: 2
Explanation: There are 2 groups [1], [2] of size 1.
```
 

__Constraints:__
```
1 <= n <= 104
```
#### EXPLANATION:

easy的题目, 按照题意去模拟出来即可.

#### SOLUTION:
```swift
class Solution {
    func countLargestGroup(_ n: Int) -> Int {
        var dic: [Int:Int] = [:]
        for i in 1...n {
            var tmp = 0
            for ch in String(i) {
                tmp += Int(String(ch))!
            }
            dic[tmp] = dic[tmp,default: 0] + 1
        }
        var result = 0
        var maxVal: Int = 0
        for d in dic {
            maxVal = max(maxVal, d.value)
        }
        for d in dic {
            if d.value == maxVal {
                result += 1
            }
        }
        return result
    }
}
```
