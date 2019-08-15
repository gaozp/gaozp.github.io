---
layout: post
title: 463. Island Perimeter
categories: [leetcode]
---

####QUESTION:  
You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water. Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells). The island doesn't have "lakes" (water inside that isn't connected to the water around the island). One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.

Example:

[[0,1,0,0],
 [1,1,1,0],
 [0,1,0,0],
 [1,1,0,0]]

Answer: 16
Explanation: The perimeter is the 16 yellow stripes in the image below:

  
####EXPLANATION:  
先计算出一共会有多少块砖，同时再检测四周是否有相邻的砖存在，如果有就减去对应的1就可以。
  
####SOLUTION:  

    public int islandPerimeter(int[][] grid) {
        int count = 0;
        int dup = 0;
        for(int i = 0 ;i<grid.length;i++){
            for(int j = 0 ;j<grid[i].length;j++){
                if(grid[i][j]==1){
                    count++;
                    if(i>0&&grid[i-1][j]==1){
                        dup++;
                    }
                    if(i<grid.length-1&&grid[i+1][j]==1){
                        dup++;
                    }
                    if(j>0 && grid[i][j-1]==1){
                        dup++;
                    }
                    if(j<grid[i].length-1&&grid[i][j+1]==1){
                        dup++;
                    }
                }
            }
        }
        return count*4-dup;
    }
    


