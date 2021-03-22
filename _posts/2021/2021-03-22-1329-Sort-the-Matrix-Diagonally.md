---
layout: post
title: 1329. Sort the Matrix Diagonally
categories: [leetcode]
---
#### QUESTION:
A matrix diagonal is a diagonal line of cells starting from some cell in either the topmost row or leftmost column and going in the bottom-right direction until reaching the matrix's end. For example, the matrix diagonal starting from mat[2][0], where mat is a 6 x 3 matrix, includes cells mat[2][0], mat[3][1], and mat[4][2].

Given an m x n matrix mat of integers, sort each matrix diagonal in ascending order and return the resulting matrix.

 

__Example 1:__
![](https://assets.leetcode.com/uploads/2020/01/21/1482_example_1_2.png)
```
Input: mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
Output: [[1,1,1,1],[1,2,2,2],[1,2,3,3]]
```
__Example 2:__
```
Input: mat = [[11,25,66,1,69,7],[23,55,17,45,15,52],[75,31,36,44,58,8],[22,27,33,25,68,4],[84,28,14,11,5,50]]
Output: [[5,17,4,1,52,7],[11,11,25,45,8,69],[14,23,25,44,58,15],[22,27,31,36,50,66],[84,28,75,33,55,68]]
```
#### EXPLANATION:
这道题目简单的逻辑就是:  
1. 斜着取出数字
2. 将数字排序
3. 再斜着放入  

那么第一步斜着取出数字可以再拆分: 
1. m就是列, n是行. 确保两者的安全边界
2. m++,n++ 取出所有数字

排序就不用说了  
再按照同样的顺序放入mat中即可.

#### SOLUTION:
```java
class Solution {
    fun diagonalSort(mat: Array<IntArray>): Array<IntArray> {
        for (i in mat[0].size-1 downTo 0) {
        var tmp = ArrayList<Int>()
        var m = i
        var n = 0
        while (m <= mat[0].size-1 && n<=mat.size-1) {
            tmp.add(mat[n][m])
            m++
            n++
        }
        Collections.sort(tmp)
        var index = 0
        n = 0
        m = i
        while (m <=mat[0].size-1 && n<=mat.size-1) {
            mat[n][m] = tmp.get(index)
            index++
            m++
            n++
        }
    }
    for (i in 0..mat.size-1) {
        var tmp = ArrayList<Int>()
        var m = 0
        var n = i
        while (m<=mat[0].size-1 && n<=mat.size-1) {
            tmp.add(mat[n][m])
            m++
            n++
        }

        Collections.sort(tmp)
        var index = 0
        m = 0
        n = i
        while (m <=mat[0].size-1 && n<=mat.size-1) {
            mat[n][m] = tmp.get(index)
            index++
            m++
            n++
        }
    }
    return mat
    }
}
```
