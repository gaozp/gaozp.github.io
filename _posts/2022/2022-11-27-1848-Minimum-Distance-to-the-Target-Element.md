---
layout: post
title: 1848. Minimum Distance to the Target Element
categories: [leetcode]
---
#### QUESTION:
Given an integer array nums (0-indexed) and two integers target and start, find an index i such that nums[i] == target and abs(i - start) is minimized. Note that abs(x) is the absolute value of x.

Return abs(i - start).

It is guaranteed that target exists in nums.

 

__Example 1:__
```
Input: nums = [1,2,3,4,5], target = 5, start = 3
Output: 1
Explanation: nums[4] = 5 is the only value equal to target, so the answer is abs(4 - 3) = 1.
```
__Example 2:__
```
Input: nums = [1], target = 1, start = 0
Output: 0
Explanation: nums[0] = 1 is the only value equal to target, so the answer is abs(0 - 0) = 0.
```
__Example 3:__
```
Input: nums = [1,1,1,1,1,1,1,1,1,1], target = 1, start = 0
Output: 0
Explanation: Every value of nums is 1, but nums[0] minimizes abs(i - start), which is abs(0 - 0) = 0.
```
 

__Constraints:__
```
1 <= nums.length <= 1000
1 <= nums[i] <= 104
0 <= start < nums.length
target is in nums.
```
#### EXPLANATION:

只要一个for循环就可以搞定

#### SOLUTION:
```swift
class Solution {
    func getMinDistance(_ nums: [Int], _ target: Int, _ start: Int) -> Int {
        var result = Int.max
        for index in nums.indices {
            if nums[index] == target {
                result = min(result, abs(index - start))
            }
        }
        return result
    }
}
```
