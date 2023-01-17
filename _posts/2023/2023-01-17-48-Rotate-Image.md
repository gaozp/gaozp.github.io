---
layout: post
title: 48. Rotate Image
categories: [leetcode]
---
#### QUESTION:
You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).

You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

 

__Example 1:__
![](https://assets.leetcode.com/uploads/2020/08/28/mat1.jpg)
```
Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [[7,4,1],[8,5,2],[9,6,3]]
```
__Example 2:__
![](https://assets.leetcode.com/uploads/2020/08/28/mat2.jpg)
```
Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
```
 

__Constraints:__
```
n == matrix.length == matrix[i].length
1 <= n <= 20
-1000 <= matrix[i][j] <= 1000
```
#### EXPLANATION:

旋转90度即可. 比较好的办法是, 首先将对角线对称进行交换, 再将每行进行reverse即可.

#### SOLUTION:
```swift
class Solution {
    func rotate(_ mat: inout [[Int]]) {
        var result: [[Int]] = []
        for indexI in mat.indices {
            var tmpArr: [Int] = []
            for indexJ in mat[indexI].indices {
                tmpArr.append(mat[indexJ][indexI])
            }
            result.append(tmpArr.reversed())
        }
        mat = result
    }
}
```
