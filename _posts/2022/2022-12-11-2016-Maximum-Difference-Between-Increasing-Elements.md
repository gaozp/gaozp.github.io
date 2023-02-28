---
layout: post
title: 2016. Maximum Difference Between Increasing Elements
categories: [leetcode]
---
#### QUESTION:
Given a 0-indexed integer array nums of size n, find the maximum difference between nums[i] and nums[j] (i.e., nums[j] - nums[i]), such that 0 <= i < j < n and nums[i] < nums[j].

Return the maximum difference. If no such i and j exists, return -1.

 

__Example 1:__
```
Input: nums = [7,1,5,4]
Output: 4
Explanation:
The maximum difference occurs with i = 1 and j = 2, nums[j] - nums[i] = 5 - 1 = 4.
Note that with i = 1 and j = 0, the difference nums[j] - nums[i] = 7 - 1 = 6, but i > j, so it is not valid.
```
__Example 2:__
```
Input: nums = [9,4,3,2]
Output: -1
Explanation:
There is no i and j such that i < j and nums[i] < nums[j].
```
__Example 3:__
```
Input: nums = [1,5,2,10]
Output: 9
Explanation:
The maximum difference occurs with i = 0 and j = 3, nums[j] - nums[i] = 10 - 1 = 9.
```
 

__Constraints:__
```
n == nums.length
2 <= n <= 1000
1 <= nums[i] <= 109
```
#### EXPLANATION:

两个for循环, 匹配好边缘情况即可.

#### SOLUTION:
```swift
class Solution {
    func maximumDifference(_ nums: [Int]) -> Int {
        var result = -1
        for indexI in 0..<nums.count-1 {
            for indexJ in indexI+1..<nums.count {
                if (nums[indexI] < nums[indexJ]) {
                    result = max(result, nums[indexJ]-nums[indexI])
                }
            }
        }
        return result
    }
}
```
