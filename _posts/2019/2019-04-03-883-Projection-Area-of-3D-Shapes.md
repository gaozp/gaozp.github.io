---
layout: post
title: 883. Projection Area of 3D Shapes
---

#### QUESTION:

On a `N * N` grid, we place some `1 * 1 * 1 `cubes that are axis-aligned with the x, y, and z axes.

Each value `v = grid[i][j]` represents a tower of `v` cubes placed on top of grid cell `(i, j)`.

Now we view the *projection* of these cubes onto the xy, yz, and zx planes.

A projection is like a shadow, that maps our 3 dimensional figure to a 2 dimensional plane. 

Here, we are viewing the "shadow" when looking at the cubes from the top, the front, and the side.

Return the total area of all three projections.

**Example 1:**

```
Input: [[2]]
Output: 5
```

**Example 2:**

```
Input: [[1,2],[3,4]]
Output: 17
Explanation: 
Here are the three projections ("shadows") of the shape made with each axis-aligned plane.
```

**Example 3:**

```
Input: [[1,0],[0,2]]
Output: 8
```

**Example 4:**

```
Input: [[1,1,1],[1,0,1],[1,1,1]]
Output: 14
```

**Example 5:**

```
Input: [[2,2,2],[2,1,2],[2,2,2]]
Output: 21
```

**Note:**

- `1 <= grid.length = grid[0].length <= 50`
- `0 <= grid[i][j] <= 50`

#### EXPLANATION:

题目的意思就是在x,y的平面上摆放1*1的小方块。然后求三个方向的投影。那么就可以分成3个部分。

x方向的投影，其实就是所有的yz这个平面上的最大值。

y方向的投影，就是所有xz这个平面上的值。

z方向的投影，就是xy平面上的值。

遍历整个数组，进行计算就可以了。

#### SOLUTION:

```java
class Solution {
    public int projectionArea(int[][] grid) {
        int sumX = 0;
        int sumY = 0;
        int sumZ = 0;

        for(int i =0;i<grid.length;i++){
            int tmpY = 0;
            int tmpX = 0;
            int tmpZ = 0;
            for(int j=0;j<grid[i].length;j++){
                tmpY = Math.max(tmpY,grid[i][j]);
                tmpX = Math.max(tmpX,grid[j][i]);
                if(grid[i][j]!=0) tmpZ++;
            }
            sumX+=tmpX;
            sumY+=tmpY;
            sumZ+=tmpZ;
        }
        return sumX+sumY+sumZ;
    }
}
```

