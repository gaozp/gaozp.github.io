---
layout: post
title: 2006. Count Number of Pairs With Absolute Difference K
categories: [leetcode]
---
#### QUESTION:
Given an integer array nums and an integer k, return the number of pairs (i, j) where i < j such that |nums[i] - nums[j]| == k.

The value of |x| is defined as:

x if x >= 0.
-x if x < 0.
 

__Example 1:__
```
Input: nums = [1,2,2,1], k = 1
Output: 4
Explanation: The pairs with an absolute difference of 1 are:
- [1,2,2,1]
- [1,2,2,1]
- [1,2,2,1]
- [1,2,2,1]
```
__Example 2:__
```
Input: nums = [1,3], k = 3
Output: 0
Explanation: There are no pairs with an absolute difference of 3.
```
__Example 3:__
```
Input: nums = [3,2,1,5,4], k = 2
Output: 3
Explanation: The pairs with an absolute difference of 2 are:
- [3,2,1,5,4]
- [3,2,1,5,4]
- [3,2,1,5,4]
 ```

__Constraints:__
```
1 <= nums.length <= 200
1 <= nums[i] <= 100
1 <= k <= 99
```
#### EXPLANATION:

简单题, 双循环比对绝对值就行. 注意边界情况.

#### SOLUTION:
```java
class Solution {
    func countKDifference(_ nums: [Int], _ k: Int) -> Int {
        var result:Int = 0;
        if nums.count == 1 {
            return result
        }

        for i in 0...nums.count-2 {
            for j in i+1 ... nums.count-1 {
                if abs(nums[i] - nums[j]) == k {
                    result += 1
                }
            }
        }
        return result
    }
}
```
