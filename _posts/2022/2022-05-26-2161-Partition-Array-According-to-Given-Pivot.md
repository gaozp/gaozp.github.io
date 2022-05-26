---
layout: post
title: 2161. Partition Array According to Given Pivot
categories: [leetcode]
---
#### QUESTION:
You are given a 0-indexed integer array nums and an integer pivot. Rearrange nums such that the following conditions are satisfied:

Every element less than pivot appears before every element greater than pivot.
Every element equal to pivot appears in between the elements less than and greater than pivot.
The relative order of the elements less than pivot and the elements greater than pivot is maintained.
More formally, consider every pi, pj where pi is the new position of the ith element and pj is the new position of the jth element. For elements less than pivot, if i < j and nums[i] < pivot and nums[j] < pivot, then pi < pj. Similarly for elements greater than pivot, if i < j and nums[i] > pivot and nums[j] > pivot, then pi < pj.
Return nums after the rearrangement.

 

__Example 1:__
```
Input: nums = [9,12,5,10,14,3,10], pivot = 10
Output: [9,5,3,10,10,12,14]
Explanation: 
The elements 9, 5, and 3 are less than the pivot so they are on the left side of the array.
The elements 12 and 14 are greater than the pivot so they are on the right side of the array.
The relative ordering of the elements less than and greater than pivot is also maintained. [9, 5, 3] and [12, 14] are the respective orderings.
```
__Example 2:__
```
Input: nums = [-3,4,3,2], pivot = 2
Output: [-3,2,4,3]
Explanation: 
The element -3 is less than the pivot so it is on the left side of the array.
The elements 4 and 3 are greater than the pivot so they are on the right side of the array.
The relative ordering of the elements less than and greater than pivot is also maintained. [-3] and [4, 3] are the respective orderings.
```
 

__Constraints:__
```
1 <= nums.length <= 105
-106 <= nums[i] <= 106
pivot equals to an element of nums.
```
#### EXPLANATION:

虽然是一个medium的题目, 如果用java来做确实比较难, 因为java数组创建了就是固定了 . 但是swift就简单了. 直接遍历然后放到3个数组中. 最后再将3个数组拼起来即可. 

#### SOLUTION:
```swift
class Solution {
    func pivotArray(_ nums: [Int], _ pivot: Int) -> [Int] {
        var result:[Int] = []
        var pre:[Int] = []
        var current:[Int] = []
        var post:[Int] = []
        for num in nums {
            if num < pivot {
                pre.append(num)
            } else if num > pivot {
                post.append(num)
            } else {
                current.append(num)
            }
        }
        result.append(contentsOf: pre)
        result.append(contentsOf: current)
        result.append(contentsOf: post)
        return result
    }
}
```
