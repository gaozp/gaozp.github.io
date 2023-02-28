---
layout: post
title: 1929. Concatenation of Array
categories: [leetcode]
---
#### QUESTION:
Given an integer array nums of length n, you want to create an array ans of length 2n where ans[i] == nums[i] and ans[i + n] == nums[i] for 0 <= i < n (0-indexed).

Specifically, ans is the concatenation of two nums arrays.

Return the array ans.

__Example 1:__
```
Input: nums = [1,2,1]
Output: [1,2,1,1,2,1]
Explanation: The array ans is formed as follows:
- ans = [nums[0],nums[1],nums[2],nums[0],nums[1],nums[2]]
- ans = [1,2,1,1,2,1]
```
__Example 2:__
```
Input: nums = [1,3,2,1]
Output: [1,3,2,1,1,3,2,1]
Explanation: The array ans is formed as follows:
- ans = [nums[0],nums[1],nums[2],nums[3],nums[0],nums[1],nums[2],nums[3]]
- ans = [1,3,2,1,1,3,2,1]
 ```

__Constraints:__
```
n == nums.length
1 <= n <= 1000
1 <= nums[i] <= 1000
```
#### EXPLANATION:

这道题目是一个easy的题目. 其实就是复制一下之前的数组. 如果不用工具类的话.   
我们可以知道 ans[i] = nums[ i % nums.size ], 既然知道这个公式, 那么久可以直接这样写就行了.

#### SOLUTION:
```java
class Solution {
    fun getConcatenation(nums: IntArray): IntArray {
        var result = IntArray(nums.size * 2)
        for (i in result.indices) result[i] = nums[i % nums.size]
        return result
    }
}
```
