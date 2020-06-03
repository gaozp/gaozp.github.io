---
layout: post
title: 329. Longest Increasing Path in a Matrix
categories: [leetcode]
---
#### QUESTION:
Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

**Example 1:**
```
Input: nums = 
[
  [9,9,4],
  [6,6,8],
  [2,1,1]
] 
Output: 4 
Explanation: The longest increasing path is [1, 2, 6, 9].
```
**Example 2:**
```
Input: nums = 
[
  [3,4,5],
  [3,2,6],
  [2,2,1]
] 
Output: 4 
Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
```
#### EXPLANATION:
本来是说今天接着来做拓扑排序的，但是这道题目虽然打上了拓扑排序的标签，其实并没有用到拓扑排序，是dfs的运用。深度优先遍历，基本就可以找到结果。一开始我就直接采用深度优先遍历，一共138个测试case，在135的时候遇到了TLE，所以肯定需要想办法优化，因为暴力的深度优先遍历，肯定会有重复遍历的情况，我们就可以做一个缓存，如果这个遍历过了，已经有了结果，那么就可以直接采用这个结果。那么这个结果又是怎么来的呢，就相当于一个贪吃蛇样式的，假设点n已经有遍历结果，有i个连续的点小于他，那么这个时候有个m需要接上这个n这个点，那么结果显而易见就是i+1个。  
思路：
1. 对每个点进行遍历
2. 对当前的结果进行cache处理，既如果当前点无法向外延伸，也就是4个方向都没有比自己大的，那么默认就是1.
3. 如果其中某个方向比自己大，那么就可以向外延伸，并且对缓存进行hit，如果hit了，就直接使用缓存。
4. 将当前的点的值保存在缓存中  


#### SOLUTION:
```java
class Solution {
    
    
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix.length==0)return 0;
        int[][] cache = new int[matrix.length][matrix[0].length];
        int max = 1;
        for(int i = 0;i<matrix.length;i++){
            for(int j = 0;j<matrix[i].length;j++){
                max = Math.max(longestIncreasingPath(matrix,i,j,cache),max);
            }
        }
        return max;
    }
    
    
    static int[] dx = new int[]{-1,0,1,0};
    static int[] dy = new int[]{0,1,0,-1};

    public static int longestIncreasingPath(int[][] matrix,int m,int n,int[][] cache) {
        if(cache[m][n]!=0) return cache[m][n];
        int max = 1;
        for(int  i= 0;i<4;i++) {
            if (m + dx[i] >= 0 && n + dy[i] >= 0 && m + dx[i] < matrix.length && n + dy[i] < matrix[0].length && matrix[m + dx[i]][n + dy[i]] > matrix[m][n])
                max = Math.max(1+longestIncreasingPath(matrix, m + dx[i], n + dy[i],cache),max);
        }
        cache[m][n] = max;
        return max;
    }
}
```
