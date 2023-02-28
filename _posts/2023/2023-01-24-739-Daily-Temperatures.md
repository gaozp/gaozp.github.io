---
layout: post
title: 739. Daily Temperatures
categories: [leetcode]
---
#### QUESTION:
Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.

 

__Example 1:__
```
Input: temperatures = [73,74,75,71,69,72,76,73]
Output: [1,1,4,2,1,1,0,0]
```
__Example 2:__
```
Input: temperatures = [30,40,50,60]
Output: [1,1,1,0]
```
__Example 3:__
```
Input: temperatures = [30,60,90]
Output: [1,1,0]
```
 

__Constraints:__
```
1 <= temperatures.length <= 105
30 <= temperatures[i] <= 100
```
#### EXPLANATION:

直接用单调栈就可以, 将对应的index作为元素, 遇到比当前last大的就惊醒出栈即可.

#### SOLUTION:
```swift
class Solution {
    func dailyTemperatures(_ temperatures: [Int]) -> [Int] {
        var arr: [Int] = Array(repeating: 0, count: temperatures.count)
        var stack: [Int] = []
        if temperatures.count < 2 {
            return arr
        }
        for i in 0..<temperatures.count {
            while !stack.isEmpty && temperatures[i] > temperatures[stack.last!] {
                let index = stack.removeLast()
                arr[index] = i - index
            }
            stack.append(i)
        }
        return arr
    }
}
```
