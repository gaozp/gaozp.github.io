---
layout: post
title: 1920. Build Array from Permutation
categories: [leetcode]
---
#### QUESTION:
Given a zero-based permutation nums (0-indexed), build an array ans of the same length where ans[i] = nums[nums[i]] for each 0 <= i < nums.length and return it.

A zero-based permutation nums is an array of distinct integers from 0 to nums.length - 1 (inclusive).

 

__Example 1:__
```
Input: nums = [0,2,1,5,3,4]
Output: [0,1,2,4,5,3]
Explanation: The array ans is built as follows: 
ans = [nums[nums[0]], nums[nums[1]], nums[nums[2]], nums[nums[3]], nums[nums[4]], nums[nums[5]]]
    = [nums[0], nums[2], nums[1], nums[5], nums[3], nums[4]]
    = [0,1,2,4,5,3]
```
__Example 2:__
```
Input: nums = [5,0,1,2,3,4]
Output: [4,5,0,1,2,3]
Explanation: The array ans is built as follows:
ans = [nums[nums[0]], nums[nums[1]], nums[nums[2]], nums[nums[3]], nums[nums[4]], nums[nums[5]]]
    = [nums[5], nums[0], nums[1], nums[2], nums[3], nums[4]]
    = [4,5,0,1,2,3]
 
```
__Constraints:__
```
1 <= nums.length <= 1000
0 <= nums[i] < nums.length
The elements in nums are distinct.
```
Follow-up: Can you solve it without using an extra space (i.e., O(1) memory)?
#### EXPLANATION:

解题思路题目中已经说明了. 其实就是result[i] = nums[nums[i]] . 
但是另外一个不使用额外的空间. 我们的解决方法就是:  
因为当前的数字最多到1000, 所以用二进制表示的话就是最多占11位. 那么32位的int值就完全能在高位进行保存.   
那么,我们就将nums[nums[i]]放在高位上, 这样在下一次循环的时候, 我们向右, 减去原先的数字, 那么, 就可以得到对应的结果了. 



#### SOLUTION:
```kotlin
class Solution {
    fun buildArray(nums: IntArray): IntArray {
        var result:IntArray = IntArray(nums.size)
        for (i in result.indices) 
            result[i] = nums[nums[i]]
        return result
    }
}

class Solution {
    fun buildArray(nums: IntArray): IntArray {
        var mask = 1023
        nums.forEachIndexed { index, num ->
            nums[index] = nums[index] or ((nums[nums[index]] and mask) shl 10)
        }

        nums.forEachIndexed { index, num ->
            nums[index] = nums[index] shr 10
        }

        return nums
    }
}
```
