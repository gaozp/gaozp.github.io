---
layout: post
title: 2455. Average Value of Even Numbers That Are Divisible by Three
categories: [leetcode]
---
#### QUESTION:
Given an integer array nums of positive integers, return the average value of all even integers that are divisible by 3.

Note that the average of n elements is the sum of the n elements divided by n and rounded down to the nearest integer.

 

__Example 1:__
```
Input: nums = [1,3,6,10,12,15]
Output: 9
Explanation: 6 and 12 are even numbers that are divisible by 3. (6 + 12) / 2 = 9.
```
__Example 2:__
```
Input: nums = [1,2,4,7,10]
Output: 0
Explanation: There is no single number that satisfies the requirement, so return 0.
```
 

__Constraints:__
```
1 <= nums.length <= 1000
1 <= nums[i] <= 1000
```
#### EXPLANATION:

根据题意, 我们可以知道判断条件就是能够被2和3整除. 那么直接一个for循环, 把这些数字挑出来, 同时用sum记录和. 最后用sum除以count即可. 注意被除数为0的情况.

#### SOLUTION:
```swift
class Solution {
    func averageValue(_ nums: [Int]) -> Int {
        var arr: [Int] = []
        var sum: Int = 0
        for num in nums {
            if (num % 2 == 0 && num % 3 == 0) {
                arr.append(num)
                sum += num
            }
        }
        return arr.count == 0 ? 0 : sum / arr.count
    }
}
```
