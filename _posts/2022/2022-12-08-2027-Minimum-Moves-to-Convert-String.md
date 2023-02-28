---
layout: post
title: 2027. Minimum Moves to Convert String
categories: [leetcode]
---
#### QUESTION:
You are given a string s consisting of n characters which are either 'X' or 'O'.

A move is defined as selecting three consecutive characters of s and converting them to 'O'. Note that if a move is applied to the character 'O', it will stay the same.

Return the minimum number of moves required so that all the characters of s are converted to 'O'.

 

__Example 1:__
```
Input: s = "XXX"
Output: 1
Explanation: XXX -> OOO
We select all the 3 characters and convert them in one move.
```
__Example 2:__
```
Input: s = "XXOX"
Output: 2
Explanation: XXOX -> OOOX -> OOOO
We select the first 3 characters in the first move, and convert them to 'O'.
Then we select the last 3 characters and convert them so that the final string contains all 'O's.
```
__Example 3:__
```
Input: s = "OOOO"
Output: 0
Explanation: There are no 'X's in s to convert.
```
 

__Constraints:__
```
3 <= s.length <= 1000
s[i] is either 'X' or 'O'.
```
#### EXPLANATION:

根据题意可以得知, 只要碰到一个x, 那么就需要将3个位置都置成O, 那么我们就可以得到, 只要按顺序去比对, 如果是O, 那么就进入下一个, 是X就将当前和后面的两个也置为O, 判断到最后即可.

#### SOLUTION:
```swift
class Solution {
    func minimumMoves(_ s: String) -> Int {
        var arrS = Array(s)
        var result = 0
        for index in arrS.indices {
            if arrS[index] == "X" {
                arrS[index] = "O"
                if index + 1 < arrS.count {
                    arrS[index+1] = "O"
                }
                if index + 2 < arrS.count {
                    arrS[index+2] = "O"
                }
                result += 1
            }
        }
        return result
    }
}
```
