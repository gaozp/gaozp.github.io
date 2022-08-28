---
layout: post
title: 1716. Calculate Money in Leetcode Bank
categories: [leetcode]
---
#### QUESTION:
Hercy wants to save money for his first car. He puts money in the Leetcode bank every day.

He starts by putting in $1 on Monday, the first day. Every day from Tuesday to Sunday, he will put in $1 more than the day before. On every subsequent Monday, he will put in $1 more than the previous Monday.
Given n, return the total amount of money he will have in the Leetcode bank at the end of the nth day.

 

__Example 1:__
```
Input: n = 4
Output: 10
Explanation: After the 4th day, the total is 1 + 2 + 3 + 4 = 10.
```
__Example 2:__
```
Input: n = 10
Output: 37
Explanation: After the 10th day, the total is (1 + 2 + 3 + 4 + 5 + 6 + 7) + (2 + 3 + 4) = 37. Notice that on the 2nd Monday, Hercy only puts in $2.
```
__Example 3:__
```
Input: n = 20
Output: 96
Explanation: After the 20th day, the total is (1 + 2 + 3 + 4 + 5 + 6 + 7) + (2 + 3 + 4 + 5 + 6 + 7 + 8) + (3 + 4 + 5 + 6 + 7 + 8) = 96.
```
 

__Constraints:__
```
1 <= n <= 1000
```
#### EXPLANATION:

主要分成两个思路, 一个就是每周存钱的思路, 需要两个值, 一个就是当前是第几周, 那么就知道了开始值, 另外就是这周有多少天. 也就知道了结束值.   

那么周要怎么计算呢, 就需要看当前天数能不能被 7 整除, 如果能被7整除就是 n/7 周 , 不能被7整除, 就是 n/7 + 1 周. 最后一周需要做特殊处理即可.

#### SOLUTION:
```swift
class Solution {
    func totalMoney(_ n: Int) -> Int {
        var result: Int = 0
        var count = n % 7 == 0 ? n / 7 : n/7+1
        for index in    0..<count {
            if index == n/7 && n%7 != 0 {
                result += totalMoneyHelper(index + 1, n%7)
            } else {
                result += totalMoneyHelper(index + 1, 7)
            }
        }
        return result
    }
    
    func totalMoneyHelper(_ nstart: Int , _ count: Int) -> Int {
        var result: Int = 0
        var nstart = nstart
        for index in 0...count - 1 {
            result += nstart
            nstart += 1
        }
        return result
    }
}
```
