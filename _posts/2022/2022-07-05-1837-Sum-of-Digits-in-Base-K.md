---
layout: post
title: 1837. Sum of Digits in Base K
categories: [leetcode]
---
#### QUESTION:
Given an integer n (in base 10) and a base k, return the sum of the digits of n after converting n from base 10 to base k.

After converting, each digit should be interpreted as a base 10 number, and the sum should be returned in base 10.

 

__Example 1:__
```
Input: n = 34, k = 6
Output: 9
Explanation: 34 (base 10) expressed in base 6 is 54. 5 + 4 = 9.
```
__Example 2:__
```
Input: n = 10, k = 10
Output: 1
Explanation: n is already in base 10. 1 + 0 = 1.
```
 

__Constraints:__
```
1 <= n <= 100
2 <= k <= 10
```
#### EXPLANATION:

easy的题目， 用自带的进制转换后一个for循环就可以计算出结果。

#### SOLUTION:
```swift
class Solution {
    func sumBase(_ n: Int, _ k: Int) -> Int {
        var result:String = String(n, radix:k)
        var sum:Int = 0
        result.forEach { Character in
            sum += Int(String(Character))!
        }
        return sum
    }
}
```
