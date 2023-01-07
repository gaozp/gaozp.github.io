---
layout: post
title: 2044. Count Number of Maximum Bitwise-OR Subsets
categories: [leetcode]
---
#### QUESTION:
Given an integer array nums, find the maximum possible bitwise OR of a subset of nums and return the number of different non-empty subsets with the maximum bitwise OR.

An array a is a subset of an array b if a can be obtained from b by deleting some (possibly zero) elements of b. Two subsets are considered different if the indices of the elements chosen are different.

The bitwise OR of an array a is equal to a[0] OR a[1] OR ... OR a[a.length - 1] (0-indexed).

 

__Example 1:__
```
Input: nums = [3,1]
Output: 2
Explanation: The maximum possible bitwise OR of a subset is 3. There are 2 subsets with a bitwise OR of 3:
- [3]
- [3,1]
```
__Example 2:__
```
Input: nums = [2,2,2]
Output: 7
Explanation: All non-empty subsets of [2,2,2] have a bitwise OR of 2. There are 23 - 1 = 7 total subsets.
```
__Example 3:__
```
Input: nums = [3,2,1,5]
Output: 6
Explanation: The maximum possible bitwise OR of a subset is 7. There are 6 subsets with a bitwise OR of 7:
- [3,5]
- [3,1,5]
- [3,2,5]
- [3,2,1,5]
- [2,5]
- [2,1,5]
```
 

__Constraints:__
```
1 <= nums.length <= 16
1 <= nums[i] <= 105
```
#### EXPLANATION:

这道题目一看就可以用回溯法去做, 因为要得到所有的子序列. 那么就可以用一个dic来装对应的值和数量. 最后再对key进行排序. 返回最后的结果就行.

#### SOLUTION:
```swift
class Solution {
    func countMaxOrSubsets(_ nums: [Int]) -> Int {
        var countMaxOrSubsetsResult: Dictionary<Int,Int> = [:]
        countMaxOrSubsetsHelper(nums, tmpIndex: 0, tmpValue: 0, countMaxOrSubsetsResult: &countMaxOrSubsetsResult)
        let result = countMaxOrSubsetsResult.sorted { a, b in
            a.key > b.key
        }
        return result[0].value
    }
    
    func countMaxOrSubsetsHelper(_ nums: [Int], tmpIndex: Int, tmpValue: Int, countMaxOrSubsetsResult: inout Dictionary<Int,Int>) {
        if tmpIndex == nums.count {
            return
        }
        for i in tmpIndex...nums.count-1 {
            let tmp = tmpValue | nums[i]
            countMaxOrSubsetsResult[tmp] = countMaxOrSubsetsResult[tmp,default: 0] + 1
            countMaxOrSubsetsHelper(nums, tmpIndex: i+1, tmpValue: tmp, countMaxOrSubsetsResult: &countMaxOrSubsetsResult)
        }
    }
}
```
