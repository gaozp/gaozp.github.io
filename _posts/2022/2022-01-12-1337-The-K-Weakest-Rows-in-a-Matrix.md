---
layout: post
title: 1337. The K Weakest Rows in a Matrix
categories: [leetcode]
---
#### QUESTION:
You are given an m x n binary matrix mat of 1's (representing soldiers) and 0's (representing civilians). The soldiers are positioned in front of the civilians. That is, all the 1's will appear to the left of all the 0's in each row.

A row i is weaker than a row j if one of the following is true:

The number of soldiers in row i is less than the number of soldiers in row j.
Both rows have the same number of soldiers and i < j.
Return the indices of the k weakest rows in the matrix ordered from weakest to strongest.

 

__Example 1:__
```
Input: mat = 
[[1,1,0,0,0],
 [1,1,1,1,0],
 [1,0,0,0,0],
 [1,1,0,0,0],
 [1,1,1,1,1]], 
k = 3
Output: [2,0,3]
Explanation: 
The number of soldiers in each row is: 
- Row 0: 2 
- Row 1: 4 
- Row 2: 1 
- Row 3: 2 
- Row 4: 5 
The rows ordered from weakest to strongest are [2,0,3,1,4].
```
__Example 2:__
```
Input: mat = 
[[1,0,0,0],
 [1,1,1,1],
 [1,0,0,0],
 [1,0,0,0]], 
k = 2
Output: [0,2]
Explanation: 
The number of soldiers in each row is: 
- Row 0: 1 
- Row 1: 4 
- Row 2: 1 
- Row 3: 1 
The rows ordered from weakest to strongest are [0,2,3,1].
``` 

__Constraints:__
```
m == mat.length
n == mat[i].length
2 <= n, m <= 100
1 <= k <= m
matrix[i][j] is either 0 or 1.
```
#### EXPLANATION:
这个题目其实还是挺容易的. 
1. 首先将每行的士兵和行号组成一组,放在array里.
2. 对array的士兵数进行排序, 这样,对应的index也会进行排序
3. 从小的开始往后数k个数 , 直接取index即可.

#### SOLUTION:
```swift
class Solution {
    func kWeakestRows(_ mat: [[Int]], _ k: Int) -> [Int] {
        var result:[Int] = []
        var sumArr:[[Int]] = []
        for index in mat.indices {
            let sum:Int = mat[index].reduce(0, { x , y in
                x + y
            })
            sumArr.append([sum,index])
        }
        sumArr.sort(by: {(num1, num2) in
            return num1[0] < num2[0]
        })
        for index in 1...k {
            result.append(sumArr[index-1][1])
        }
        return result
    }
}
```
