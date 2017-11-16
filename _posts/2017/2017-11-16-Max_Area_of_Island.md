---
layout: post
title: 695. Max Area of Island
---

#### QUESTION:

Given a non-empty 2D array `grid` of 0's and 1's, an **island** is a group of `1`'s (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)

**Example 1:**

```
[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]

```

Given the above grid, return 

```
6
```

Note the answer is not 11, because the island must be connected 4-directionally.

**Example 2:**

```
[[0,0,0,0,0,0,0,0]]
```

Given the above grid, return 

```
0
```

**Note:** The length of each dimension in the given `grid` does not exceed 50.

#### EXPLANATION:

采用递归的思路，当遇到一个1的时候，就看看四周有没有1，然后递归。当然最重要的是还需要记录当前的所有已经走过的路。

#### EXPLANATION:

```JAVA
class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int length  =grid.length;
        seen = new boolean[grid.length][grid[0].length];
        if(length == 0)return 0;
        int result = 0;
        for(int i = 0;i < length;i++){
            for(int j = 0;j<grid[0].length;j++){
                if(grid[i][j]==1){
                    result = Math.max(result, maxAreaOfIslandHelper(grid,i,j));
                }
            }
        }
        return result;
    }
    boolean[][] seen;
    public int maxAreaOfIslandHelper(int[][] grid,int r,int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length ||
                seen[r][c] || grid[r][c] == 0)
            return 0;
        seen[r][c] = true;
        return (1 + maxAreaOfIslandHelper(grid,r+1, c) + maxAreaOfIslandHelper(grid,r-1, c)
                  + maxAreaOfIslandHelper(grid,r, c-1) + maxAreaOfIslandHelper(grid,r, c+1));
    }
}
```

