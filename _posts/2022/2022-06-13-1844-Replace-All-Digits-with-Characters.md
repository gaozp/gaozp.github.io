---
layout: post
title: 1844. Replace All Digits with Characters
categories: [leetcode]
---
#### QUESTION:
You are given a 0-indexed string s that has lowercase English letters in its even indices and digits in its odd indices.

There is a function shift(c, x), where c is a character and x is a digit, that returns the xth character after c.

For example, shift('a', 5) = 'f' and shift('x', 0) = 'x'.
For every odd index i, you want to replace the digit s[i] with shift(s[i-1], s[i]).

Return s after replacing all digits. It is guaranteed that shift(s[i-1], s[i]) will never exceed 'z'.

 

__Example 1:__
```
Input: s = "a1c1e1"
Output: "abcdef"
Explanation: The digits are replaced as follows:
- s[1] -> shift('a',1) = 'b'
- s[3] -> shift('c',1) = 'd'
- s[5] -> shift('e',1) = 'f'
```
__Example 2:__
```
Input: s = "a1b2c3d4e"
Output: "abbdcfdhe"
Explanation: The digits are replaced as follows:
- s[1] -> shift('a',1) = 'b'
- s[3] -> shift('b',2) = 'd'
- s[5] -> shift('c',3) = 'f'
- s[7] -> shift('d',4) = 'h'
 ```

__Constraints:__
```
1 <= s.length <= 100
s consists only of lowercase English letters and digits.
shift(s[i-1], s[i]) <= 'z' for all odd indices i.
```
#### EXPLANATION:

这道题目虽然是easy题, 但是里面还是有一点陷阱的. 比如题目中就没有明确说s是一个偶数的string. 所以在只能选择单个进行replace, 否则直接拿的话会有数组角标的问题. 需要注意.

#### SOLUTION:
```swift
class Solution {
    func replaceDigits(_ s: String) -> String {
        let c = s.count
        var s = Array(s)
        for i in stride(from: 1, to: c, by: 2) {
            s[i] = Character(UnicodeScalar((s[i - 1].asciiValue ?? 97) + (s[i].asciiValue ?? 48) - 48))
        }
        return String(s)
    }
}
```
