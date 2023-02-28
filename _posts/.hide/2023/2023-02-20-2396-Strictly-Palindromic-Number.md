---
layout: post
title: 2396. Strictly Palindromic Number
categories: [leetcode]
---
#### QUESTION:
An integer n is strictly palindromic if, for every base b between 2 and n - 2 (inclusive), the string representation of the integer n in base b is palindromic.

Given an integer n, return true if n is strictly palindromic and false otherwise.

A string is palindromic if it reads the same forward and backward.

 

__Example 1:__
```
Input: n = 9
Output: false
Explanation: In base 2: 9 = 1001 (base 2), which is palindromic.
In base 3: 9 = 100 (base 3), which is not palindromic.
Therefore, 9 is not strictly palindromic so we return false.
Note that in bases 4, 5, 6, and 7, n = 9 is also not palindromic.
```
__Example 2:__
```
Input: n = 4
Output: false
Explanation: We only consider base 2: 4 = 100 (base 2), which is not palindromic.
Therefore, we return false.
```

 

__Constraints:__
```
4 <= n <= 105
```
#### EXPLANATION:

按着模拟出来即可, 但是还有简单的方法, 直接返回false就行. 

#### SOLUTION:
```swift
class Solution {
    func isStrictlyPalindromic(_ n: Int) -> Bool {
        var result = true
        for i in 2...n {
            var tmpA = Array(String(n, radix: i))
            var tmpB = Array(tmpA.reversed())
            if tmpA != tmpB {
                return false
            }
        }
        return result
    }
}
```
