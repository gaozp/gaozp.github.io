---
layout: post
title: 1979. Find Greatest Common Divisor of Array
categories: [leetcode]
---
#### QUESTION:
Given an integer array nums, return the greatest common divisor of the smallest number and largest number in nums.

The greatest common divisor of two numbers is the largest positive integer that evenly divides both numbers.

 

__Example 1:__
```
Input: nums = [2,5,6,9,10]
Output: 2
Explanation:
The smallest number in nums is 2.
The largest number in nums is 10.
The greatest common divisor of 2 and 10 is 2.
```
__Example 2:__
```
Input: nums = [7,5,6,8,3]
Output: 1
Explanation:
The smallest number in nums is 3.
The largest number in nums is 8.
The greatest common divisor of 3 and 8 is 1.
```
__Example 3:__
```
Input: nums = [3,3]
Output: 3
Explanation:
The smallest number in nums is 3.
The largest number in nums is 3.
The greatest common divisor of 3 and 3 is 3.
```

__Constraints:__
```
2 <= nums.length <= 1000
1 <= nums[i] <= 1000
```
#### EXPLANATION:

easy的题目 , 排序后直接找最大公约数就可以.

#### SOLUTION:
```swift
class Solution {
    func findGCD(_ nums: [Int]) -> Int {
        var arr = nums.sorted()
        var smallest:Int = arr[0]
        var biggest:Int = arr[arr.count-1]
        var result:Int = 0
        for i in 1...smallest {
            if smallest % i == 0 && biggest % i == 0 {
                result = i
            }
        }
        return result
    }
}
```
