---
layout: post
title: 1877. Minimize Maximum Pair Sum in Array
categories: [leetcode]
---
#### QUESTION:
The pair sum of a pair (a,b) is equal to a + b. The maximum pair sum is the largest pair sum in a list of pairs.

For example, if we have pairs (1,5), (2,3), and (4,4), the maximum pair sum would be max(1+5, 2+3, 4+4) = max(6, 5, 8) = 8.
Given an array nums of even length n, pair up the elements of nums into n / 2 pairs such that:

Each element of nums is in exactly one pair, and
The maximum pair sum is minimized.
Return the minimized maximum pair sum after optimally pairing up the elements.

 

__Example 1:__
```
Input: nums = [3,5,2,3]
Output: 7
Explanation: The elements can be paired up into pairs (3,3) and (5,2).
The maximum pair sum is max(3+3, 5+2) = max(6, 7) = 7.
```
__Example 2:__
```
Input: nums = [3,5,4,2,4,6]
Output: 8
Explanation: The elements can be paired up into pairs (3,5), (4,4), and (6,2).
The maximum pair sum is max(3+5, 4+4, 6+2) = max(8, 8, 8) = 8.
 ```

Constraints:

n == nums.length
2 <= n <= 105
n is even.
1 <= nums[i] <= 105
#### EXPLANATION:

题目很简单, 最小的和那肯定就是大数加小数了. 那么排序一下就可以. 然后再在其中的和里找到最小的一个就可以. 

#### SOLUTION:
```swift
class Solution {
    func minPairSum(_ nums: [Int]) -> Int {
        var result:Int = 0
        let array = nums.sorted()
        for i in 0...nums.count/2 - 1 {
            result = max(result, array[i] + array[array.count - 1 - i])
        }
        return result
    }
}
```
