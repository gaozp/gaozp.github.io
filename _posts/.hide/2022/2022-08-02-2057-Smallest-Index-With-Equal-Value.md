---
layout: post
title: 2057. Smallest Index With Equal Value
categories: [leetcode]
---
#### QUESTION:
Given a 0-indexed integer array nums, return the smallest index i of nums such that i mod 10 == nums[i], or -1 if such index does not exist.

x mod y denotes the remainder when x is divided by y.

 

__Example 1:__
```
Input: nums = [0,1,2]
Output: 0
Explanation: 
i=0: 0 mod 10 = 0 == nums[0].
i=1: 1 mod 10 = 1 == nums[1].
i=2: 2 mod 10 = 2 == nums[2].
All indices have i mod 10 == nums[i], so we return the smallest index 0.
```
__Example 2:__
```
Input: nums = [4,3,2,1]
Output: 2
Explanation: 
i=0: 0 mod 10 = 0 != nums[0].
i=1: 1 mod 10 = 1 != nums[1].
i=2: 2 mod 10 = 2 == nums[2].
i=3: 3 mod 10 = 3 != nums[3].
2 is the only index which has i mod 10 == nums[i].
```
__Example 3:__
```
Input: nums = [1,2,3,4,5,6,7,8,9,0]
Output: -1
Explanation: No index satisfies i mod 10 == nums[i].
```
 

__Constraints:__
```
1 <= nums.length <= 100
0 <= nums[i] <= 9
```
#### EXPLANATION:

easy的题目,一个for循环搞定.

#### SOLUTION:
```swift
class Solution {
    func smallestEqual(_ nums: [Int]) -> Int {
        var result:Int = -1
        for index in nums.indices {
            if index % 10 == nums[index] {
                return index
            }
        }
        return result
    }
}
```
