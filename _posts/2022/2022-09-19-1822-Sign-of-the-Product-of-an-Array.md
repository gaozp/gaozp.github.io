---
layout: post
title: 1822. Sign of the Product of an Array
categories: [leetcode]
---
#### QUESTION:
There is a function signFunc(x) that returns:

1 if x is positive.
-1 if x is negative.
0 if x is equal to 0.
You are given an integer array nums. Let product be the product of all values in the array nums.

Return signFunc(product).

 

__Example 1:__
```
Input: nums = [-1,-2,-3,-4,3,2,1]
Output: 1
Explanation: The product of all values in the array is 144, and signFunc(144) = 1
```
__Example 2:__
```
Input: nums = [1,5,0,2,-3]
Output: 0
Explanation: The product of all values in the array is 0, and signFunc(0) = 0
```
__Example 3:__
```
Input: nums = [-1,1,-1,1,-1]
Output: -1
Explanation: The product of all values in the array is -1, and signFunc(-1) = -1
```
 

__Constraints:__
```
1 <= nums.length <= 1000
-100 <= nums[i] <= 100
```
#### EXPLANATION:

比较简单的题目, 如果有0直接返回0即可, 否则用1或者-1来防止产生的溢出问题.

#### SOLUTION:
```swift
class Solution {
    func arraySign(_ nums: [Int]) -> Int {
        var result: Int = 1
        for num in nums {
            if num == 0 {
                return 0
            } else if num > 0 {
                result *= 1
            } else if num < 0 {
                result *= -1
            }
        }
        return result
    }
}
```
