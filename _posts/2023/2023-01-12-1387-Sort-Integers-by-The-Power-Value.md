---
layout: post
title: 1387. Sort Integers by The Power Value
categories: [leetcode]
---
#### QUESTION:
The power of an integer x is defined as the number of steps needed to transform x into 1 using the following steps:

if x is even then x = x / 2
if x is odd then x = 3 * x + 1
For example, the power of x = 3 is 7 because 3 needs 7 steps to become 1 (3 --> 10 --> 5 --> 16 --> 8 --> 4 --> 2 --> 1).

Given three integers lo, hi and k. The task is to sort all integers in the interval [lo, hi] by the power value in ascending order, if two or more integers have the same power value sort them by ascending order.

Return the kth integer in the range [lo, hi] sorted by the power value.

Notice that for any integer x (lo <= x <= hi) it is guaranteed that x will transform into 1 using these steps and that the power of x is will fit in a 32-bit signed integer.

 

__Example 1:__
```
Input: lo = 12, hi = 15, k = 2
Output: 13
Explanation: The power of 12 is 9 (12 --> 6 --> 3 --> 10 --> 5 --> 16 --> 8 --> 4 --> 2 --> 1)
The power of 13 is 9
The power of 14 is 17
The power of 15 is 17
The interval sorted by the power value [12,13,14,15]. For k = 2 answer is the second element which is 13.
Notice that 12 and 13 have the same power value and we sorted them in ascending order. Same for 14 and 15.
```
__Example 2:__
```
Input: lo = 7, hi = 11, k = 4
Output: 7
Explanation: The power array corresponding to the interval [7, 8, 9, 10, 11] is [16, 3, 19, 6, 14].
The interval sorted by power is [8, 10, 11, 7, 9].
The fourth number in the sorted array is 7.
```
 

__Constraints:__
```
1 <= lo <= hi <= 1000
1 <= k <= hi - lo + 1
```
#### EXPLANATION:

因为每个数字都要计算power, 所以我们可以把这里单独抽出来一个方法. 剩下的就是计算出每个数字的power, 然后排序, 这里也没有难的. 这样就可以.

#### SOLUTION:
```swift
class Solution {
    func getKth(_ lo: Int, _ hi: Int, _ k: Int) -> Int {
        var dic:Dictionary<Int,Int> = [:]
        for num in lo...hi {
            dic[num] = getKthHelper(num)
        }
        let sorted = dic.sorted { a, b in
            if a.value == b.value {
                return a.key < b.key
            } else {
                return a.value < b.value
            }
        }
        return sorted[k-1].key
    }

    func getKthHelper(_ num: Int) -> Int {
        var num = num
        var result = 0
        while num != 1 {
            if num % 2 == 1 {
                num = num * 3 + 1
            } else {
                num = num / 2
            }
            result += 1
        }
        return result
    }
}
```
