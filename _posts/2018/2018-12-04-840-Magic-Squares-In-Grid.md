---
layout: post
title: 840. Magic Squares In Grid
---

#### QUESTION:

A 3 x 3 magic square is a 3 x 3 grid filled with distinct numbers **from 1 to 9** such that each row, column, and both diagonals all have the same sum.

Given an `grid` of integers, how many 3 x 3 "magic square" subgrids are there?  (Each subgrid is contiguous).

**Example 1:**

```
Input: [[4,3,8,4],
        [9,5,1,9],
        [2,7,6,2]]
Output: 1
Explanation: 
The following subgrid is a 3 x 3 magic square:
438
951
276

while this one is not:
384
519
762

In total, there is only one magic square inside the given grid.
```

**Note:**

1. `1 <= grid.length <= 10`
2. `1 <= grid[0].length <= 10`
3. `0 <= grid[i][j] <= 15`

#### EXPLANATION:

这道题目我只想说真的蠢，怪不得那么多的人点了down。

因为中间有一个很蠢的设定就是，每个值必须在0-9之间，那么你为什么还要提供第三个note呢。

而且是不是说每个3*3都必须是0-9每个数字而不能重复呢？

算了，不吐槽了。

理解的逻辑就是：我们需要考虑的是，因为是3*3，那么我们每次取中间的数字，然后将他四周的数字进行比对。

那么就比较简单了。

1.取出所有中心的位置的数字 

2.将每个中心数字的横排进行结果比对

3.将每个中心的竖排进行结果比对

4.将每个中心的斜对角进行数字比对

5.将中心的三种结果进行比对

6.如果都能通过，那么就说明是magic的了

#### SOLUTION:

```java
class Solution {
    public int numMagicSquaresInside(int[][] grid) {
        int result = 0;
        for(int j = 1;j<grid.length-1;j++){
            for(int i = 1;i<grid[0].length-1;i++){
                if(numMagicSquaresInsideHelper(grid,j,i))
                    result++;

            }
        }
        return result;
    }
    
        public static boolean numMagicSquaresInsideHelper(int[][] grid,int i,int j){
        boolean row = true;
        int tmpRow = -1;
        for(int m =i-1;m<=i+1;m++){
            int rowsum = 0;
            for(int n = j-1;n<=j+1;n++){
                if(grid[m][n]<1 || grid[m][n]>9) return false;
                rowsum+=grid[m][n];
            }
            if(tmpRow==-1)
                tmpRow= rowsum;
            else if(tmpRow!=rowsum){
                return false;
            }
        }
        boolean column = true;
        int tmpColumn = -1;
        for(int n = j-1;n<=j+1;n++){
            int columnsum = 0;
            for(int m =i-1;m<=i+1;m++){
                if(grid[m][n]<1 || grid[m][n]>9) return false;
                columnsum+=grid[m][n];
            }
            if(tmpColumn==-1)
                tmpColumn= columnsum;
            else if(tmpColumn!=columnsum){
                return false;
            }
        }
        boolean diagonals = true;
        int tmp1 = grid[i-1][j-1]+grid[i][j]+grid[i+1][j+1];
        int tmp2 = grid[i-1][j+1]+grid[i][j]+grid[i+1][j-1];
        diagonals = tmp1==tmp2;
        if(!diagonals) return false;
        return tmpRow == tmpColumn && tmpRow == tmp1;
    }
}
```

