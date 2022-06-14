---
layout: post
title: 1572. Matrix Diagonal Sum
categories: [leetcode]
---
#### QUESTION:
Given a square matrix mat, return the sum of the matrix diagonals.

Only include the sum of all the elements on the primary diagonal and all the elements on the secondary diagonal that are not part of the primary diagonal.

 

__Example 1:__
![](https://assets.leetcode.com/uploads/2020/08/14/sample_1911.png)
```
Input: mat = [[1,2,3],
              [4,5,6],
              [7,8,9]]
Output: 25
Explanation: Diagonals sum: 1 + 5 + 9 + 3 + 7 = 25
Notice that element mat[1][1] = 5 is counted only once.
```
__Example 2:__
```
Input: mat = [[1,1,1,1],
              [1,1,1,1],
              [1,1,1,1],
              [1,1,1,1]]
Output: 8
```
Example 3:
```
Input: mat = [[5]]
Output: 5
 ```

__Constraints:__
```
n == mat.length == mat[i].length
1 <= n <= 100
1 <= mat[i][j] <= 100
```
#### EXPLANATION:

按照题目的要求, 先加左上往右下的这一个斜. 再加上右上往左下的这一个列. 然后发现只有奇数的情况下, 需要减掉中间的数, 因为已经加了两次.   
当然也有其他办法, 就是每一行的索引, 前后两个都是减去i, 所以可以一个for循环进行搞定.

#### SOLUTION:
```swift
class Solution {
    func diagonalSum(_ mat: [[Int]]) -> Int {
        var indexI:Int = 0
        var indexJ:Int = 0
        var result:Int = 0
        while indexI < mat.count && indexJ < mat.count {
            result += mat[indexI][indexJ]
            indexI += 1
            indexJ += 1
        }
        indexI = 0
        indexJ = mat.count - 1
        while indexI < mat.count && indexJ >= 0 {
            result += mat[indexI][indexJ]
            indexI += 1
            indexJ -= 1
        }
        if mat.count % 2 == 1 {
            result -= mat[mat.count/2][mat.count/2]
        }
        return result
    }
}
```
