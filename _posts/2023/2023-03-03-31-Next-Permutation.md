---
layout: post
title: 31. Next Permutation
categories: [leetcode]
---
#### QUESTION:
A permutation of an array of integers is an arrangement of its members into a sequence or linear order.

For example, for arr = [1,2,3], the following are all the permutations of arr: [1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1].
The next permutation of an array of integers is the next lexicographically greater permutation of its integer. More formally, if all the permutations of the array are sorted in one container according to their lexicographical order, then the next permutation of that array is the permutation that follows it in the sorted container. If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).

For example, the next permutation of arr = [1,2,3] is [1,3,2].
Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a lexicographical larger rearrangement.
Given an array of integers nums, find the next permutation of nums.

The replacement must be in place and use only constant extra memory.

 

__Example 1:__
```
Input: nums = [1,2,3]
Output: [1,3,2]
```
__Example 2:__
```
Input: nums = [3,2,1]
Output: [1,2,3]
```
__Example 3:__
```
Input: nums = [1,1,5]
Output: [1,5,1]
```
 

__Constraints:__
```
1 <= nums.length <= 100
0 <= nums[i] <= 100
```
#### EXPLANATION:

找到需要调整的位置, 再将后面的所有进行排序即可.

#### SOLUTION:
```swift
class Solution {
    func nextPermutation(_ nums: inout [Int]) {
        var breakPoint = -1
        for i in stride(from: nums.count - 1, to: 0, by: -1) {
            if nums[i] > nums[i-1] {
                breakPoint = i - 1
                break
            }
        }
        if breakPoint < 0 {
            nums = nums.reversed()
            return
        }
        for i in stride(from: nums.count - 1, to: 0, by: -1) {
            if nums[breakPoint] < nums[i] {
                nums.swapAt(breakPoint, i)
                nums[(breakPoint+1)...].reverse()
                break
            }
        }
    }
}
```
