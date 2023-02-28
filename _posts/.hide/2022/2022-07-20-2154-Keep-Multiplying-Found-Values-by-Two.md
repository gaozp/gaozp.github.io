---
layout: post
title: 2154. Keep Multiplying Found Values by Two
categories: [leetcode]
---
#### QUESTION:
You are given an array of integers nums. You are also given an integer original which is the first number that needs to be searched for in nums.

You then do the following steps:

If original is found in nums, multiply it by two (i.e., set original = 2 * original).
Otherwise, stop the process.
Repeat this process with the new number as long as you keep finding the number.
Return the final value of original.

 

__Example 1:__
```
Input: nums = [5,3,6,1,12], original = 3
Output: 24
Explanation: 
- 3 is found in nums. 3 is multiplied by 2 to obtain 6.
- 6 is found in nums. 6 is multiplied by 2 to obtain 12.
- 12 is found in nums. 12 is multiplied by 2 to obtain 24.
- 24 is not found in nums. Thus, 24 is returned.
```
__Example 2:__
```
Input: nums = [2,7,9], original = 4
Output: 4
Explanation:
- 4 is not found in nums. Thus, 4 is returned.
```
 

__Constraints:__
```
1 <= nums.length <= 1000
1 <= nums[i], original <= 1000
```
#### EXPLANATION:

由于origin是一个正数,那么我们可以得到original\*2也一定比original大, 那么我们完全可以先排序 , 排序后的original\*2肯定在当前的后面. 然后再一个for循环将结果求出即可.

#### SOLUTION:
```swift
class Solution {
    func findFinalValue(_ nums: [Int], _ original: Int) -> Int {
        var arr:[Int] = nums.sorted()
        var result:Int = original
        for a in arr {
            if (a == result) {
                result = result * 2
            }
        }
        return result
    }
}
```
