---
layout: post
title: 3190. Find Minimum Operations to Make All Elements Divisible by Three
categories: [leetcode]
---
#### QUESTION:
You are given an integer array nums. In one operation, you can add or subtract 1 from any element of nums.

Return the minimum number of operations to make all elements of nums divisible by 3.

 

__Example 1:__
```
Input: nums = [1,2,3,4]

Output: 3

Explanation:

All array elements can be made divisible by 3 using 3 operations:

Subtract 1 from 1.
Add 1 to 2.
Subtract 1 from 4.
```
__Example 2:__
```
Input: nums = [3,6,9]

Output: 0
```

 

__Constraints:__
```
1 <= nums.length <= 50
1 <= nums[i] <= 50
```
#### EXPLANATION:

能够被3整除. 我们可以发现规律: 0,1,2,3,4,5,6. 0正好可以, 1只需要-1, 2只需要+1, 3正好可以, 4只需要-1, 5只需要+1, 6正好可以. 我们可以发现, 如果一个数不能被三整除, 那么只需要1步改变就可以. 所以我们就可以得到:  
__if num % 3 != 0 { result += 1 }__  
这样就算是解决了. 

#### SOLUTION:
```swift
class Solution {
    func minimumOperations(_ nums: [Int]) -> Int {
        var result: Int = 0
        for num in nums {
            if num % 3 != 0 {
                result += 1
            }
        }
        return result
    }
}
```
