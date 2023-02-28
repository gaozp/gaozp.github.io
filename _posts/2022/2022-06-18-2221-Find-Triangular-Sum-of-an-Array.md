---
layout: post
title: 2221. Find Triangular Sum of an Array
categories: [leetcode]
---
#### QUESTION:
You are given a 0-indexed integer array nums, where nums[i] is a digit between 0 and 9 (inclusive).

The triangular sum of nums is the value of the only element present in nums after the following process terminates:

Let nums comprise of n elements. If n == 1, end the process. Otherwise, create a new 0-indexed integer array newNums of length n - 1.
For each index i, where 0 <= i < n - 1, assign the value of newNums[i] as (nums[i] + nums[i+1]) % 10, where % denotes modulo operator.
Replace the array nums with newNums.
Repeat the entire process starting from step 1.
Return the triangular sum of nums.

 

__Example 1:__
![](https://assets.leetcode.com/uploads/2022/02/22/ex1drawio.png)
```
Input: nums = [1,2,3,4,5]
Output: 8
Explanation:
The above diagram depicts the process from which we obtain the triangular sum of the array.
```
__Example 2:__
```
Input: nums = [5]
Output: 5
Explanation:
Since there is only one element in nums, the triangular sum is the value of that element itself.
 ```

__Constraints:__
```
1 <= nums.length <= 1000
0 <= nums[i] <= 9
```
#### EXPLANATION:

使用index和index+1进行索引计算, 这样计算的结果就可以放在index的位置上. 同时, 每次循环之后, 将数组的长度减1 . 到最后数组长度只剩下1的时候那就是需要的结果了.

#### SOLUTION:
```swift
class Solution {
    func triangularSum(_ nums: [Int]) -> Int {
        var sums = nums
        var length:Int = sums.count
        while length != 1 {
            for index in 0..<sums.count - 1 {
                sums[index] = (sums[index] + sums[index+1]) % 10
            }
            length -= 1
        }
        return sums[0]
    }
}
```
