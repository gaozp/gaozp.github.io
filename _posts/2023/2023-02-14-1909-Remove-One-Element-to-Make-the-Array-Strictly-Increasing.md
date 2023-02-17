---
layout: post
title: 1909. Remove One Element to Make the Array Strictly Increasing
categories: [leetcode]
---
#### QUESTION:
Given a 0-indexed integer array nums, return true if it can be made strictly increasing after removing exactly one element, or false otherwise. If the array is already strictly increasing, return true.

The array nums is strictly increasing if nums[i - 1] < nums[i] for each index (1 <= i < nums.length).

 

__Example 1:__
```
Input: nums = [1,2,10,5,7]
Output: true
Explanation: By removing 10 at index 2 from nums, it becomes [1,2,5,7].
[1,2,5,7] is strictly increasing, so return true.
```
__Example 2:__
```
Input: nums = [2,3,1,2]
Output: false
Explanation:
[3,1,2] is the result of removing the element at index 0.
[2,1,2] is the result of removing the element at index 1.
[2,3,2] is the result of removing the element at index 2.
[2,3,1] is the result of removing the element at index 3.
No resulting array is strictly increasing, so return false.
```
__Example 3:__
```
Input: nums = [1,1,1]
Output: false
Explanation: The result of removing any element is [1,1].
[1,1] is not strictly increasing, so return false.
```
 

__Constraints:__
```
2 <= nums.length <= 1000
1 <= nums[i] <= 1000
```
#### EXPLANATION:

模拟这个去除的过程即可

#### SOLUTION:
```swift
class Solution {
    func canBeIncreasing(_ nums: [Int]) -> Bool {
        if nums.count == 2 {
            return true
        }
        var nums = nums
        for (index, num) in nums.enumerated() {
            nums.remove(at: index)
            if canBeIncreasingHelper(&nums) {
                return true
            }
            nums.insert(num, at: index)
        }
        return false
    }

    func canBeIncreasingHelper(_ nums: inout [Int]) -> Bool {
        for index in 1...nums.count - 1  {
            if nums[index] <= nums[index-1] {
                return false
            }
        }
        return true
    }
}
```
