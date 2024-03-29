---
layout: post
title: 1833. Maximum Ice Cream Bars
categories: [leetcode]
---
#### QUESTION:
It is a sweltering summer day, and a boy wants to buy some ice cream bars.

At the store, there are n ice cream bars. You are given an array costs of length n, where costs[i] is the price of the ith ice cream bar in coins. The boy initially has coins coins to spend, and he wants to buy as many ice cream bars as possible. 

Note: The boy can buy the ice cream bars in any order.

Return the maximum number of ice cream bars the boy can buy with coins coins.

You must solve the problem by counting sort.

 

__Example 1:__
```
Input: costs = [1,3,2,4,1], coins = 7
Output: 4
Explanation: The boy can buy ice cream bars at indices 0,1,2,4 for a total price of 1 + 3 + 2 + 1 = 7.
```
__Example 2:__
```
Input: costs = [10,6,8,7,7,8], coins = 5
Output: 0
Explanation: The boy cannot afford any of the ice cream bars.
```
__Example 3:__
```
Input: costs = [1,6,3,1,2,5], coins = 20
Output: 6
Explanation: The boy can buy all the ice cream bars for a total price of 1 + 6 + 3 + 1 + 2 + 5 = 18.
```
 

__Constraints:__
```
costs.length == n
1 <= n <= 105
1 <= costs[i] <= 105
1 <= coins <= 108
```
#### EXPLANATION:

看完题意就知道使用贪心算法, 排序后加就可以了.

#### SOLUTION:
```swift
class Solution {
    func maxIceCream(_ costs: [Int], _ coins: Int) -> Int {
        var sorted = costs.sorted()
        var tmp = 0
        var result = 0
        for sort in sorted {
            tmp += sort
            if tmp <= coins {
                result += 1
            } else {
                break
            }
        }
        return result
    }
}
```
