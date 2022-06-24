---
layout: post
title: 1827. Minimum Operations to Make the Array Increasing
categories: [leetcode]
---
#### QUESTION:
You are given an integer array nums (0-indexed). In one operation, you can choose an element of the array and increment it by 1.

For example, if nums = [1,2,3], you can choose to increment nums[1] to make nums = [1,3,3].
Return the minimum number of operations needed to make nums strictly increasing.

An array nums is strictly increasing if nums[i] < nums[i+1] for all 0 <= i < nums.length - 1. An array of length 1 is trivially strictly increasing.

 

__Example 1:__
```
Input: nums = [1,1,1]
Output: 3
Explanation: You can do the following operations:
1) Increment nums[2], so nums becomes [1,1,2].
2) Increment nums[1], so nums becomes [1,2,2].
3) Increment nums[2], so nums becomes [1,2,3].
```
__Example 2:__
```
Input: nums = [1,5,2,4,1]
Output: 14
```
__Example 3:__
```
Input: nums = [8]
Output: 0
```
 

__Constraints:__
```
1 <= nums.length <= 5000
1 <= nums[i] <= 10^4
```
#### EXPLANATION:

一道easy的题目, 直接一个for循环就可以做出来了. 因为每个数只需要比前一个数大1即可. 将结果累加就行.

#### SOLUTION:
```swift
class Solution {
    func minOperations(_ nums: [Int]) -> Int {
        var result:Int = 0
        var arr = nums
        for index in stride(from: 1, to: nums.count, by: 1) {
            if (arr[index] <= arr[index - 1]) {
                result += arr[index-1] - arr[index] + 1
                arr[index] = arr[index-1]+1
            }
        }
        return result
    }
}
```
