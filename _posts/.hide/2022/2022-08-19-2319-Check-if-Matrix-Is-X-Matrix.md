---
layout: post
title: 2319. Check if Matrix Is X-Matrix
categories: [leetcode]
---
#### QUESTION:
A square matrix is said to be an X-Matrix if both of the following conditions hold:

All the elements in the diagonals of the matrix are non-zero.
All other elements are 0.
Given a 2D integer array grid of size n x n representing a square matrix, return true if grid is an X-Matrix. Otherwise, return false.

 

__Example 1:__
![](https://assets.leetcode.com/uploads/2022/05/03/ex1.jpg)
```
Input: grid = [[2,0,0,1],[0,3,1,0],[0,5,2,0],[4,0,0,2]]
Output: true
Explanation: Refer to the diagram above. 
An X-Matrix should have the green elements (diagonals) be non-zero and the red elements be 0.
Thus, grid is an X-Matrix.
```
__Example 2:__
![](https://assets.leetcode.com/uploads/2022/05/03/ex2.jpg)
```
Input: grid = [[5,7,0],[0,3,1],[0,5,0]]
Output: false
Explanation: Refer to the diagram above.
An X-Matrix should have the green elements (diagonals) be non-zero and the red elements be 0.
Thus, grid is not an X-Matrix.
```
 

__Constraints:__
```
n == grid.length == grid[i].length
3 <= n <= 100
0 <= grid[i][j] <= 105
```
#### EXPLANATION:

其实主要的就是对角线的算法. 一个就是 indexi = indexj 另外一条就是 indexj = grid.count-1-indexi. 

#### SOLUTION:
```swift
class Solution {
    func checkXMatrix(_ grid: [[Int]]) -> Bool {
        var result:Bool = true
        for indexI in grid.indices {
            for indexJ in grid[indexI].indices {
                if (indexI == indexJ || indexJ == grid.count - 1 - indexI) {
                    if (grid[indexI][indexJ] == 0) {
                        return false
                    }
                } else {
                    if (grid[indexI][indexJ] != 0) {
                        return false
                    }
                }
            }
        }
        return result
    }
}
```
