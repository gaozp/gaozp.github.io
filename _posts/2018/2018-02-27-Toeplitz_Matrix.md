---
layout: post
title: 766. Toeplitz Matrix
categories: [leetcode]
---

#### QUESTION:

A matrix is *Toeplitz* if every diagonal from top-left to bottom-right has the same element.

Now given an `M x N` matrix, return `True` if and only if the matrix is *Toeplitz*.

**Example 1:**

```
Input: matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
Output: True
Explanation:
1234
5123
9512

In the above grid, the diagonals are "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]", and in each diagonal all elements are the same, so the answer is True.

```

**Example 2:**

```
Input: matrix = [[1,2],[2,2]]
Output: False
Explanation:
The diagonal "[1, 2]" has different elements.

```

**Note:**

1. `matrix` will be a 2D array of integers.
2. `matrix` will have a number of rows and columns in range `[1, 20]`.
3. `matrix[i][j]` will be integers in range `[0, 99]`.

#### EXPLANATION:

看到这个题目的想法就是按照顺序来呗，分成横向和竖向两种。

1.首先处理横向的情况：横向的时候就进行遍历操作，对j进行遍历，然后进行判断，这样就可以了。

2.进行竖向操作的时候，只需要进行一次斜的遍历，这样就可以避免重复操作了。

如图：

第一步：

x,x,x,x,x,x

o,x,x,x,x,x

o,o,x,x,x,x

o,o,o,x,x,x

o,o,o,o,x,x

o,o,o,o,o,x

第二步：

x,x,x,x,x,x

x,x,x,x,x,x

o,x,x,x,x,x

o,o,x,x,x,x

o,o,o,x,x,x

o,o,o,o,x,x

第三步：

x,x,x,x,x,x

x,x,x,x,x,x

x,x,x,x,x,x

o,x,x,x,x,x

o,o,x,x,x,x

o,o,o,x,x,x

#### SOLUTION:

```java
class Solution {
    public boolean isToeplitzMatrix(int[][] matrix) {
        for(int i = 0;i<matrix.length;i++){
            if(i==0){
                for(int j = 0;j<matrix[i].length;j++){
                    int m = 0;int n=j;
                    int tmp = matrix[m][n];
                    while (m<matrix.length&&n<matrix[m].length){
                        if(matrix[m][n]!=tmp) return false;
                        m++;n++;
                    }
                }
            }else{
                int m = i;int n=0;
                int tmp = matrix[m][n];
                while (m<matrix.length&&n<matrix[m].length){
                    if(matrix[m][n]!=tmp) return false;
                    m++;n++;
                }
            }
        }
        return true;
    }
}
```

