---
layout: post
title: 1512. Number of Good Pairs
categories: [leetcode]
---
#### QUESTION:
Given an array of integers nums.

A pair (i,j) is called good if nums[i] == nums[j] and i < j.

Return the number of good pairs.

__Example 1:__
```
Input: nums = [1,2,3,1,1,3]
Output: 4
```
Explanation: There are 4 good pairs (0,3), (0,4), (3,4), (2,5) 0-indexed.

__Example 2:__
```
Input: nums = [1,1,1,1]
Output: 6
```
Explanation: Each pair in the array are good.

__Example 3:__
```
Input: nums = [1,2,3]
Output: 0
 ```

__Constraints:__
```
1 <= nums.length <= 100
1 <= nums[i] <= 100
```
#### EXPLANATION:
这道题目也是很简单, 只需要两个循环进行判断就可以得出结果. 但是就是不知道两个lambda表达式的嵌套循环在cr的时候能否过得去.
#### SOLUTION:
```kotlin
fun numIdenticalPairs(nums: IntArray): Int {
    var result: Int = 0
    nums.forEachIndexed { i, tmpa ->
        nums.forEachIndexed { j, tmpb ->
            if (i < j && tmpa == tmpb) result++
        }
    }
    return result
}
```
