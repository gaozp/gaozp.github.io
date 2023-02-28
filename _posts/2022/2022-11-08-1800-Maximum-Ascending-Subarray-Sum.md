---
layout: post
title: 1800. Maximum Ascending Subarray Sum
categories: [leetcode]
---
#### QUESTION:
Given an array of positive integers nums, return the maximum possible sum of an ascending subarray in nums.

A subarray is defined as a contiguous sequence of numbers in an array.

A subarray [numsl, numsl+1, ..., numsr-1, numsr] is ascending if for all i where l <= i < r, numsi < numsi+1. Note that a subarray of size 1 is ascending.

 

__Example 1:__
```
Input: nums = [10,20,30,5,10,50]
Output: 65
Explanation: [5,10,50] is the ascending subarray with the maximum sum of 65.
```
__Example 2:__
```
Input: nums = [10,20,30,40,50]
Output: 150
Explanation: [10,20,30,40,50] is the ascending subarray with the maximum sum of 150.
```
__Example 3:__
```
Input: nums = [12,17,15,13,10,11,12]
Output: 33
Explanation: [10,11,12] is the ascending subarray with the maximum sum of 33.
```
 

__Constraints:__
```
1 <= nums.length <= 100
1 <= nums[i] <= 100
```
#### EXPLANATION:

只要判断出当前位置和前一个位置的大小, 如果比前一个位置大, 那么就在tmpresult上加上结果. 如果没有前一个位置大, 就将当前值传给tmpresult. 然后进行result和tmpresult的大小比较即可. 

#### SOLUTION:
```swift
class Solution {
    func maxAscendingSum(_ nums: [Int]) -> Int {
        var result = 0
        var tmpResult = 0
        for index in nums.indices {
            if (index == 0) {
                tmpResult = nums[index]
            } else {
                if (nums[index] > nums[index - 1]) {
                    tmpResult += nums[index]
                } else {
                    tmpResult = nums[index]
                }
            }
            result = max(result, tmpResult)
        }
        return result
    }
}
```
