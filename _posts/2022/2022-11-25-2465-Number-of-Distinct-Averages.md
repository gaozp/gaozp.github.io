---
layout: post
title: 2465. Number of Distinct Averages
categories: [leetcode]
---
#### QUESTION:
You are given a 0-indexed integer array nums of even length.

As long as nums is not empty, you must repetitively:

Find the minimum number in nums and remove it.
Find the maximum number in nums and remove it.
Calculate the average of the two removed numbers.
The average of two numbers a and b is (a + b) / 2.

For example, the average of 2 and 3 is (2 + 3) / 2 = 2.5.
Return the number of distinct averages calculated using the above process.

Note that when there is a tie for a minimum or maximum number, any can be removed.

 

__Example 1:__
```
Input: nums = [4,1,4,0,3,5]
Output: 2
Explanation:
1. Remove 0 and 5, and the average is (0 + 5) / 2 = 2.5. Now, nums = [4,1,4,3].
2. Remove 1 and 4. The average is (1 + 4) / 2 = 2.5, and nums = [4,3].
3. Remove 3 and 4, and the average is (3 + 4) / 2 = 3.5.
Since there are 2 distinct numbers among 2.5, 2.5, and 3.5, we return 2.
```
__Example 2:__
```
Input: nums = [1,100]
Output: 1
Explanation:
There is only one average to be calculated after removing 1 and 100, so we return 1.
```
 

__Constraints:__
```
2 <= nums.length <= 100
nums.length is even.
0 <= nums[i] <= 100
```
#### EXPLANATION:

easy的题目, 因为需要remove其中的数字, 但是数组remove操作其实不太好, 所以改变思路, 不用remove. 先进行排序, 这样每次都是取最前和最后的, 那么就可以一个for循环搞定了. 用set来装结果, 这样自然就是单独的.

#### SOLUTION:
```swift
class Solution {
    func distinctAverages(_ nums: [Int]) -> Int {
        var nums = nums.sorted()
        var set:Set<Double> = Set()
        for index in 0..<nums.count/2 {
            set.insert((Double)(nums[index] + nums[nums.count - 1 - index])/2)
        }
        return set.count
    }
}
```
