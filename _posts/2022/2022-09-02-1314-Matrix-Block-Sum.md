---
layout: post
title: 1314. Matrix Block Sum
categories: [leetcode]
---
#### QUESTION:
Given a m x n matrix mat and an integer k, return a matrix answer where each answer[i][j] is the sum of all elements mat[r][c] for:

i - k <= r <= i + k,
j - k <= c <= j + k, and
(r, c) is a valid position in the matrix.
 

__Example 1:__
```
Input: mat = [[1,2,3],[4,5,6],[7,8,9]], k = 1
Output: [[12,21,16],[27,45,33],[24,39,28]]
```
__Example 2:__
```
Input: mat = [[1,2,3],[4,5,6],[7,8,9]], k = 2
Output: [[45,45,45],[45,45,45],[45,45,45]]
```
 

__Constraints:__
```
m == mat.length
n == mat[i].length
1 <= m, n, k <= 100
1 <= mat[i][j] <= 100
```
#### EXPLANATION:

比较简单, 其实就是把每个数的周围K个数的和即可, 那么可以拆分成两步: 取出当前的数, 再把对应的K方框的和求出来, 用一个helper函数来计算, 那么久转化成了对应方块的合理范围.

#### SOLUTION:
```swift
class Solution {
    func matrixBlockSum(_ mat: [[Int]], _ k: Int) -> [[Int]] {
        var result:[[Int]] = Array(repeating: Array(repeating: 0, count: mat[0].count), count: mat.count)
        for indexI in mat.indices {
            for indexJ in mat[indexI].indices {
                result[indexI][indexJ] = matrixBlockSumHelper(mat, indexI, indexJ, k)
            }
        }
        return result
    }
    
    func matrixBlockSumHelper(_ mat: [[Int]],_ i: Int, _ j: Int, _ k:Int) -> Int {
        var sum:Int = 0
        var startI:Int = i - k >= 0 ? i - k : 0
        var endI:Int = i + k <= mat.count - 1 ? i + k : mat.count - 1
        var startJ:Int = j - k >= 0 ? j - k : 0
        var endJ:Int = j + k <= mat[i].count - 1 ? j + k : mat[i].count - 1
        for indexI in startI...endI {
            for indexJ in startJ...endJ {
                sum += mat[indexI][indexJ]
            }
        }
        return sum
    }
}
```
