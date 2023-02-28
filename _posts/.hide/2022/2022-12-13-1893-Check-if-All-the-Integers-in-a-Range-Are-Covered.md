---
layout: post
title: 1893. Check if All the Integers in a Range Are Covered
categories: [leetcode]
---
#### QUESTION:
You are given a 2D integer array ranges and two integers left and right. Each ranges[i] = [starti, endi] represents an inclusive interval between starti and endi.

Return true if each integer in the inclusive range [left, right] is covered by at least one interval in ranges. Return false otherwise.

An integer x is covered by an interval ranges[i] = [starti, endi] if starti <= x <= endi.

 

__Example 1:__
```
Input: ranges = [[1,2],[3,4],[5,6]], left = 2, right = 5
Output: true
Explanation: Every integer between 2 and 5 is covered:
- 2 is covered by the first range.
- 3 and 4 are covered by the second range.
- 5 is covered by the third range.
```
__Example 2:__
```
Input: ranges = [[1,10],[10,20]], left = 21, right = 21
Output: false
Explanation: 21 is not covered by any range.
```
 

__Constraints:__
```
1 <= ranges.length <= 50
1 <= starti <= endi <= 50
1 <= left <= right <= 50
```
#### EXPLANATION:

采用网格涂色的方式, 将range中所有的格子先涂上颜色, 也就是true, 然后在判断left,right之间的所有格子是不是都是染色即可.

#### SOLUTION:
```swift
class Solution {
    func isCovered(_ ranges: [[Int]], _ left: Int, _ right: Int) -> Bool {
        var resultArr:[Bool] = Array(repeating: false, count: 51)
        for range in ranges {
            for index in range[0]...range[1] {
                resultArr[index] = true
            }
        }
        for index in left...right {
            if !resultArr[index] {
                return false
            }
        }
        return true
    }
}
```
