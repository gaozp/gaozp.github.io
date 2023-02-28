---
layout: post
title: 2574. Left and Right Sum Differences
categories: [leetcode]
---
#### QUESTION:
Given a 0-indexed integer array nums, find a 0-indexed integer array answer where:

answer.length == nums.length.
answer[i] = |leftSum[i] - rightSum[i]|.
Where:

leftSum[i] is the sum of elements to the left of the index i in the array nums. If there is no such element, leftSum[i] = 0.
rightSum[i] is the sum of elements to the right of the index i in the array nums. If there is no such element, rightSum[i] = 0.
Return the array answer.

 

__Example 1:__
```
Input: nums = [10,4,8,3]
Output: [15,1,11,22]
Explanation: The array leftSum is [0,10,14,22] and the array rightSum is [15,11,3,0].
The array answer is [|0 - 15|,|10 - 11|,|14 - 3|,|22 - 0|] = [15,1,11,22].
```
__Example 2:__
```
Input: nums = [1]
Output: [0]
Explanation: The array leftSum is [0] and the array rightSum is [0].
The array answer is [|0 - 0|] = [0].
```
 

__Constraints:__
```
1 <= nums.length <= 1000
1 <= nums[i] <= 105
```
#### EXPLANATION:

easy的题目, 左边的和用tmp来每次叠加. 右边的和则用sum每次去减当前的数即可.

#### SOLUTION:
```swift
class Solution {
    func leftRigthDifference(_ nums: [Int]) -> [Int] {
        var result: [Int] = []
        var sum = nums.reduce(0, +)
        var tmp = 0
        for i in 0...nums.count-1 {
            sum -= nums[i]
            result.append(abs(sum - tmp))
            tmp += nums[i]
        }
        return result
    }
}
```
