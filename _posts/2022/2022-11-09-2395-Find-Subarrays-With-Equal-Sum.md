---
layout: post
title: 2395. Find Subarrays With Equal Sum
categories: [leetcode]
---
#### QUESTION:
Given a 0-indexed integer array nums, determine whether there exist two subarrays of length 2 with equal sum. Note that the two subarrays must begin at different indices.

Return true if these subarrays exist, and false otherwise.

A subarray is a contiguous non-empty sequence of elements within an array.

 

__Example 1:__
```
Input: nums = [4,2,4]
Output: true
Explanation: The subarrays with elements [4,2] and [2,4] have the same sum of 6.
```
__Example 2:__
```
Input: nums = [1,2,3,4,5]
Output: false
Explanation: No two subarrays of size 2 have the same sum.
```
__Example 3:__
```
Input: nums = [0,0,0]
Output: true
Explanation: The subarrays [nums[0],nums[1]] and [nums[1],nums[2]] have the same sum of 0. 
Note that even though the subarrays have the same content, the two subarrays are considered different because they are in different positions in the original array.
```
 

__Constraints:__
```
2 <= nums.length <= 1000
-109 <= nums[i] <= 109
```
#### EXPLANATION:

用一个数组来标记已经有了的sum值, 一个for循环来记录相邻两数的sum值即可.

#### SOLUTION:
```swift
class Solution {
    func findSubarrays(_ nums: [Int]) -> Bool {
        var resultArr:[Int] = []
        for indexI in 0..<nums.count - 1 {
            var tmpSum = nums[indexI] + nums[indexI + 1]
            if (resultArr.contains(tmpSum)) {
                return true
            }
            resultArr.append(tmpSum)
        }
        return false
    }
}
```
