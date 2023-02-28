---
layout: post
title: 221. Maximal Square
categories: [leetcode]
---
#### QUESTION:
Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

 

__Example 1:__
![](https://assets.leetcode.com/uploads/2020/11/26/max1grid.jpg)

Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
Output: 4
__Example 2:__
![](https://assets.leetcode.com/uploads/2020/11/26/max2grid.jpg)

Input: matrix = [["0","1"],["1","0"]]
Output: 1
__Example 3:__

Input: matrix = [["0"]]
Output: 0
 

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 300
matrix[i][j] is '0' or '1'.
#### EXPLANATION:
这个题目就有点意思了. 思路我先写一下: 
1. 找出最大可能的框maxlengh, 也就是 max(m,n)
2. 那既然框知道了, 那我们就可以将框从1-maxlength的框在matrix上移动
3. 移动到一个位置后, 需要判断当前框内的所有位置是否是1
4. 这样循环一遍后, 就能得到当前框是否可行.
5. 再将框扩大1, 再从头开始移动, 直到最后框的结束
#### SOLUTION:
```swift
class Solution {
    func maximalSquare(_ matrix: [[Character]]) -> Int {
        var m:Int = matrix.count
        var n:Int = matrix[0].count
        var maxLength:Int = m > n ? n : m
        var result:Int = 0
        for length in 1...maxLength {
            for anchorX in 0...matrix[0].count-length {
                for anchorY in 0...matrix.count-length {
                    var indexMaxX = anchorX + length <= matrix[0].count ? anchorX + length - 1 : matrix[0].count - 1
                    var indexMaxY = anchorY + length <= matrix.count ? anchorY + length - 1 : matrix.count - 1
                    var tmpResult:Bool = true
                    w: for indexX in anchorX...indexMaxX {
                        for indexY in anchorY...indexMaxY {
                            if matrix[indexY][indexX] == "0" {
                                tmpResult = false
                                break w
                            }
                        }
                    }
                    if tmpResult {
                        result = result < length ? length : result
                    }
                }
            }
        }
        return result * result
    }
}
```
