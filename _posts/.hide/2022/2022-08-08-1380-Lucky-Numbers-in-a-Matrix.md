---
layout: post
title: 1380. Lucky Numbers in a Matrix
categories: [leetcode]
---
#### QUESTION:
Given an m x n matrix of distinct numbers, return all lucky numbers in the matrix in any order.

A lucky number is an element of the matrix such that it is the minimum element in its row and maximum in its column.

 

__Example 1:__
```
Input: matrix = [[3,7,8],[9,11,13],[15,16,17]]
Output: [15]
Explanation: 15 is the only lucky number since it is the minimum in its row and the maximum in its column.
```
__Example 2:__
```
Input: matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]]
Output: [12]
Explanation: 12 is the only lucky number since it is the minimum in its row and the maximum in its column.
```
__Example 3:__
```
Input: matrix = [[7,8],[1,2]]
Output: [7]
Explanation: 7 is the only lucky number since it is the minimum in its row and the maximum in its column.
```
 

__Constraints:__
```
m == mat.length
n == mat[i].length
1 <= n, m <= 50
1 <= matrix[i][j] <= 105.
All elements in the matrix are distinct.
```
#### EXPLANATION:

easy的题目, 首先算出每行最小的, 在最小的列中查看是否是最大的.

#### SOLUTION:
```swift
class Solution {
    func luckyNumbers (_ matrix: [[Int]]) -> [Int] {
        var result:[Int] = []
        for indexi in matrix.indices {
            var colum:Int = 0
            var tmp:Int = matrix[indexi][colum]
            for indexj in matrix[indexi].indices {
                if matrix[indexi][indexj] < tmp {
                    tmp = matrix[indexi][indexj]
                    colum = indexj
                }
            }
            tmp = matrix[0][colum]
            var targetRow:Int = 0
            for rowJ in matrix.indices {
                if matrix[rowJ][colum] > tmp {
                    tmp = matrix[rowJ][colum]
                    targetRow = rowJ
                }
            }
            if targetRow == indexi {
                result.append(matrix[indexi][colum])
            }
        }
        return result
    }
}

```
