---
layout: post
title: 892. Surface Area of 3D Shapes
---

#### QUESTION:

On a `N * N` grid, we place some `1 * 1 * 1 `cubes.

Each value `v = grid[i][j]` represents a tower of `v` cubes placed on top of grid cell `(i, j)`.

Return the total surface area of the resulting shapes.

**Example 1:**

```
Input: [[2]]
Output: 10
```

**Example 2:**

```
Input: [[1,2],[3,4]]
Output: 34
```

**Example 3:**

```
Input: [[1,0],[0,2]]
Output: 16
```

**Example 4:**

```
Input: [[1,1,1],[1,0,1],[1,1,1]]
Output: 32
```

**Example 5:**

```
Input: [[2,2,2],[2,1,2],[2,2,2]]
Output: 46
```

**Note:**

- `1 <= N <= 50`
- `0 <= grid[i][j] <= 50`

#### EXPLANATION:

刚开始以为只要做出x轴，y轴，z轴的直面图，然后乘以2就可以了。没想到会有中间有空洞的情况。那么只能换一种思路。那就是一个一个的计算。一个一个计算的步骤其实就是：

1.首先计算获得当前的整个面积

2.减去相邻四面可能重合的面积

通过一番优化后，步骤二可以细分为：

1.比相邻的高，那么只需要加上减去相邻高度

2.比相邻的矮，那么就不用加

这样算法就出来了。

#### SOLUTION:

```java
    public static int surfaceArea(int[][] grid) {
        int[] dr = new int[]{1,-1,0,0};
        int[] dc = new int[]{0,0,-1,1};
        int result = 0;
        for (int i = 0;i<grid.length;i++){
            for(int j = 0;j<grid[i].length;j++){
                if(grid[i][j]!=0){
                    result+=2;
                    for(int k = 0;k<4;k++){
                        int v = 0;
                        int nr = i + dr[k];
                        int nc = j + dc[k];
                        if(nr>=0&&nr<grid.length&&nc>=0&&nc<grid.length)
                            v = grid[nr][nc];

                        result+=Math.max(grid[i][j]-v,0);
                    }
                }
            }
        }
        return result;
    }
```

