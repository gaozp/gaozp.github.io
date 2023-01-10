---
layout: post
title: 2475. Number of Unequal Triplets in Array
categories: [leetcode]
---
#### QUESTION:
You are given a 0-indexed array of positive integers nums. Find the number of triplets (i, j, k) that meet the following conditions:

0 <= i < j < k < nums.length
nums[i], nums[j], and nums[k] are pairwise distinct.
In other words, nums[i] != nums[j], nums[i] != nums[k], and nums[j] != nums[k].
Return the number of triplets that meet the conditions.

 

__Example 1:__
```
Input: nums = [4,4,2,4,3]
Output: 3
Explanation: The following triplets meet the conditions:
- (0, 2, 4) because 4 != 2 != 3
- (1, 2, 4) because 4 != 2 != 3
- (2, 3, 4) because 2 != 4 != 3
Since there are 3 triplets, we return 3.
Note that (2, 0, 4) is not a valid triplet because 2 > 0.
```
__Example 2:__
```
Input: nums = [1,1,1,1,1]
Output: 0
Explanation: No triplets meet the conditions so we return 0.
```
 

__Constraints:__
```
3 <= nums.length <= 100
1 <= nums[i] <= 1000
```
#### EXPLANATION:

直接一个for循环即可.

#### SOLUTION:
```swift
class Solution {
    func unequalTriplets(_ nums: [Int]) -> Int {
        var result = 0
        for i in 0...nums.count - 3 {
            for j in i+1...nums.count - 2 {
                for k in j+1...nums.count - 1 {
                    if nums[i] != nums[j] && nums[j] != nums[k] && nums[i] != nums[k] {
                        result += 1
                    }
                }
            }
        }
        return result
    }
}
```
