---
layout: post
title: 661. Image Smoother
categories: [leetcode]
---

#### QUESTION:

Given a 2D integer matrix M representing the gray scale of an image, you need to design a smoother to make the gray scale of each cell becomes the average gray scale (rounding down) of all the 8 surrounding cells and itself. If a cell has less than 8 surrounding cells, then use as many as you can.

**Example 1:**

```
Input:
[[1,1,1],
 [1,0,1],
 [1,1,1]]
Output:
[[0, 0, 0],
 [0, 0, 0],
 [0, 0, 0]]
Explanation:
For the point (0,0), (0,2), (2,0), (2,2): floor(3/4) = floor(0.75) = 0
For the point (0,1), (1,0), (1,2), (2,1): floor(5/6) = floor(0.83333333) = 0
For the point (1,1): floor(8/9) = floor(0.88888889) = 0

```

**Note:**

1. The value in the given matrix is in the range of [0, 255].
2. The length and width of the given matrix are in the range of [1, 150].

#### EXPLANATION:

思路就是：

1.轮询每个点

2.每个点的值其实就等于周围8个点的值和自己这个值的平均值

也没有什么特殊的技巧，就是这样。

#### SOLUTION:

```JAVA
class Solution {
    public int[][] imageSmoother(int[][] M) {
        int[][] result = new int[M.length][M[0].length];
        for(int i = 0;i<M.length;i++){
            for(int j =0;j<M[0].length;j++){
                int count = 1;
                int left=0,right=0,top=0,down=0,topleft=0,topright=0,downleft=0,downright = 0;
                if(i-1>=0){
                    top = M[i-1][j];
                    count++;
                    if(j-1>=0){
                        topleft = M[i-1][j-1];
                        count++;
                    }
                }
                if(j-1>=0){
                    left = M[i][j-1];
                    count++;
                    if(i+1<M.length){
                        downleft = M[i+1][j-1];
                        count++;
                    }
                }
                if(i+1<M.length){
                    down = M[i+1][j];
                    count++;
                    if(j+1<M[0].length){
                        downright = M[i+1][j+1];
                        count++;
                    }
                }
                if(j+1<M[0].length){
                    right = M[i][j+1];
                    count++;
                    if(i-1>=0){
                        topright = M[i-1][j+1];
                        count++;
                    }
                }
                result[i][j] = (int)Math.floor((M[i][j]+left+right+top+down+topleft+topright+downleft+downright)/count);
            }
        }
        return result;
    }
}
```

