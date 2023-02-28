---
layout: post
title: 1748. Sum of Unique Elements
categories: [leetcode]
---
#### QUESTION:
You are given an integer array nums. The unique elements of an array are the elements that appear exactly once in the array.

Return the sum of all the unique elements of nums.

 

__Example 1:__
```
Input: nums = [1,2,3,2]
Output: 4
Explanation: The unique elements are [1,3], and the sum is 4.
```
__Example 2:__
```
Input: nums = [1,1,1,1,1]
Output: 0
Explanation: There are no unique elements, and the sum is 0.
```
__Example 3:__
```
Input: nums = [1,2,3,4,5]
Output: 15
Explanation: The unique elements are [1,2,3,4,5], and the sum is 15.
```
 

__Constraints:__
```
1 <= nums.length <= 100
1 <= nums[i] <= 100
```
#### EXPLANATION:

easy的题目， for循环将每个数字出现的次数算出， 再一个for循环将只出现1次的数字加入结果

#### SOLUTION:
```swift
class Solution {
    func sumOfUnique(_ nums: [Int]) -> Int {
        var arr:[Int] = Array(repeating: 0, count: 101)
        var sum:Int = 0
        for num in nums {
            arr[num] += 1
        }
        
        for index in arr.indices {
            if (arr[index] == 1) {
                sum += index
            }
        }
        
        return sum
    }
}
```
