---
layout: post
title: 1952. Three Divisors
categories: [leetcode]
---
#### QUESTION:
Given an integer n, return true if n has exactly three positive divisors. Otherwise, return false.

An integer m is a divisor of n if there exists an integer k such that n = k * m.

 

__Example 1:__
```
Input: n = 2
Output: false
Explantion: 2 has only two divisors: 1 and 2.
```
__Example 2:__
```
Input: n = 4
Output: true
Explantion: 4 has three divisors: 1, 2, and 4.
```
 

__Constraints:__
```
1 <= n <= 104
```
#### EXPLANATION:

其实只要for循环, 然后只要循环到n/2即可, 因为后面都是重复的了. 用set来去重即可.

#### SOLUTION:
```swift
class Solution {
    func isThree(_ n: Int) -> Bool {
        if n <= 3 {
            return false
        }
        var result: Set<Int> = Set()
        for i in 1...n/2 {
            if n % i == 0 {
                result.insert(i)
                result.insert(n/i)
            }
        }
        return result.count == 3
    }
}
```
