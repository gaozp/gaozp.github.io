---
layout: post
title: 2248. Intersection of Multiple Arrays
categories: [leetcode]
---
#### QUESTION:
Given a 2D integer array nums where nums[i] is a non-empty array of distinct positive integers, return the list of integers that are present in each array of nums sorted in ascending order.
 

__Example 1:__
```
Input: nums = [[3,1,2,4,5],[1,2,3,4],[3,4,5,6]]
Output: [3,4]
Explanation: 
The only integers present in each of nums[0] = [3,1,2,4,5], nums[1] = [1,2,3,4], and nums[2] = [3,4,5,6] are 3 and 4, so we return [3,4].
```
__Example 2:__
```
Input: nums = [[1,2,3],[4,5,6]]
Output: []
Explanation: 
There does not exist any integer present both in nums[0] and nums[1], so we return an empty list [].
```
 

__Constraints:__
```
1 <= nums.length <= 1000
1 <= sum(nums[i].length) <= 1000
1 <= nums[i][j] <= 1000
All the values of nums[i] are unique.
```
#### EXPLANATION:

只要注意到题目中说的distinct,那么就可以知道,如果一个数字出现了nums.count次,就说明它在每个数组中都出现了.添加到结果中就可以了.

#### SOLUTION:
```swift
class Solution {
    func intersection(_ nums: [[Int]]) -> [Int] {
        var dic:Dictionary<Int,Int> = [:]
        for num in nums {
            for n in num {
                if dic[n] == nil {
                    dic[n] = 1
                } else {
                    dic[n] = dic[n]! + 1
                }
            }
        }
        var result:[Int] = []
        dic.forEach { (key: Int, value: Int) in
            if value == nums.count {
                result.append(key)
            }
        }
        return result.sorted()
    }
}
```
