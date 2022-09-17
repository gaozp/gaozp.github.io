---
layout: post
title: 2224. Minimum Number of Operations to Convert Time
categories: [leetcode]
---
#### QUESTION:
You are given two strings current and correct representing two 24-hour times.

24-hour times are formatted as "HH:MM", where HH is between 00 and 23, and MM is between 00 and 59. The earliest 24-hour time is 00:00, and the latest is 23:59.

In one operation you can increase the time current by 1, 5, 15, or 60 minutes. You can perform this operation any number of times.

Return the minimum number of operations needed to convert current to correct.

 

__Example 1:__
```
Input: current = "02:30", correct = "04:35"
Output: 3
Explanation:
We can convert current to correct in 3 operations as follows:
- Add 60 minutes to current. current becomes "03:30".
- Add 60 minutes to current. current becomes "04:30".
- Add 5 minutes to current. current becomes "04:35".
It can be proven that it is not possible to convert current to correct in fewer than 3 operations.
```
__Example 2:__
```
Input: current = "11:00", correct = "11:01"
Output: 1
Explanation: We only have to add one minute to current, so the minimum number of operations needed is 1.
```
 

__Constraints:__
```
current and correct are in the format "HH:MM"
current <= correct
```
#### EXPLANATION:

简单的题目, 直接将时间都换成分钟, 然后用贪心算法, 直接用最大的方式进行增加就可以.

#### SOLUTION:
```swift
class Solution {
    func convertTime(_ current: String, _ correct: String) -> Int {
        var currentHour: Int = Int(current.split(separator: ":")[0])!
        var currentMinut: Int = Int(current.split(separator: ":")[1])!
        var correctHour: Int = Int(correct.split(separator: ":")[0])!
        var correctMinut: Int = Int(correct.split(separator: ":")[1])!
        
        var sumCurrent: Int = currentHour * 60 + currentMinut
        var sumCorrect: Int = correctHour * 60 + correctMinut
        
        var step: Int = 0
        
        while sumCurrent != sumCorrect {
            if sumCurrent + 60 <= sumCorrect {
                sumCurrent += 60
            } else if sumCurrent + 15 <= sumCorrect {
                sumCurrent += 15
            } else if sumCurrent + 5 <= sumCorrect {
                sumCurrent += 5
            } else {
                sumCurrent += 1
            }
            step += 1
        }
        return step
    }
}
```
