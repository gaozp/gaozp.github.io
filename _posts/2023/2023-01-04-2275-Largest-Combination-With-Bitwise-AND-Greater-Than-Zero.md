---
layout: post
title: 2275. Largest Combination With Bitwise AND Greater Than Zero
categories: [leetcode]
---
#### QUESTION:
The bitwise AND of an array nums is the bitwise AND of all integers in nums.

For example, for nums = [1, 5, 3], the bitwise AND is equal to 1 & 5 & 3 = 1.
Also, for nums = [7], the bitwise AND is 7.
You are given an array of positive integers candidates. Evaluate the bitwise AND of every combination of numbers of candidates. Each number in candidates may only be used once in each combination.

Return the size of the largest combination of candidates with a bitwise AND greater than 0.

 

__Example 1:__
```
Input: candidates = [16,17,71,62,12,24,14]
Output: 4
Explanation: The combination [16,17,62,24] has a bitwise AND of 16 & 17 & 62 & 24 = 16 > 0.
The size of the combination is 4.
It can be shown that no combination with a size greater than 4 has a bitwise AND greater than 0.
Note that more than one combination may have the largest size.
For example, the combination [62,12,24,14] has a bitwise AND of 62 & 12 & 24 & 14 = 8 > 0.
```
__Example 2:__
```
Input: candidates = [8,8]
Output: 2
Explanation: The largest combination [8,8] has a bitwise AND of 8 & 8 = 8 > 0.
The size of the combination is 2, so we return 2.
```
 

__Constraints:__
```
1 <= candidates.length <= 105
1 <= candidates[i] <= 107
```
#### EXPLANATION:

思路说出来就很简单, 要有几个数&下来大于0, 那么说明他们其中某一位都是1, 那么就变成了求这些数中, 那个位置的1最多. 这样想起来就简单了. 那么用一个1去进行&, 如果大于0, 说明这个位置有. 然后算出这个位置的总数, 再左移一位. 直到最大值结束. 

#### SOLUTION:
```swift
class Solution {
    func largestCombination(_ candidates: [Int]) -> Int {
        var result = 0
        var i = 1
        while i < 10000000 {
            var cur = 0
            for can in candidates {
                if can & i > 0 {
                    cur += 1
                }
            }
            result = max(result, cur)
            i <<= 1
        }
        return result
    }
}
```
