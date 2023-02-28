---
layout: post
title: 1869. Longer Contiguous Segments of Ones than Zeros
categories: [leetcode]
---
#### QUESTION:
Given a binary string s, return true if the longest contiguous segment of 1's is strictly longer than the longest contiguous segment of 0's in s, or return false otherwise.

For example, in s = "110100010" the longest continuous segment of 1s has length 2, and the longest continuous segment of 0s has length 3.
Note that if there are no 0's, then the longest continuous segment of 0's is considered to have a length 0. The same applies if there is no 1's.

 

__Example 1:__
```
Input: s = "1101"
Output: true
Explanation:
The longest contiguous segment of 1s has length 2: "1101"
The longest contiguous segment of 0s has length 1: "1101"
The segment of 1s is longer, so return true.
```
__Example 2:__
```
Input: s = "111000"
Output: false
Explanation:
The longest contiguous segment of 1s has length 3: "111000"
The longest contiguous segment of 0s has length 3: "111000"
The segment of 1s is not longer, so return false.
```
__Example 3:__
```
Input: s = "110100010"
Output: false
Explanation:
The longest contiguous segment of 1s has length 2: "110100010"
The longest contiguous segment of 0s has length 3: "110100010"
The segment of 1s is not longer, so return false.
```
 

__Constraints:__
```
1 <= s.length <= 100
s[i] is either '0' or '1'.
```
#### EXPLANATION:

比较简单, for循环之后判断, 如果是0 那么久把tmp0加一, tmp1置为0 , 否则相反. 每次进行判断max, 这样就可以得到最长的0和1了. 返回结果即可.

#### SOLUTION:
```swift
class Solution {
    func checkZeroOnes(_ s: String) -> Bool {
        var maxZero = 0
        var maxOne = 0
        var tmpZero = 0
        var tmpOne = 0
        for ch in s {
            if ch == "0" {
                tmpOne = 0
                tmpZero += 1
            } else {
                tmpZero = 0
                tmpOne += 1
            }
            maxOne = max(tmpOne, maxOne)
            maxZero = max(tmpZero, maxZero)
        }
        return maxOne > maxZero
    }
}
```
