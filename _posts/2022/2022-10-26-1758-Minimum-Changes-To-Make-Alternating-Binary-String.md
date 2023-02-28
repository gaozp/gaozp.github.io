---
layout: post
title: 1758. Minimum Changes To Make Alternating Binary String
categories: [leetcode]
---
#### QUESTION:
You are given a string s consisting only of the characters '0' and '1'. In one operation, you can change any '0' to '1' or vice versa.

The string is called alternating if no two adjacent characters are equal. For example, the string "010" is alternating, while the string "0100" is not.

Return the minimum number of operations needed to make s alternating.

 

__Example 1:__
```
Input: s = "0100"
Output: 1
Explanation: If you change the last character to '1', s will be "0101", which is alternating.
```
__Example 2:__
```
Input: s = "10"
Output: 0
Explanation: s is already alternating.
```
__Example 3:__
```
Input: s = "1111"
Output: 2
Explanation: You need two operations to reach "0101" or "1010".
```
 

__Constraints:__
```
1 <= s.length <= 104
s[i] is either '0' or '1'.
```
#### EXPLANATION:

这种只有两种情况, 一种是0开头, 一种是1开头, 只要把这两种情况算出来再计算最后的结果即可. 

#### SOLUTION:
```swift
class Solution {
    func minOperations(_ s: String) -> Int {
        var tmp0 = true
        var tmp1 = true
        var result0 = 0
        var result1 = 0
        for ch in s {
            if (tmp0) { // 当前位置应该为0
                if (ch == "0") {
                    
                } else {
                    result0 += 1
                }
            } else {
                if (ch == "0") {
                    result0 += 1
                } else {
                    
                }
            }
            if (tmp1) { // 当前位置应该为1
                if (ch == "1") {
                    
                } else {
                    result1 += 1
                }
            } else {
                if (ch == "1") {
                    result1 += 1
                } else {
                    
                }
            }
            tmp0 = !tmp0
            tmp1 = !tmp1
        }
        return min(result0, result1)
    }
}
```
