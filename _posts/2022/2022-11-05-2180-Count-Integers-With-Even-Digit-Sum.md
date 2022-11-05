---
layout: post
title: 2180. Count Integers With Even Digit Sum
categories: [leetcode]
---
#### QUESTION:
Given a positive integer num, return the number of positive integers less than or equal to num whose digit sums are even.

The digit sum of a positive integer is the sum of all its digits.

 

__Example 1:__
```
Input: num = 4
Output: 2
Explanation:
The only integers less than or equal to 4 whose digit sums are even are 2 and 4.    
```
__Example 2:__
```
Input: num = 30
Output: 14
Explanation:
The 14 integers less than or equal to 30 whose digit sums are even are
2, 4, 6, 8, 11, 13, 15, 17, 19, 20, 22, 24, 26, and 28.
```
 

__Constraints:__
```
1 <= num <= 1000
```
#### EXPLANATION:

for循环去挨个获取, 将每个数字进行string, 用tmp进行相加. 然后将tmp来判断是否是偶数. 返回结果.

#### SOLUTION:
```swift
class Solution {
    func countEven(_ num: Int) -> Int {
        var result = 0
        for i in 1...num {
            var tmp = 0
            for s in String(i) {
                tmp += Int(String(s))!
            }
            if (tmp % 2 == 0) {
                result += 1
            }
        }
        return result
    }
}
```
