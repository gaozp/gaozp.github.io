---
layout: post
title: 3289. The Two Sneaky Numbers of Digitville
categories: [leetcode]
---
#### QUESTION:
In the town of Digitville, there was a list of numbers called nums containing integers from 0 to n - 1. Each number was supposed to appear exactly once in the list, however, two mischievous numbers sneaked in an additional time, making the list longer than usual.

As the town detective, your task is to find these two sneaky numbers. Return an array of size two containing the two numbers (in any order), so peace can return to Digitville.

 

__Example 1:__
```
Input: nums = [0,1,1,0]

Output: [0,1]

Explanation:

The numbers 0 and 1 each appear twice in the array.
```
__Example 2:__
```
Input: nums = [0,3,2,1,3,2]

Output: [2,3]

Explanation:

The numbers 2 and 3 each appear twice in the array.
```
__Example 3:__
```
Input: nums = [7,1,5,4,3,4,6,0,9,5,8,2]

Output: [4,5]

Explanation:

The numbers 4 and 5 each appear twice in the array.
```
 

__Constraints:__
```
2 <= n <= 100
nums.length == n + 2
0 <= nums[i] < n
The input is generated such that nums contains exactly two repeated elements.
```
#### EXPLANATION:

有一个小知识, 就是相等的两个数异或, 结果就是0. 所以我们可以利用这个. 并且我们也从条件中得到. 每个数最多出现两次. 那么我们就可以先进行排序. 然后通过排序后的两个数进行比对. 如果等于0则说明两个数相等. 放到结果中即可. 这样就可以得到一个时间复杂度为O(n)的方式.

#### SOLUTION:
```swift
class Solution {
    func getSneakyNumbers(_ nums: [Int]) -> [Int] {
        let sorted: [Int] = nums.sorted()
        var result: [Int] = []
        for i in 0...sorted.count - 2 {
            if sorted[i] ^ sorted[i+1] == 0 {
                result.append(sorted[i])
            }
        }
        return result
    }
}
```
