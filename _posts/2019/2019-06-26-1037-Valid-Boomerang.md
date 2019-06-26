---
layout: post
title: 1037. Valid Boomerang
---
#### QUESTION:
A boomerang is a set of 3 points that are all distinct and not in a straight line.

Given a list of three points in the plane, return whether these points are a boomerang.

 

Example 1:

Input: [[1,1],[2,3],[3,2]]
Output: true
Example 2:

Input: [[1,1],[2,2],[3,3]]
Output: false
 

Note:

points.length == 3
points[i].length == 2
0 <= points[i][j] <= 100
#### EXPLANATION:

这个题目有两个想法
1.可以通过两个点计算出ax+b=y,然后再将最后一个点带入
2.既然三个点一个直线，那么就说明他们中两个点的距离等于第三个点的距离
所以我们采用的是第二个方法。
1.传入两两的点来计算距离
2.判断是否有两段距离相加等于第三段距离

#### SOLUTION:
```java
class Solution {
    public boolean isBoomerang(int[][] points) {
       double disA = isBoomerangHelper(points[0],points[1]);
        double disB = isBoomerangHelper(points[1],points[2]);
        double disC = isBoomerangHelper(points[0],points[2]);
        return !(disA+disB==disC||disA+disC==disB||disB+disC==disA); 
    }
    
    
    public static double isBoomerangHelper(int[] A,int[] B){
        return Math.sqrt((A[0]-B[0])*(A[0]-B[0])+(A[1]-B[1])*(A[1]-B[1]));
    }
}
```