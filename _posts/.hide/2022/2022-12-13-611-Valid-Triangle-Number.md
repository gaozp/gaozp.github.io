---
layout: post
title: 611. Valid Triangle Number
categories: [leetcode]
---
#### QUESTION:
Given an integer array nums, return the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.

 

__Example 1:__
```
Input: nums = [2,2,3,4]
Output: 3
Explanation: Valid combinations are: 
2,3,4 (using the first 2)
2,3,4 (using the second 2)
2,2,3
```
__Example 2:__
```
Input: nums = [4,2,3,4]
Output: 4
```
 

__Constraints:__
```
1 <= nums.length <= 1000
0 <= nums[i] <= 1000
```
#### EXPLANATION:

思路也就比较简单, 三个for循环进行判断即可, 但是中间有tle, 所以需要把对应的过程简化一下, 也就是先排序, 如果两边完全小于第三边了, 后面的就没有必要再循环了.

#### SOLUTION:
```swift
class Solution {
    func triangleNumber(_ nums: [Int]) -> Int {
        if nums.count < 3 {
            return 0
        }
        var result = 0
        var nums = nums.sorted()
        for indexI in 0...nums.count-3 {
            for indexJ in indexI+1...nums.count-2 {
                for indexM in indexJ+1...nums.count-1 {
                    if (nums[indexI]+nums[indexJ]<nums[indexM]) {
                        break
                    }
                    if ((nums[indexI]+nums[indexJ] > nums[indexM] && abs(nums[indexI]-nums[indexJ])<nums[indexM])
                        || (nums[indexI]+nums[indexM] > nums[indexJ] && abs(nums[indexI]-nums[indexM])<nums[indexJ])
                        || (nums[indexM]+nums[indexJ] > nums[indexI] && abs(nums[indexM]-nums[indexJ])<nums[indexI])) {
                        result+=1
                    }
                    
                }
            }
        }
        return result
    }
}
```
