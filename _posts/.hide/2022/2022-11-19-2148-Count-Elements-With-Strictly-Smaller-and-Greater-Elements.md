---
layout: post
title: 2148. Count Elements With Strictly Smaller and Greater Elements
categories: [leetcode]
---
#### QUESTION:
Given an integer array nums, return the number of elements that have both a strictly smaller and a strictly greater element appear in nums.

 

__Example 1:__
```
Input: nums = [11,7,2,15]
Output: 2
Explanation: The element 7 has the element 2 strictly smaller than it and the element 11 strictly greater than it.
Element 11 has element 7 strictly smaller than it and element 15 strictly greater than it.
In total there are 2 elements having both a strictly smaller and a strictly greater element appear in nums.
```
__Example 2:__
```
Input: nums = [-3,3,3,90]
Output: 2
Explanation: The element 3 has the element -3 strictly smaller than it and the element 90 strictly greater than it.
Since there are two elements with the value 3, in total there are 2 elements having both a strictly smaller and a strictly greater element appear in nums.
```
 

__Constraints:__
```
1 <= nums.length <= 100
-105 <= nums[i] <= 105
```
#### EXPLANATION:

如果当前数据只有2种以下, 那么久返回0 , 否则就直接减去min的count和max的count即可.

#### SOLUTION:
```swift
class Solution {
    func countElements(_ nums: [Int]) -> Int {
        return Set(nums).count <= 2 ? 0 : nums.count - nums.filter({ a in
            a == nums.min()
        }).count - nums.filter({ b in
            b == nums.max()
        }).count
    }
}
```
