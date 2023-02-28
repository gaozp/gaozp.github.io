---
layout: post
title: 1232. Check If It Is a Straight Line
categories: [leetcode]
---
#### QUESTION:
You are given an array coordinates, coordinates[i] = [x, y], where [x, y] represents the coordinate of a point. Check if these points make a straight line in the XY plane.

 

 

Example 1:

![](https://assets.leetcode.com/uploads/2019/10/15/untitled-diagram-2.jpg)

Input: coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
Output: true
Example 2:

![](https://assets.leetcode.com/uploads/2019/10/09/untitled-diagram-1.jpg)

Input: coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
Output: false
 

Constraints:

2 <= coordinates.length <= 1000
coordinates[i].length == 2
-10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4
coordinates contains no duplicate point.
#### EXPLANATION:
其实就是初中的二元一次方程，思路就是取出两个点，确定这个方程式，然后将所有的点都套进去，查看是否所有的都能够适用。需要注意的是其中有重复的点。  
思路：  
1.如果只有两个点，那么这两个点肯定在一条直线上。  
2.判断是否所有点都在同一个位置，如果在同一个位置，那么也在同一条直线上。  
3.找到一对横纵坐标都不相同的点，来确定一条直线。  
4.将所有点带入这个方程式，如果结果不符合则说明false。否则true。
#### SOLUTION:
```java
class Solution {
    public boolean checkStraightLine(int[][] coordinates) {
        if(coordinates.length==2) return true;
        int[] a = coordinates[0];
        int index = 1;
        int[] b = coordinates[index];
        // 判断是否是同一个点
        while (index<coordinates.length && b[0] == a[0] && b[1] == a[1])
            b = coordinates[index++];
        if(a[0]==b[0] && a[1]==b[1]) return true;

        index = 1;
        b = coordinates[index];
        while (index<coordinates.length && (b[0] == a[0] || b[1]==a[1]))
            b = coordinates[index++];

        int x = (b[1]-a[1])/(b[0]-a[0]);
        int y = b[1]-x*b[0];
        for(int i = 0;i<coordinates.length;i++){
            if(coordinates[i][0]*x+y!=coordinates[i][1]) return false;
        }
        return true;
    }
}
```
