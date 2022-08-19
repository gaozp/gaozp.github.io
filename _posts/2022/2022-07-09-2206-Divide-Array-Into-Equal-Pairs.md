---
layout: post
title: 2206. Divide Array Into Equal Pairs
categories: [leetcode]
---
#### QUESTION:
You are given an integer array nums consisting of 2 * n integers.

You need to divide nums into n pairs such that:

Each element belongs to exactly one pair.
The elements present in a pair are equal.
Return true if nums can be divided into n pairs, otherwise return false.

 

__Example 1:__
```
Input: nums = [3,2,3,2,2,2]
Output: true
Explanation: 
There are 6 elements in nums, so they should be divided into 6 / 2 = 3 pairs.
If nums is divided into the pairs (2, 2), (3, 3), and (2, 2), it will satisfy all the conditions.
```
__Example 2:__
```
Input: nums = [1,2,3,4]
Output: false
Explanation: 
There is no way to divide nums into 4 / 2 = 2 pairs such that the pairs satisfy every condition.
```
 

__Constraints:__
```
nums.length == 2 * n
1 <= n <= 500
1 <= nums[i] <= 500
```
#### EXPLANATION:

easy的题目, 也就不多说了, 一个for循环.

#### SOLUTION:
```swift
class Solution {
    func divideArray(_ nums: [Int]) -> Bool {
        var arr:[Int] = nums.sorted()
        for index in stride(from: 0, to: arr.count, by: 2) {
            if arr[index] != arr[index+1] {
                return false
            }
        }
        return true
    }
}
```
