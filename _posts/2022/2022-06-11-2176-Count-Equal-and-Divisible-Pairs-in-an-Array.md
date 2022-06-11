---
layout: post
title: 2176. Count Equal and Divisible Pairs in an Array
categories: [leetcode]
---
#### QUESTION:
Given a 0-indexed integer array nums of length n and an integer k, return the number of pairs (i, j) where 0 <= i < j < n, such that nums[i] == nums[j] and (i * j) is divisible by k.
 

__Example 1:__
```
Input: nums = [3,1,2,2,2,1,3], k = 2
Output: 4
Explanation:
There are 4 pairs that meet all the requirements:
- nums[0] == nums[6], and 0 * 6 == 0, which is divisible by 2.
- nums[2] == nums[3], and 2 * 3 == 6, which is divisible by 2.
- nums[2] == nums[4], and 2 * 4 == 8, which is divisible by 2.
- nums[3] == nums[4], and 3 * 4 == 12, which is divisible by 2.
```
__Example 2:__
```
Input: nums = [1,2,3,4], k = 1
Output: 0
Explanation: Since no value in nums is repeated, there are no pairs (i,j) that meet all the requirements.
```
 

__Constraints:__
```
1 <= nums.length <= 100
1 <= nums[i], k <= 100
```
#### EXPLANATION:

easy的题目, 也就是两个for循环再加上一个判断就是可以了. 

#### SOLUTION:
```swift
class Solution {
    func countPairs(_ nums: [Int], _ k: Int) -> Int {
        var result:Int = 0
        for indexI in 0..<nums.count {
            for indexJ in indexI+1..<nums.count {
                if ((nums[indexI] == nums[indexJ])
                    && (indexI*indexJ)%k == 0) {
                    result+=1
                }
            }
        }
        return result
    }
}
```
