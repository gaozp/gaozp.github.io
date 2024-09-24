---
layout: post
title: 2769. Find the Maximum Achievable Number
categories: [leetcode]
---
#### QUESTION:
Given two integers, num and t. A number is achievable if it can become equal to num after applying the following operation:

Increase or decrease the number by 1, and simultaneously increase or decrease num by 1.
Return the maximum achievable number after applying the operation at most t times.

 

__Example 1:__
```
Input: num = 4, t = 1

Output: 6
```
__Explanation:__

Apply the following operation once to make the maximum achievable number equal to num:

Decrease the maximum achievable number by 1, and increase num by 1.
__Example 2:__
```
Input: num = 3, t = 2

Output: 7
```

__Explanation:__

Apply the following operation twice to make the maximum achievable number equal to num:

Decrease the maximum achievable number by 1, and increase num by 1.
 

__Constraints:__
```
1 <= num, t <= 50
```
#### EXPLANATION:

比较简单, 假设maxachievable是x, 那么对x减去t次, num加上t次. 就是最后的值. 也就是 x - t = num + t . 得到 x = num + t + t. 

#### SOLUTION:
```swift
class Solution {
    func theMaximumAchievableX(_ num: Int, _ t: Int) -> Int {
        return num + (2 * t)
    }
}
```
