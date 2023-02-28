---
layout: post
title: 2341. Maximum Number of Pairs in Array
categories: [leetcode]
---
#### QUESTION:
You are given a 0-indexed integer array nums. In one operation, you may do the following:

Choose two integers in nums that are equal.
Remove both integers from nums, forming a pair.
The operation is done on nums as many times as possible.

Return a 0-indexed integer array answer of size 2 where answer[0] is the number of pairs that are formed and answer[1] is the number of leftover integers in nums after doing the operation as many times as possible.

 

__Example 1:__
```
Input: nums = [1,3,2,1,3,2,2]
Output: [3,1]
Explanation:
Form a pair with nums[0] and nums[3] and remove them from nums. Now, nums = [3,2,3,2,2].
Form a pair with nums[0] and nums[2] and remove them from nums. Now, nums = [2,2,2].
Form a pair with nums[0] and nums[1] and remove them from nums. Now, nums = [2].
No more pairs can be formed. A total of 3 pairs have been formed, and there is 1 number leftover in nums.
```
__Example 2:__
```
Input: nums = [1,1]
Output: [1,0]
Explanation: Form a pair with nums[0] and nums[1] and remove them from nums. Now, nums = [].
No more pairs can be formed. A total of 1 pair has been formed, and there are 0 numbers leftover in nums.
```
__Example 3:__
```
Input: nums = [0]
Output: [0,1]
Explanation: No pairs can be formed, and there is 1 number leftover in nums.
```
 

__Constraints:__
```
1 <= nums.length <= 100
0 <= nums[i] <= 100
```
#### EXPLANATION:

easy的题目， 只要对当前的nums进行遍历， 如果当前的数字出现过偶数次， 那么就将formed数量+1， 最后将formed数量和 总数量 - formed * 2 返回就可以。

#### SOLUTION:
```swift
class Solution {
    func numberOfPairs(_ nums: [Int]) -> [Int] {
        var dic:Dictionary<Int,Int> = [:]
        var formed:Int = 0
        for num in nums {
            if dic[num] == nil {
                dic[num] = 1
            } else {
                dic[num] = dic[num]! + 1
            }
            if dic[num]! % 2 == 0 {
                formed += 1
            }
        }
        return [formed, nums.count - formed * 2]
    }
}
```
