---
layout: post
title: 1779. Find Nearest Point That Has the Same X or Y Coordinate
categories: [leetcode]
---
#### QUESTION:
You are given two integers, x and y, which represent your current location on a Cartesian grid: (x, y). You are also given an array points where each points[i] = [ai, bi] represents that a point exists at (ai, bi). A point is valid if it shares the same x-coordinate or the same y-coordinate as your location.

Return the index (0-indexed) of the valid point with the smallest Manhattan distance from your current location. If there are multiple, return the valid point with the smallest index. If there are no valid points, return -1.

The Manhattan distance between two points (x1, y1) and (x2, y2) is abs(x1 - x2) + abs(y1 - y2).

 

__Example 1:__
```
Input: x = 3, y = 4, points = [[1,2],[3,1],[2,4],[2,3],[4,4]]
Output: 2
Explanation: Of all the points, only [3,1], [2,4] and [4,4] are valid. Of the valid points, [2,4] and [4,4] have the smallest Manhattan distance from your current location, with a distance of 1. [2,4] has the smallest index, so return 2.
```
__Example 2:__
```
Input: x = 3, y = 4, points = [[3,4]]
Output: 0
Explanation: The answer is allowed to be on the same location as your current location.
```
__Example 3:__
```
Input: x = 3, y = 4, points = [[2,3]]
Output: -1
Explanation: There are no valid points.
```
 

__Constraints:__
```
1 <= points.length <= 104
points[i].length == 2
1 <= x, y, ai, bi <= 104
```
#### EXPLANATION:

既然要求最小的index, 那么直接反向循环就行. 记得判断条件就是ok的. 

#### SOLUTION:
```swift
class Solution {
    func nearestValidPoint(_ x: Int, _ y: Int, _ points: [[Int]]) -> Int {
        var result:Int = -1
        var tmpDistance:Int = Int.max
        for index in points.indices.reversed() {
            var point:[Int] = points[index]
            if point[0] == x || point[1] == y {
                if abs(point[0]-x) + abs(point[1]-y) <= tmpDistance {
                    tmpDistance = abs(point[0]-x) + abs(point[1]-y)
                    result = index
                }
            }
        }
        return result
    }
}
```
