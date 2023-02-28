---
layout: post
title: 2441. Largest Positive Integer That Exists With Its Negative
categories: [leetcode]
---
#### QUESTION:
Given an integer array nums that does not contain any zeros, find the largest positive integer k such that -k also exists in the array.

Return the positive integer k. If there is no such integer, return -1.

 

__Example 1:__
```
Input: nums = [-1,2,-3,3]
Output: 3
Explanation: 3 is the only valid k we can find in the array.
```
__Example 2:__
```
Input: nums = [-1,10,6,7,-7,1]
Output: 7
Explanation: Both 1 and 7 have their corresponding negative values in the array. 7 has a larger value.
```
__Example 3:__
```
Input: nums = [-10,8,6,7,-2,-3]
Output: -1
Explanation: There is no a single valid k, we return -1.
```
 

__Constraints:__
```
1 <= nums.length <= 1000
-1000 <= nums[i] <= 1000
nums[i] != 0
```
#### EXPLANATION:

排序后进行筛选, 如果0-num也就是相反的数也在, 那么就可以返回. 

#### SOLUTION:
```swift
class Solution {
    func findMaxK(_ nums: [Int]) -> Int {
        var nums = nums.sorted()
        for num in nums {
            if (nums.contains(0-num)) {
                return abs(0-num)
            }
        }
        return -1
    }
}
```
