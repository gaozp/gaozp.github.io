---
layout: post
title: 2404. Most Frequent Even Element
categories: [leetcode]
---
#### QUESTION:
Given an integer array nums, return the most frequent even element.

If there is a tie, return the smallest one. If there is no such element, return -1.

 

__Example 1:__
```
Input: nums = [0,1,2,2,4,4,1]
Output: 2
Explanation:
The even elements are 0, 2, and 4. Of these, 2 and 4 appear the most.
We return the smallest one, which is 2.
```
__Example 2:__
```
Input: nums = [4,4,4,9,2,4]
Output: 4
Explanation: 4 is the even element appears the most.
```
__Example 3:__
```
Input: nums = [29,47,21,41,13,37,25,7]
Output: -1
Explanation: There is no even element.
```
 

__Constraints:__
```
1 <= nums.length <= 2000
0 <= nums[i] <= 105
```
#### EXPLANATION:

新学会了在for循环的时候直接使用where关键字, 就可以节省一个if括号, 优雅, 非常的优雅. 最后在dic里进行一下排序, 如果value相同就排序key, 以value的降序排列. 最后取第一个就行. 如果没去到就返回-1. 

#### SOLUTION:
```swift
class Solution {
    func mostFrequentEven(_ nums: [Int]) -> Int {
        var dic:Dictionary<Int,Int> = [:]
        for num in nums where num % 2 == 0 {
            dic[num] = dic[num, default: 0] + 1
        }
        return dic.sorted { a, b in
            if a.value == b.value {
                return a.key < b.key
            } else {
                return a.value > b.value
            }
        }.first?.key ?? -1
    }
}
```
