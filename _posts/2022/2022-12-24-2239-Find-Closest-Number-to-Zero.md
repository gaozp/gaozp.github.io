---
layout: post
title: 2239. Find Closest Number to Zero
categories: [leetcode]
---
#### QUESTION:
Given an integer array nums of size n, return the number with the value closest to 0 in nums. If there are multiple answers, return the number with the largest value.

 

__Example 1:__
```
Input: nums = [-4,-2,1,4,8]
Output: 1
Explanation:
The distance from -4 to 0 is |-4| = 4.
The distance from -2 to 0 is |-2| = 2.
The distance from 1 to 0 is |1| = 1.
The distance from 4 to 0 is |4| = 4.
The distance from 8 to 0 is |8| = 8.
Thus, the closest number to 0 in the array is 1.
```
__Example 2:__
```
Input: nums = [2,-1,1]
Output: 1
Explanation: 1 and -1 are both the closest numbers to 0, so 1 being larger is returned.
```
 

__Constraints:__
```
1 <= n <= 1000
-105 <= nums[i] <= 105
```
#### EXPLANATION:

比较简单, 找出abs最小的值, 记录下来即可.

#### SOLUTION:
```swift
    func findClosestNumber(_ nums: [Int]) -> Int {
        var result = Int.max
        var resultIndex = Int.max
        let nums = nums.sorted()
        for num in nums {
            if (abs(num) <= result) {
                result = abs(num)
                resultIndex = num
            }
        }
        return resultIndex
    }
```
