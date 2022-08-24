---
layout: post
title: 2164. Sort Even and Odd Indices Independently
categories: [leetcode]
---
#### QUESTION:
You are given a 0-indexed integer array nums. Rearrange the values of nums according to the following rules:

Sort the values at odd indices of nums in non-increasing order.
For example, if nums = [4,1,2,3] before this step, it becomes [4,3,2,1] after. The values at odd indices 1 and 3 are sorted in non-increasing order.
Sort the values at even indices of nums in non-decreasing order.
For example, if nums = [4,1,2,3] before this step, it becomes [2,1,4,3] after. The values at even indices 0 and 2 are sorted in non-decreasing order.
Return the array formed after rearranging the values of nums.

 

__Example 1:__
```
Input: nums = [4,1,2,3]
Output: [2,3,4,1]
Explanation: 
First, we sort the values present at odd indices (1 and 3) in non-increasing order.
So, nums changes from [4,1,2,3] to [4,3,2,1].
Next, we sort the values present at even indices (0 and 2) in non-decreasing order.
So, nums changes from [4,1,2,3] to [2,3,4,1].
Thus, the array formed after rearranging the values is [2,3,4,1].
```
__Example 2:__
```
Input: nums = [2,1]
Output: [2,1]
Explanation: 
Since there is exactly one odd index and one even index, no rearrangement of values takes place.
The resultant array formed is [2,1], which is the same as the initial array. 
```
 

__Constraints:__
```
1 <= nums.length <= 100
1 <= nums[i] <= 100
```
#### EXPLANATION:

将nums数组按照奇偶数取出来分成两个数组,然后重新排序, 再将两个数组组装成一个.

#### SOLUTION:
```swift
class Solution {
    func sortEvenOdd(_ nums: [Int]) -> [Int] {
        var result:[Int] = []
        var flag:Bool = true
        var evenArr:[Int] = []
        var oddArr:[Int] = []
        for num in nums {
            if flag {
                evenArr.append(num)
            } else {
                oddArr.append(num)
            }
            flag = !flag
        }
        evenArr = evenArr.sorted()
        oddArr = oddArr.sorted(by: >)
        flag = true
        for index in 0...nums.count - 1 {
            if flag {
                result.append(evenArr[0])
                evenArr.remove(at: 0)
            } else {
                result.append(oddArr[0])
                oddArr.remove(at: 0)
            }
            flag = !flag
        }
        return result
    }
}
```
