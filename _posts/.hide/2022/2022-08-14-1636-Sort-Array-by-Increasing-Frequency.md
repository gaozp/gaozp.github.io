---
layout: post
title: 1636. Sort Array by Increasing Frequency
categories: [leetcode]
---
#### QUESTION:
Given an array of integers nums, sort the array in increasing order based on the frequency of the values. If multiple values have the same frequency, sort them in decreasing order.

Return the sorted array.

 

__Example 1:__
```
Input: nums = [1,1,2,2,2,3]
Output: [3,1,1,2,2,2]
Explanation: '3' has a frequency of 1, '1' has a frequency of 2, and '2' has a frequency of 3.
```
__Example 2:__
```
Input: nums = [2,3,1,3,2]
Output: [1,3,3,2,2]
Explanation: '2' and '3' both have a frequency of 2, so they are sorted in decreasing order.
```
__Example 3:__
```
Input: nums = [-1,1,-6,4,5,-6,1,4,1]
Output: [5,-1,4,4,-6,-6,1,1,1]
```
 

__Constraints:__
```
1 <= nums.length <= 100
-100 <= nums[i] <= 100
```
#### EXPLANATION:

easy的题目, 一开始只要将频率进行统计. 放在一个字典里. 然后对字典进行排序即可. 最后返回排序后的字典就ok.

#### SOLUTION:
```swift
class Solution {
    func frequencySort(_ nums: [Int]) -> [Int] {
        var dic:Dictionary<Int,Int> = [:]
        for num in nums {
            dic[num,default: 0] += 1
        }
        return dic.sorted(by: { $0.value == $1.value ? $0.key > $1.key : $0.value < $1.value}).flatMap ({Array(repeating:$0.key, count:$0.value)})
    }
}
```
