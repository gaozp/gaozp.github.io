---
layout: post
title: 1266. Minimum Time Visiting All Points
categories: [leetcode]
---
#### QUESTION:
On a plane there are n points with integer coordinates points[i] = [xi, yi]. Your task is to find the minimum time in seconds to visit all points.

You can move according to the next rules:

In one second always you can either move vertically, horizontally by one unit or diagonally (it means to move one unit vertically and one unit horizontally in one second).
You have to visit the points in the same order as they appear in the array.
 

**Example 1:**
![img](https://assets.leetcode.com/uploads/2019/11/14/1626_example_1.PNG)
```
Input: points = [[1,1],[3,4],[-1,0]]
Output: 7
Explanation: One optimal path is [1,1] -> [2,2] -> [3,3] -> [3,4] -> [2,3] -> [1,2] -> [0,1] -> [-1,0]   
Time from [1,1] to [3,4] = 3 seconds 
Time from [3,4] to [-1,0] = 4 seconds
Total time = 7 seconds
```
**Example 2:**
```
Input: points = [[3,2],[-2,2]]
Output: 5
```
 

**Constraints:**
```
points.length == n
1 <= n <= 100
points[i].length == 2
-1000 <= points[i][0], points[i][1] <= 1000
```
#### EXPLANATION:
题意也很直接，你每次可以横着或者竖着，或者对角线走一步，二是需要按着顺序走，那么就很容易知道，就是求两点之间的最短，都知道直接是直线最短，那么这个地方怎么进行呢。你把这两个点想成直角三角形的两个边，极端情况就是两者在x轴上或者y轴上，所以可以得到min = max(dx,dy)  
思路：  
1. 定义一个result来记录结果
2. 获取到i点和i-1的点，两者x和y的最大的delta
3. 累加到result中
4. 返回结果
#### SOLUTION:
```java
class Solution {
    public int minTimeToVisitAllPoints(int[][] points) {
        int result = 0;
        for(int i=1;i<points.length;i++)
            result+= Math.max(Math.abs(points[i][0]-points[i-1][0]),Math.abs(points[i][1]-points[i-1][1]));
        return result;
    }
}
```
