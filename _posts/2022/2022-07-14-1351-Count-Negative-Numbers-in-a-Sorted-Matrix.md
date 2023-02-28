---
layout: post
title: 1351. Count Negative Numbers in a Sorted Matrix
categories: [leetcode]
---
#### QUESTION:
Given a m x n matrix grid which is sorted in non-increasing order both row-wise and column-wise, return the number of negative numbers in grid.

 

__Example 1:__
```
Input: grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
Output: 8
Explanation: There are 8 negatives number in the matrix.
```
__Example 2:__
```
Input: grid = [[3,2],[1,0]]
Output: 0
```
 

__Constraints:__
```
m == grid.length
n == grid[i].length
1 <= m, n <= 100
-100 <= grid[i][j] <= 100
```
#### EXPLANATION:

easy的题目，for循环嵌套遍历就可以

#### SOLUTION:
```swift
class Solution {
    func countNegatives(_ grid: [[Int]]) -> Int {
        var result:Int = 0
        for gri in grid {
            for gr in gri {
                if gr < 0 {
                    result += 1
                }
            }
        }
        return result
    }
}
```
