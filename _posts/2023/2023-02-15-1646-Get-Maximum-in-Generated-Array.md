---
layout: post
title: 1646. Get Maximum in Generated Array
categories: [leetcode]
---
#### QUESTION:
You are given an integer n. A 0-indexed integer array nums of length n + 1 is generated in the following way:

nums[0] = 0
nums[1] = 1
nums[2 * i] = nums[i] when 2 <= 2 * i <= n
nums[2 * i + 1] = nums[i] + nums[i + 1] when 2 <= 2 * i + 1 <= n
Return the maximum integer in the array nums​​​.

 

__Example 1:__
```
Input: n = 7
Output: 3
Explanation: According to the given rules:
  nums[0] = 0
  nums[1] = 1
  nums[(1 * 2) = 2] = nums[1] = 1
  nums[(1 * 2) + 1 = 3] = nums[1] + nums[2] = 1 + 1 = 2
  nums[(2 * 2) = 4] = nums[2] = 1
  nums[(2 * 2) + 1 = 5] = nums[2] + nums[3] = 1 + 2 = 3
  nums[(3 * 2) = 6] = nums[3] = 2
  nums[(3 * 2) + 1 = 7] = nums[3] + nums[4] = 2 + 1 = 3
Hence, nums = [0,1,1,2,1,3,2,3], and the maximum is max(0,1,1,2,1,3,2,3) = 3.
```
__Example 2:__
```
Input: n = 2
Output: 1
Explanation: According to the given rules, nums = [0,1,1]. The maximum is max(0,1,1) = 1.
```
__Example 3:__
```
Input: n = 3
Output: 2
Explanation: According to the given rules, nums = [0,1,1,2]. The maximum is max(0,1,1,2) = 2.
```
 

__Constraints:__
```
0 <= n <= 100
```
#### EXPLANATION:

比较简单, 模拟出来即可.

#### SOLUTION:
```swift
class Solution {
    func getMaximumGenerated(_ n: Int) -> Int {
        var arr: [Int] = [0,1]
        if n == 0 {
            return 0
        }
        if n == 1 {
            return 1
        }
        for index in 2...n {
            if index % 2 == 0 {
                arr.append(arr[index/2])
            } else {
                arr.append(arr[index/2] + arr[index/2+1])
            }
        }
        return arr.max()!
    }
}
```
