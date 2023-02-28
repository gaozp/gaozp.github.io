---
layout: post
title: 2357. Make Array Zero by Subtracting Equal Amounts
categories: [leetcode]
---
#### QUESTION:
You are given a non-negative integer array nums. In one operation, you must:

Choose a positive integer x such that x is less than or equal to the smallest non-zero element in nums.
Subtract x from every positive element in nums.
Return the minimum number of operations to make every element in nums equal to 0.

 

__Example 1:__
```
Input: nums = [1,5,0,3,5]
Output: 3
Explanation:
In the first operation, choose x = 1. Now, nums = [0,4,0,2,4].
In the second operation, choose x = 2. Now, nums = [0,2,0,0,2].
In the third operation, choose x = 2. Now, nums = [0,0,0,0,0].
```
__Example 2:__
```
Input: nums = [0]
Output: 0
Explanation: Each element in nums is already 0 so no operations are needed.
```
 

__Constraints:__
```
1 <= nums.length <= 100
0 <= nums[i] <= 100
```
#### EXPLANATION:

easy的题目, 就不多说了, 直接按着题目的思路写出来即可.

#### SOLUTION:
```swift
class Solution {
    func minimumOperations(_ nums: [Int]) -> Int {
        var result = 0
        var newNums = nums.sorted()
        while  newNums[newNums.count-1] != 0 {
            var index:Int = 0
            while newNums[index] == 0 {
                index += 1
            }
            var tmp = newNums[index]
            for i in index...newNums.count-1 {
                newNums[i] -= tmp
            }
            result += 1
            newNums = newNums.sorted()
        }
        return result
    }
}
```
