---
layout: post
title: 2032. Two Out of Three
categories: [leetcode]
---
#### QUESTION:
Given three integer arrays nums1, nums2, and nums3, return a distinct array containing all the values that are present in at least two out of the three arrays. You may return the values in any order.
 

__Example 1:__
```
Input: nums1 = [1,1,3,2], nums2 = [2,3], nums3 = [3]
Output: [3,2]
Explanation: The values that are present in at least two arrays are:
- 3, in all three arrays.
- 2, in nums1 and nums2.
```
__Example 2:__
```
Input: nums1 = [3,1], nums2 = [2,3], nums3 = [1,2]
Output: [2,3,1]
Explanation: The values that are present in at least two arrays are:
- 2, in nums2 and nums3.
- 3, in nums1 and nums2.
- 1, in nums1 and nums3.
```
__Example 3:__
```
Input: nums1 = [1,2,2], nums2 = [4,3,3], nums3 = [5]
Output: []
Explanation: No value is present in at least two arrays.
```

__Constraints:__
```
1 <= nums1.length, nums2.length, nums3.length <= 100
1 <= nums1[i], nums2[j], nums3[k] <= 100
```
#### EXPLANATION:

easy的题目, 用一个数组来标注某个数字出现了几次. 用位运算的方式来标记对应的出现次数.最后再判断bitcount就可以.

#### SOLUTION:
```swift
class Solution {
    func twoOutOfThree(_ nums1: [Int], _ nums2: [Int], _ nums3: [Int]) -> [Int] {
        var arr:[Int] = Array(repeating: 0, count: 101)
        var tmp:Int = 1
        for numArray in [nums1,nums2,nums3] {
            for num in numArray {
                arr[num] = arr[num] | tmp
            }
            tmp = tmp << 1
        }
        var result:[Int] = []
        for index in arr.indices {
            if arr[index].nonzeroBitCount >= 2 {
                result.append(index)
            }
        }
        return result
    }
}
```
