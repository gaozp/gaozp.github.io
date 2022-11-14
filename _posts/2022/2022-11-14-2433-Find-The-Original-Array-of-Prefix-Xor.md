---
layout: post
title: 2433. Find The Original Array of Prefix Xor
categories: [leetcode]
---
#### QUESTION:
You are given an integer array pref of size n. Find and return the array arr of size n that satisfies:

pref[i] = arr[0] ^ arr[1] ^ ... ^ arr[i].
Note that ^ denotes the bitwise-xor operation.

It can be proven that the answer is unique.

 

__Example 1:__
```
Input: pref = [5,2,0,3,1]
Output: [5,7,2,3,2]
Explanation: From the array [5,7,2,3,2] we have the following:
- pref[0] = 5.
- pref[1] = 5 ^ 7 = 2.
- pref[2] = 5 ^ 7 ^ 2 = 0.
- pref[3] = 5 ^ 7 ^ 2 ^ 3 = 3.
- pref[4] = 5 ^ 7 ^ 2 ^ 3 ^ 2 = 1.
```
__Example 2:__
```
Input: pref = [13]
Output: [13]
Explanation: We have pref[0] = arr[0] = 13.
```
 

__Constraints:__
```
1 <= pref.length <= 105
0 <= pref[i] <= 106
```
#### EXPLANATION:

这道题目只要知道 a ^ b = c 那么 b ^ c = a 这个等式的存在即可.

#### SOLUTION:
```swift
class Solution {
    func findArray(_ pref: [Int]) -> [Int] {
        var result:[Int] = [pref[0]]
        for index in 1..<pref.count {
            result.append(pref[index - 1] ^ pref[index])
        }
        return result
    }
}
```
