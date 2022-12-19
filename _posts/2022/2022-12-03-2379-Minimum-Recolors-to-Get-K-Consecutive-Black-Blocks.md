---
layout: post
title: 2379. Minimum Recolors to Get K Consecutive Black Blocks
categories: [leetcode]
---
#### QUESTION:
You are given a 0-indexed string blocks of length n, where blocks[i] is either 'W' or 'B', representing the color of the ith block. The characters 'W' and 'B' denote the colors white and black, respectively.

You are also given an integer k, which is the desired number of consecutive black blocks.

In one operation, you can recolor a white block such that it becomes a black block.

Return the minimum number of operations needed such that there is at least one occurrence of k consecutive black blocks.

 

__Example 1:__
```
Input: blocks = "WBBWWBBWBW", k = 7
Output: 3
Explanation:
One way to achieve 7 consecutive black blocks is to recolor the 0th, 3rd, and 4th blocks
so that blocks = "BBBBBBBWBW". 
It can be shown that there is no way to achieve 7 consecutive black blocks in less than 3 operations.
Therefore, we return 3.
```
__Example 2:__
```
Input: blocks = "WBWBBBW", k = 2
Output: 0
Explanation:
No changes need to be made, since 2 consecutive black blocks already exist.
Therefore, we return 0.
```
 

__Constraints:__
```
n == blocks.length
1 <= n <= 100
blocks[i] is either 'W' or 'B'.
1 <= k <= n
```
#### EXPLANATION:

使用windows的方式, 使用for循环, 这样每次都可以用k的window去进行比对, 拿到最小的k就行.

#### SOLUTION:
```swift
class Solution {
    func minimumRecolors(_ blocks: String, _ k: Int) -> Int {
        var result = Int.max
        let arr = Array(blocks)
        for indexI in 0..<blocks.count - k + 1 {
            var tmpCount = 0
            for indexJ in indexI..<indexI + k {
                if (arr[indexJ] == "W") {
                    tmpCount += 1
                }
            }
            result = min(result, tmpCount)
        }
        return result
    }
}
```