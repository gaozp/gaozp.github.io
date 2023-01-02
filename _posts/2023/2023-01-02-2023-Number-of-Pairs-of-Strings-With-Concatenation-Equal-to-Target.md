---
layout: post
title: 2023. Number of Pairs of Strings With Concatenation Equal to Target
categories: [leetcode]
---
#### QUESTION:
Given an array of digit strings nums and a digit string target, return the number of pairs of indices (i, j) (where i != j) such that the concatenation of nums[i] + nums[j] equals target.

 

__Example 1:__
```
Input: nums = ["777","7","77","77"], target = "7777"
Output: 4
Explanation: Valid pairs are:
- (0, 1): "777" + "7"
- (1, 0): "7" + "777"
- (2, 3): "77" + "77"
- (3, 2): "77" + "77"
```
__Example 2:__
```
Input: nums = ["123","4","12","34"], target = "1234"
Output: 2
Explanation: Valid pairs are:
- (0, 1): "123" + "4"
- (2, 3): "12" + "34"
```
__Example 3:__
```
Input: nums = ["1","1","1"], target = "11"
Output: 6
Explanation: Valid pairs are:
- (0, 1): "1" + "1"
- (1, 0): "1" + "1"
- (0, 2): "1" + "1"
- (2, 0): "1" + "1"
- (1, 2): "1" + "1"
- (2, 1): "1" + "1"
```
 

__Constraints:__
```
2 <= nums.length <= 100
1 <= nums[i].length <= 100
2 <= target.length <= 100
nums[i] and target consist of digits.
nums[i] and target do not have leading zeros.
```
#### EXPLANATION:

比较简单, 两个for循环就可以搞定了. 在中间加一个判断即可.

#### SOLUTION:
```swift
class Solution {
    func numOfPairs(_ nums: [String], _ target: String) -> Int {
        var result = 0
        for indexI in nums.indices {
            for indexJ in nums.indices {
                if indexI != indexJ {
                    if nums[indexI].count + nums[indexJ].count == target.count
                        && nums[indexI] + nums[indexJ] == target {
                        result += 1
                    }
                }
            }
        }
        return result
    }
}
```
