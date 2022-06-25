---
layout: post
title: 2089. Find Target Indices After Sorting Array
categories: [leetcode]
---
#### QUESTION:
ou are given a 0-indexed integer array nums and a target element target.

A target index is an index i such that nums[i] == target.

Return a list of the target indices of nums after sorting nums in non-decreasing order. If there are no target indices, return an empty list. The returned list must be sorted in increasing order.

 

__Example 1:__
```
Input: nums = [1,2,5,2,3], target = 2
Output: [1,2]
Explanation: After sorting, nums is [1,2,2,3,5].
The indices where nums[i] == 2 are 1 and 2.
```
__Example 2:__
```
Input: nums = [1,2,5,2,3], target = 3
Output: [3]
Explanation: After sorting, nums is [1,2,2,3,5].
The index where nums[i] == 3 is 3.
```
__Example 3:__
```
Input: nums = [1,2,5,2,3], target = 5
Output: [4]
Explanation: After sorting, nums is [1,2,2,3,5].
The index where nums[i] == 5 is 4.
 ```

__Constraints:__
```
1 <= nums.length <= 100
1 <= nums[i], target <= 100
```
#### EXPLANATION:

一道easy的题目, 直接sort之后for循环判断即可.

#### SOLUTION:
```swift
class Solution {
    func targetIndices(_ nums: [Int], _ target: Int) -> [Int] {
        let arr = nums.sorted()
        var result:[Int] = []
        for index in arr.indices {
            if arr[index]==target {
                result.append(index)
            }
        }
        return result
    }
}
```
