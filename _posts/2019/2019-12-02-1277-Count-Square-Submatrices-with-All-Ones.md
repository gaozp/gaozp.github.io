---
layout: post
title: 1277. Count Square Submatrices with All Ones
categories: [leetcode]
---
#### QUESTION:
Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.

 

Example 1:

Input: matrix =
[
  [0,1,1,1],
  [1,1,1,1],
  [0,1,1,1]
]
Output: 15
Explanation: 
There are 10 squares of side 1.
There are 4 squares of side 2.
There is  1 square of side 3.
Total number of squares = 10 + 4 + 1 = 15.
Example 2:

Input: matrix = 
[
  [1,0,1],
  [1,1,0],
  [1,1,0]
]
Output: 7
Explanation: 
There are 6 squares of side 1.  
There is 1 square of side 2. 
Total number of squares = 6 + 1 = 7.
 

Constraints:

1 <= arr.length <= 300
1 <= arr[0].length <= 300
0 <= arr[i][j] <= 1
#### EXPLANATION:
这个题目看起来很复杂，但是如果从一个一个的点开始考虑的话，推广一下就很容易想到。  
思路：  
1.遍历每一个点  
2.将每个点的单边长的方形从1开始到最大单边长进行遍历  
3.如果方形其中有一个点为0，那么退出遍历，并且后续的长度都不用再遍历了。  
4.循环1-3步，直到最终的结果
#### SOLUTION:
```java
class Solution {
    public int countSquares(int[][] matrix) {
        int result = 0;
        for(int i = 0;i<matrix.length;i++){
            for(int j = 0;j<matrix[i].length;j++){
                w: for(int m = 0;m<Math.min(matrix.length-i,matrix[i].length-j);m++){
                    if(matrix[i+m][j+m]==0) break;
                    for(int x = i;x<=i+m;x++){
                        for(int y = j;y<=j+m;y++){
                            if(matrix[x][y]!=1)
                                break w;
                        }
                    }
                    result++;
                }
            }
        }
        return result;
    }
}
```
