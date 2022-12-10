---
layout: post
title: 1984. Minimum Difference Between Highest and Lowest of K Scores
categories: [leetcode]
---
#### QUESTION:
You are given a 0-indexed integer array nums, where nums[i] represents the score of the ith student. You are also given an integer k.

Pick the scores of any k students from the array so that the difference between the highest and the lowest of the k scores is minimized.

Return the minimum possible difference.

 

__Example 1:__
```
Input: nums = [90], k = 1
Output: 0
Explanation: There is one way to pick score(s) of one student:
- [90]. The difference between the highest and lowest score is 90 - 90 = 0.
The minimum possible difference is 0.
```
__Example 2:__
```
Input: nums = [9,4,1,7], k = 2
Output: 2
Explanation: There are six ways to pick score(s) of two students:
- [9,4,1,7]. The difference between the highest and lowest score is 9 - 4 = 5.
- [9,4,1,7]. The difference between the highest and lowest score is 9 - 1 = 8.
- [9,4,1,7]. The difference between the highest and lowest score is 9 - 7 = 2.
- [9,4,1,7]. The difference between the highest and lowest score is 4 - 1 = 3.
- [9,4,1,7]. The difference between the highest and lowest score is 7 - 4 = 3.
- [9,4,1,7]. The difference between the highest and lowest score is 7 - 1 = 6.
The minimum possible difference is 2.
 ```

__Constraints:__
```
1 <= k <= nums.length <= 1000
0 <= nums[i] <= 105
```
#### EXPLANATION:

看题意便可知道, 要取k个数的最大最小, 那肯定就是需要进行排序的, 所以排序之后, 用window的想法去进行匹配即可.

#### SOLUTION:
```swift
class Solution {
    func minimumDifference(_ nums: [Int], _ k: Int) -> Int {
        let sorted = nums.sorted()
        var result = Int.max
        if sorted.count - k < 0 {
            return 0
        }
        for index in 0...sorted.count - k {
            result = min(sorted[index+k-1] - sorted[index] , result)
        }
        return result
    }
}
```
