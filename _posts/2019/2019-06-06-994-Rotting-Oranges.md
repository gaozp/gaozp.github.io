---
layout: post
title: 994. Rotting Oranges
---
#### QUESTION:
In a given grid, each cell can have one of three values:

the value 0 representing an empty cell;
the value 1 representing a fresh orange;
the value 2 representing a rotten orange.
Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.

 

Example 1:
![haha](https://assets.leetcode.com/uploads/2019/02/16/oranges.png)
Input: [[2,1,1],[1,1,0],[0,1,1]]
Output: 4

Example 2:

Input: [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
Example 3:

Input: [[0,2]]
Output: 0
Explanation:  Since there are already no fresh oranges at minute 0, the answer is just 0.
 

Note:

1 <= grid.length <= 10
1 <= grid[0].length <= 10
grid[i][j] is only 0, 1, or 2.

#### EXPLANATION:
我们可以这样想：
1.首先进行一遍查找，查找到需要改变的点
2.如果没有点需要进行改变，那么就说明是已经全部改变完了
3.再进行check，如果还有1，说明还有不需要改变的点
4.如果没有1了，那么就输出改变了多少次

需要改变的点的算法就比较简单了，直接得到周围是否有2的点就行。

#### SOLUTION:
```
class Solution {
    public int orangesRotting(int[][] grid) {
        Queue<int[]> queue = new ArrayDeque<>();
        int count = 0;
        while (true){
            for(int i = 0;i<grid.length;i++){
                for(int j = 0;j<grid[0].length;j++){
                    if(grid[i][j]==1){
                        if(i-1>=0 && grid[i-1][j]==2) queue.add(new int[]{i,j});
                        if(j-1>=0 && grid[i][j-1]==2) queue.add(new int[]{i,j});
                        if(i+1<grid.length && grid[i+1][j]==2) queue.add(new int[]{i,j});
                        if(j+1<grid[i].length && grid[i][j+1]==2) queue.add(new int[]{i,j});
                    }
                }
            }
            if(queue.isEmpty()) break;
            while (!queue.isEmpty()){
                int[] poll = queue.poll();
                grid[poll[0]][poll[1]] = 2;
            }
            count++;
        }
        if(orangesRottingHelper(grid)) return count;
        else return -1;
    }
    
    public static boolean orangesRottingHelper(int[][] grid){
        for(int i = 0;i<grid.length;i++){
            for(int j = 0;j<grid[i].length;j++){
                if(grid[i][j]==1) return false;
            }
        }
        return true;
    }
}
```