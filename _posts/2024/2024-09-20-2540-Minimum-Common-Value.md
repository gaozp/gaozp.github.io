---
layout: post
title: 2540. Minimum Common Value
categories: [leetcode]
---
#### QUESTION:
Given two integer arrays nums1 and nums2, sorted in non-decreasing order, return the minimum integer common to both arrays. If there is no common integer amongst nums1 and nums2, return -1.

Note that an integer is said to be common to nums1 and nums2 if both arrays have at least one occurrence of that integer.

 

__Example 1:__
```
Input: nums1 = [1,2,3], nums2 = [2,4]
Output: 2
Explanation: The smallest element common to both arrays is 2, so we return 2.
```
__Example 2:__
```
Input: nums1 = [1,2,3,6], nums2 = [2,3,4,5]
Output: 2
Explanation: There are two common elements in the array 2 and 3 out of which 2 is the smallest, so 2 is returned.
```

__Constraints:__
```
1 <= nums1.length, nums2.length <= 105
1 <= nums1[i], nums2[j] <= 109
Both nums1 and nums2 are sorted in non-decreasing order.
```
#### EXPLANATION:

思路:  
用双指针的方式分别往前走, 如果遇到两个相同的情况就返回, 否则将小的那个继续往前. 

#### SOLUTION:
```swift
class Solution {
    func getCommon(_ nums1: [Int], _ nums2: [Int]) -> Int {
        var i: Int = 0
        var j: Int = 0
        while i < nums1.count && j < nums2.count {
            if nums1[i] == nums2[j] {
                return nums1[i]
            } else if nums1[i] < nums2[j] {
                i += 1
            } else {
                j += 1
            }
        }
        return -1
    }
}
```
