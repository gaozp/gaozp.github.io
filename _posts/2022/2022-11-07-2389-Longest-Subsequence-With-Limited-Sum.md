---
layout: post
title: 2389. Longest Subsequence With Limited Sum
categories: [leetcode]
---
#### QUESTION:
You are given an integer array nums of length n, and an integer array queries of length m.

Return an array answer of length m where answer[i] is the maximum size of a subsequence that you can take from nums such that the sum of its elements is less than or equal to queries[i].

A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.

 

__Example 1:__
```
Input: nums = [4,5,2,1], queries = [3,10,21]
Output: [2,3,4]
Explanation: We answer the queries as follows:
- The subsequence [2,1] has a sum less than or equal to 3. It can be proven that 2 is the maximum size of such a subsequence, so answer[0] = 2.
- The subsequence [4,5,1] has a sum less than or equal to 10. It can be proven that 3 is the maximum size of such a subsequence, so answer[1] = 3.
- The subsequence [4,5,2,1] has a sum less than or equal to 21. It can be proven that 4 is the maximum size of such a subsequence, so answer[2] = 4.
```
__Example 2:__
```
Input: nums = [2,3,4,5], queries = [1]
Output: [0]
Explanation: The empty subsequence is the only subsequence that has a sum less than or equal to 1, so answer[0] = 0.
```
#### EXPLANATION:

采用贪心算法, 因为要求最大数量, 那么每次减少最大的数即可.   
1. 我们先求出所有和.  
2. 减去当前最大的数和query进行比较
3. 如果当前已经比query小, 那么就把index减去, 就是剩余的数量. 
4. 否则就继续进行下一个减

#### SOLUTION:
```swift
class Solution {
    func answerQueries(_ nums: [Int], _ queries: [Int]) -> [Int] {
        var result:[Int] = []
        var sum = 0
        nums.forEach { num in
            sum += num
        }
        var sorted:[Int] = nums.sorted().reversed()
        for query in queries {
            var tmp = sum
            if (tmp <= query) {
                result.append(nums.count)
                continue
            }
            for index in 0...nums.count-1 {
                tmp -= sorted[index]
                if (tmp <= query) {
                    result.append(nums.count - index - 1)
                    break
                }
            }
        }
        return result
    }
}
```
