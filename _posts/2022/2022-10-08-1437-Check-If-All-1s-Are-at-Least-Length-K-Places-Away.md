---
layout: post
title: 1437. Check If All 1's Are at Least Length K Places Away
categories: [leetcode]
---
#### QUESTION:
Given an binary array nums and an integer k, return true if all 1's are at least k places away from each other, otherwise return false.

 

__Example 1:__
![](https://assets.leetcode.com/uploads/2020/04/15/sample_1_1791.png)
```
Input: nums = [1,0,0,0,1,0,0,1], k = 2
Output: true
Explanation: Each of the 1s are at least 2 places away from each other.
```
__Example 2:__
![](https://assets.leetcode.com/uploads/2020/04/15/sample_2_1791.png)
```
Input: nums = [1,0,0,1,0,1], k = 2
Output: false
Explanation: The second 1 and third 1 are only one apart from each other.
```
 

__Constraints:__
```
1 <= nums.length <= 105
0 <= k <= nums.length
nums[i] is 0 or 1
```
#### EXPLANATION:

easy的题目 , 首先用一个标记位来记录1的位置, for循环, 判断当前位置是不是1, 如果是1,那么就与前一个位置进行比较. 小于k说明不合适. 循环结束就说明都符合条件.

#### SOLUTION:
```kotlin
class Solution {
    fun kLengthApart(nums: IntArray, k: Int): Boolean {
        var pre:Int = -1
        for (i in nums.indices) {
            if (nums[i] == 1) {
                if (pre == -1) {
                    pre = i
                } else {
                    if (i - pre - 1 < k) {
                        return false
                    } else {
                        pre = i
                    }
                }
            }
        }
        return true
    }
}
```
