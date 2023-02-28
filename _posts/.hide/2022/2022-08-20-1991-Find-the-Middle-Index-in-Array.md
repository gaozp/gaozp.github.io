---
layout: post
title: 1991. Find the Middle Index in Array
categories: [leetcode]
---
#### QUESTION:
Given a 0-indexed integer array nums, find the leftmost middleIndex (i.e., the smallest amongst all the possible ones).

A middleIndex is an index where nums[0] + nums[1] + ... + nums[middleIndex-1] == nums[middleIndex+1] + nums[middleIndex+2] + ... + nums[nums.length-1].

If middleIndex == 0, the left side sum is considered to be 0. Similarly, if middleIndex == nums.length - 1, the right side sum is considered to be 0.

Return the leftmost middleIndex that satisfies the condition, or -1 if there is no such index.

 

__Example 1:__
```
Input: nums = [2,3,-1,8,4]
Output: 3
Explanation: The sum of the numbers before index 3 is: 2 + 3 + -1 = 4
The sum of the numbers after index 3 is: 4 = 4
```
__Example 2:__
```
Input: nums = [1,-1,4]
Output: 2
Explanation: The sum of the numbers before index 2 is: 1 + -1 = 0
The sum of the numbers after index 2 is: 0
```
__Example 3:__
```
Input: nums = [2,5]
Output: -1
Explanation: There is no valid middleIndex.
```
 

__Constraints:__
```
1 <= nums.length <= 100
-1000 <= nums[i] <= 1000
```
#### EXPLANATION:

思路也比较简单, 一个左右的和都是可以算出来的, 但是就不需要每次都重新计算, 这样会浪费很多算力. 我们可以把每个位置的左右都算出来, 放在对应的数组里再进行比较,这样就可以快速的得到后果了.

#### SOLUTION:
```swift
class Solution {
    func findMiddleIndex(_ nums: [Int]) -> Int {
        var left:[Int] = [0]
        var right:[Int] = [0]
        var sum:Int = 0
        for num in nums {
            left.append(sum+num)
            sum += num
        }
        sum = 0
        for num in nums.reversed() {
            right.insert(sum+num, at: 0)
            sum += num
        }
        for index in 0...nums.count - 1 {
            if left[index] == right[index + 1] {
                return index
            }
        }
        return -1
    }
}
```
