---
layout: post
title: 1886. Determine Whether Matrix Can Be Obtained By Rotation
categories: [leetcode]
---
#### QUESTION:
Given two n x n binary matrices mat and target, return true if it is possible to make mat equal to target by rotating mat in 90-degree increments, or false otherwise.

 

__Example 1:__
![](https://assets.leetcode.com/uploads/2021/05/20/grid3.png)
```
Input: mat = [[0,1],[1,0]], target = [[1,0],[0,1]]
Output: true
Explanation: We can rotate mat 90 degrees clockwise to make mat equal target.
```
__Example 2:__
![](https://assets.leetcode.com/uploads/2021/05/20/grid4.png)
```
Input: mat = [[0,1],[1,1]], target = [[1,0],[0,1]]
Output: false
Explanation: It is impossible to make mat equal to target by rotating mat.
```
__Example 3:__
![](https://assets.leetcode.com/uploads/2021/05/26/grid4.png)
```
Input: mat = [[0,0,0],[0,1,0],[1,1,1]], target = [[1,1,1],[0,1,0],[0,0,0]]
Output: true
Explanation: We can rotate mat 90 degrees clockwise two times to make mat equal target.
```
 

__Constraints:__
```
n == mat.length == target.length
n == mat[i].length == target[i].length
1 <= n <= 10
mat[i][j] and target[i][j] are either 0 or 1.
```
#### EXPLANATION:

通过观察我们可以看到规律, 也就是将每一竖排反向后再横着就是最终的旋转90度. 那么就很容易了, 只需要旋转4次即可.每次判断是否相同即可完成.

#### SOLUTION:
```swift
class Solution {
    func findRotation(_ mat: [[Int]], _ target: [[Int]]) -> Bool {
        var mat = mat
        for _ in 1...5 {
            if mat == target {
                return true
            }
            mat = rotation90(mat)
        }
        return false
    }

    func rotation90(_ mat: [[Int]]) -> [[Int]] {
        var result: [[Int]] = []
        for indexI in mat.indices {
            var tmpArr: [Int] = []
            for indexJ in mat[indexI].indices {
                tmpArr.append(mat[indexJ][indexI])
            }
            result.append(tmpArr.reversed())
        }
        return result
    }
}
```
