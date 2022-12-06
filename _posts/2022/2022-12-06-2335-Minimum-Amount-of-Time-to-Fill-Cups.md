---
layout: post
title: 2335. Minimum Amount of Time to Fill Cups
categories: [leetcode]
---
#### QUESTION:
You have a water dispenser that can dispense cold, warm, and hot water. Every second, you can either fill up 2 cups with different types of water, or 1 cup of any type of water.

You are given a 0-indexed integer array amount of length 3 where amount[0], amount[1], and amount[2] denote the number of cold, warm, and hot water cups you need to fill respectively. Return the minimum number of seconds needed to fill up all the cups.

 

__Example 1:__
```
Input: amount = [1,4,2]
Output: 4
Explanation: One way to fill up the cups is:
Second 1: Fill up a cold cup and a warm cup.
Second 2: Fill up a warm cup and a hot cup.
Second 3: Fill up a warm cup and a hot cup.
Second 4: Fill up a warm cup.
It can be proven that 4 is the minimum number of seconds needed.
```
__Example 2:__
```
Input: amount = [5,4,4]
Output: 7
Explanation: One way to fill up the cups is:
Second 1: Fill up a cold cup, and a hot cup.
Second 2: Fill up a cold cup, and a warm cup.
Second 3: Fill up a cold cup, and a warm cup.
Second 4: Fill up a warm cup, and a hot cup.
Second 5: Fill up a cold cup, and a hot cup.
Second 6: Fill up a cold cup, and a warm cup.
Second 7: Fill up a hot cup.
```
__Example 3:__
```
Input: amount = [5,0,0]
Output: 5
Explanation: Every second, we fill up a cold cup.
```
 

__Constraints:__
```
amount.length == 3
0 <= amount[i] <= 100
```
#### EXPLANATION:

模拟出这个过程即可. 当然也可以找到规律. 首先排序一下, 如果a[2] > a[0]+a[1] 那么就明显需要a[2]次, 如果a[2] < a[0]+a[1] 那么最大的fill了之后, 就需要另外两个一起fill, 也就是a[0]+a[1]+1/2的.所以也就能得到 a[2] + (max(a[0]+a[1]-a[2])+1)/2 

#### SOLUTION:
```swift
class Solution {
    func fillCups(_ amount: [Int]) -> Int {
        var amount = amount
        var result = 0
        while amount.reduce(0, +) != 0 {
            amount = amount.sorted()
            if amount[1] != 0 {
                amount[1] -= 1
            }
            if amount[2] != 0 {
                amount[2] -= 1
            }
            result += 1
        }
        return result
    }
}
```
