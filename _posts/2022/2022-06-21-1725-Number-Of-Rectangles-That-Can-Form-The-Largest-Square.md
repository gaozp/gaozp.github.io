---
layout: post
title: 1725. Number Of Rectangles That Can Form The Largest Square
categories: [leetcode]
---
#### QUESTION:
You are given an array rectangles where rectangles[i] = [li, wi] represents the ith rectangle of length li and width wi.

You can cut the ith rectangle to form a square with a side length of k if both k <= li and k <= wi. For example, if you have a rectangle [4,6], you can cut it to get a square with a side length of at most 4.

Let maxLen be the side length of the largest square you can obtain from any of the given rectangles.

Return the number of rectangles that can make a square with a side length of maxLen.

 

__Example 1:__
```
Input: rectangles = [[5,8],[3,9],[5,12],[16,5]]
Output: 3
Explanation: The largest squares you can get from each rectangle are of lengths [5,3,5,5].
The largest possible square is of length 5, and you can get it out of 3 rectangles.
```
__Example 2:__
```
Input: rectangles = [[2,3],[3,7],[4,3],[3,7]]
Output: 3
 ```

__Constraints:__
```
1 <= rectangles.length <= 1000
rectangles[i].length == 2
1 <= li, wi <= 109
li != wi
```
#### EXPLANATION:

easy的题目, 直接看注释就可以了.

#### SOLUTION:
```swift
class Solution {
    func countGoodRectangles(_ rectangles: [[Int]]) -> Int {
        var result:[Int] = [0,0] // 索引0 表示最大边长, 索引1表示个数
        for rectangle in rectangles {
            var minLength:Int = min(rectangle[0], rectangle[1])
            if (minLength == result[0]) { // 如果是当前最大边长, 将结果加一
                result[1] += 1
            } else if (minLength > result[0]) { // 如果最大边长改变, 那么记录下最大边长, 同时将结果复位成1
                result[0] = minLength
                result[1] = 1
            }
        }
        return result[1]
    }
}
```
