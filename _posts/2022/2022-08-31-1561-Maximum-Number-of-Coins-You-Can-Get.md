---
layout: post
title: 1561. Maximum Number of Coins You Can Get
categories: [leetcode]
---
#### QUESTION:
There are 3n piles of coins of varying size, you and your friends will take piles of coins as follows:

In each step, you will choose any 3 piles of coins (not necessarily consecutive).
Of your choice, Alice will pick the pile with the maximum number of coins.
You will pick the next pile with the maximum number of coins.
Your friend Bob will pick the last pile.
Repeat until there are no more piles of coins.
Given an array of integers piles where piles[i] is the number of coins in the ith pile.

Return the maximum number of coins that you can have.

 

__Example 1:__
```
Input: piles = [2,4,1,2,7,8]
Output: 9
Explanation: Choose the triplet (2, 7, 8), Alice Pick the pile with 8 coins, you the pile with 7 coins and Bob the last one.
Choose the triplet (1, 2, 4), Alice Pick the pile with 4 coins, you the pile with 2 coins and Bob the last one.
The maximum number of coins which you can have are: 7 + 2 = 9.
On the other hand if we choose this arrangement (1, 2, 8), (2, 4, 7) you only get 2 + 4 = 6 coins which is not optimal.
```
__Example 2:__
```
Input: piles = [2,4,5]
Output: 4
```
__Example 3:__
```
Input: piles = [9,8,7,6,5,1,2,3,4]
Output: 18
```
 

__Constraints:__
```
3 <= piles.length <= 105
piles.length % 3 == 0
1 <= piles[i] <= 104
```
#### EXPLANATION:

贪心算法, 每次都取倒数第二大的熟即可. 那么只要排序一下, 按顺序取出即可.

#### SOLUTION:
```swift
class Solution {
    func maxCoins(_ piles: [Int]) -> Int {
        var piles:[Int] = piles.sorted()
        var count:Int = piles.count / 3
        var result:Int = 0
        for index in 1...count {
            result += piles[piles.count - index * 2]
        }
        return result
    }
}
```
