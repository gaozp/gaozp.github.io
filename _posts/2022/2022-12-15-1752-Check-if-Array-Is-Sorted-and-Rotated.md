---
layout: post
title: 1752. Check if Array Is Sorted and Rotated
categories: [leetcode]
---
#### QUESTION:
Given an array nums, return true if the array was originally sorted in non-decreasing order, then rotated some number of positions (including zero). Otherwise, return false.

There may be duplicates in the original array.

Note: An array A rotated by x positions results in an array B of the same length such that A[i] == B[(i+x) % A.length], where % is the modulo operation.

 

__Example 1:__
```
Input: nums = [3,4,5,1,2]
Output: true
Explanation: [1,2,3,4,5] is the original sorted array.
You can rotate the array by x = 3 positions to begin on the the element of value 3: [3,4,5,1,2].
```
__Example 2:__
```
Input: nums = [2,1,3,4]
Output: false
Explanation: There is no sorted array once rotated that can make nums.
```
__Example 3:__
```
Input: nums = [1,2,3]
Output: true
Explanation: [1,2,3] is the original sorted array.
You can rotate the array by x = 0 positions (i.e. no rotation) to make nums.
```
 

__Constraints:__
```
1 <= nums.length <= 100
1 <= nums[i] <= 100
```
#### EXPLANATION:

既然是旋转了的, 那么直接double当前数组, 那么中间一定有一个window能够满足和sorted相同. 有了这个思路就可以. 那么就直接用window去套, 看是否有相同的即可.

#### SOLUTION:
```swift
class Solution {
    func check(_ nums: [Int]) -> Bool {
        let sorted = nums.sorted()
        if sorted == nums || nums.count == 1{
            return true
        }
        var doubleNum = nums + nums
        for index in 0...nums.count-1 {
            var result = true
            for indexJ in 0...nums.count-1 {
                if (doubleNum[index+indexJ] != sorted[indexJ]) {
                    result = false
                    break
                }
            }
            if result {
                return true
            }
        }
        return false
    }
}
```
