---
layout: post
title: 1812. Determine Color of a Chessboard Square
categories: [leetcode]
---
#### QUESTION:
You are given coordinates, a string that represents the coordinates of a square of the chessboard. Below is a chessboard for your reference.

![](https://assets.leetcode.com/uploads/2021/02/19/screenshot-2021-02-20-at-22159-pm.png)

Return true if the square is white, and false if the square is black.

The coordinate will always represent a valid chessboard square. The coordinate will always have the letter first, and the number second.

 

__Example 1:__
```
Input: coordinates = "a1"
Output: false
Explanation: From the chessboard above, the square with coordinates "a1" is black, so return false.
```
__Example 2:__
```
Input: coordinates = "h3"
Output: true
Explanation: From the chessboard above, the square with coordinates "h3" is white, so return true.
```
__Example 3:__
```
Input: coordinates = "c7"
Output: false
```
 

__Constraints:__

coordinates.length == 2
'a' <= coordinates[0] <= 'h'
'1' <= coordinates[1] <= '8'
#### EXPLANATION:

easy的题目,直接将棋盘摆出,然后查找就可以了.

#### SOLUTION:
```swift
class Solution {
    func squareIsWhite(_ coordinates: String) -> Bool {
        var chessboard:[[Bool]] = [
            [false,true,false,true,false,true,false,true],
            [true,false,true,false,true,false,true,false],
            [false,true,false,true,false,true,false,true],
            [true,false,true,false,true,false,true,false],
            [false,true,false,true,false,true,false,true],
            [true,false,true,false,true,false,true,false],
            [false,true,false,true,false,true,false,true],
            [true,false,true,false,true,false,true,false]
        ]
        var row:Int = Int(Array(coordinates)[0].asciiValue! - 97)
        var col:Int = Int(String(Array(coordinates)[1]))!
        return chessboard[row][col-1]
    }
}
```
