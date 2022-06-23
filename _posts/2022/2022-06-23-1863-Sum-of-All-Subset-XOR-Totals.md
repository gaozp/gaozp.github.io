---
layout: post
title: 1863. Sum of All Subset XOR Totals
categories: [leetcode]
---
#### QUESTION:
The XOR total of an array is defined as the bitwise XOR of all its elements, or 0 if the array is empty.

For example, the XOR total of the array [2,5,6] is 2 XOR 5 XOR 6 = 1.
Given an array nums, return the sum of all XOR totals for every subset of nums. 

Note: Subsets with the same elements should be counted multiple times.

An array a is a subset of an array b if a can be obtained from b by deleting some (possibly zero) elements of b.

 

__Example 1:__
```
Input: nums = [1,3]
Output: 6
Explanation: The 4 subsets of [1,3] are:
- The empty subset has an XOR total of 0.
- [1] has an XOR total of 1.
- [3] has an XOR total of 3.
- [1,3] has an XOR total of 1 XOR 3 = 2.
0 + 1 + 3 + 2 = 6
```
__Example 2:__
```
Input: nums = [5,1,6]
Output: 28
Explanation: The 8 subsets of [5,1,6] are:
- The empty subset has an XOR total of 0.
- [5] has an XOR total of 5.
- [1] has an XOR total of 1.
- [6] has an XOR total of 6.
- [5,1] has an XOR total of 5 XOR 1 = 4.
- [5,6] has an XOR total of 5 XOR 6 = 3.
- [1,6] has an XOR total of 1 XOR 6 = 7.
- [5,1,6] has an XOR total of 5 XOR 1 XOR 6 = 2.
0 + 5 + 1 + 6 + 4 + 3 + 7 + 2 = 28
```
__Example 3:__
```
Input: nums = [3,4,5,6,7,8]
Output: 480
Explanation: The sum of all XOR totals for every subset is 480.
```

__Constraints:__
```
1 <= nums.length <= 12
1 <= nums[i] <= 20
```
#### EXPLANATION:

这是一个简单题目, 利用swift的函数编程特性. 一个for循环, 将所有可能的组合都放到res列表中, 再对res列表中的每个数组进行求异或, 结果相加即可.

#### SOLUTION:
```swift
class Solution {
    func subsetXORSum(_ nums: [Int]) -> Int {
        var res: [[Int]] = [[]] 
        var sum: Int = 0
        
        for num in nums {
            for subset in res {
                res.append(subset + [num]) 
                sum += res.last!.reduce(0) { $0 ^ $1 }
            }
        }
        
        return sum
    }
}
```
