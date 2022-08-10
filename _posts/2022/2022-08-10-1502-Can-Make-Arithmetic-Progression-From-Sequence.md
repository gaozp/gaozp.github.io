---
layout: post
title: 1502. Can Make Arithmetic Progression From Sequence
categories: [leetcode]
---
#### QUESTION:
A sequence of numbers is called an arithmetic progression if the difference between any two consecutive elements is the same.

Given an array of numbers arr, return true if the array can be rearranged to form an arithmetic progression. Otherwise, return false.

 

__Example 1:__
```
Input: arr = [3,5,1]
Output: true
Explanation: We can reorder the elements as [1,3,5] or [5,3,1] with differences 2 and -2 respectively, between each consecutive elements.
```
__Example 2:__
```
Input: arr = [1,2,4]
Output: false
Explanation: There is no way to reorder the elements to obtain an arithmetic progression.
```
 

__Constraints:__
```
2 <= arr.length <= 1000
-106 <= arr[i] <= 106
```
#### EXPLANATION:

easy的题目, 一个for循环就可以搞定了. a = b, a = c 可得 b = c,小学数学. 

#### SOLUTION:
```swift
class Solution {
    func canMakeArithmeticProgression(_ arr: [Int]) -> Bool {
        var sortedArr:[Int] = arr.sorted()
        var tmp:Int = sortedArr[0] - sortedArr[1]
        for index in stride(from: 0, to: sortedArr.count - 1, by: 1) {
            if sortedArr[index] - sortedArr[index + 1] != tmp {
                return false
            }
        }
        return true
    }
}
```
