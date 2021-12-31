---
layout: post
title: 1360. Number of Days Between Two Dates
categories: [leetcode]
---
#### QUESTION:
Write a program to count the number of days between two dates.

The two dates are given as strings, their format is YYYY-MM-DD as shown in the examples.

 

__Example 1:__
```
Input: date1 = "2019-06-29", date2 = "2019-06-30"
Output: 1
```
__Example 2:__
```
Input: date1 = "2020-01-15", date2 = "2019-12-31"
Output: 15
 ```

__Constraints:__
```
The given dates are valid dates between the years 1971 and 2100.
```
#### EXPLANATION:
这道题目我点了个踩, 如果自己去计算年月日, 那么边际条件就很多. 如果不是自己去计算, 那么就是完全在考api的熟悉程度. 毫无意义.

#### SOLUTION:
```swift
class Solution {
    func daysBetweenDates(_ date1: String, _ date2: String) -> Int {
        var formatter:DateFormatter = DateFormatter()
        formatter.dateFormat = "yyyy-MM-dd"
        var dateA:Date = formatter.date(from: date1)!
        var dateB:Date = formatter.date(from: date2)!
        let days = Calendar.current.dateComponents([.day], from: dateA, to: dateB)
        return abs(days.day!)
    }
}
```
