---
layout: post
title: 1742. Maximum Number of Balls in a Box
categories: [leetcode]
---
#### QUESTION:
You are working in a ball factory where you have n balls numbered from lowLimit up to highLimit inclusive (i.e., n == highLimit - lowLimit + 1), and an infinite number of boxes numbered from 1 to infinity.

Your job at this factory is to put each ball in the box with a number equal to the sum of digits of the ball's number. For example, the ball number 321 will be put in the box number 3 + 2 + 1 = 6 and the ball number 10 will be put in the box number 1 + 0 = 1.

Given two integers lowLimit and highLimit, return the number of balls in the box with the most balls.

 

__Example 1:__
```
Input: lowLimit = 1, highLimit = 10
Output: 2
Explanation:
Box Number:  1 2 3 4 5 6 7 8 9 10 11 ...
Ball Count:  2 1 1 1 1 1 1 1 1 0  0  ...
Box 1 has the most number of balls with 2 balls.
```
__Example 2:__
```
Input: lowLimit = 5, highLimit = 15
Output: 2
Explanation:
Box Number:  1 2 3 4 5 6 7 8 9 10 11 ...
Ball Count:  1 1 1 1 2 2 1 1 1 0  0  ...
Boxes 5 and 6 have the most number of balls with 2 balls in each.
```
__Example 3:__
```
Input: lowLimit = 19, highLimit = 28
Output: 2
Explanation:
Box Number:  1 2 3 4 5 6 7 8 9 10 11 12 ...
Ball Count:  0 1 1 1 1 1 1 1 1 2  0  0  ...
Box 10 has the most number of balls with 2 balls.
```
 

__Constraints:__
```
1 <= lowLimit <= highLimit <= 10^5
```
#### EXPLANATION:

easy的题目, 按照题目要求模拟出来即可.

#### SOLUTION:
```swift
class Solution {
    func countBalls(_ lowLimit: Int, _ highLimit: Int) -> Int {
        var arr:[Int] = Array(repeating: 0, count: highLimit+1)
        for item in stride(from: lowLimit, to: highLimit+1, by: 1) {
            var tmp:[Character] = Array(String(item))
            var sum:Int = 0
            for it in tmp {
                sum += Int(String(it))!
            }
            arr[sum] += 1
        }
        var result:Int = 0
        for a in arr {
            result = result > a ? result : a
        }
        return result
    }
}

```
