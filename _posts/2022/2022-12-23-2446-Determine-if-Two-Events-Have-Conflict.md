---
layout: post
title: 2446. Determine if Two Events Have Conflict
categories: [leetcode]
---
#### QUESTION:
You are given two arrays of strings that represent two inclusive events that happened on the same day, event1 and event2, where:

event1 = [startTime1, endTime1] and
event2 = [startTime2, endTime2].
Event times are valid 24 hours format in the form of HH:MM.

A conflict happens when two events have some non-empty intersection (i.e., some moment is common to both events).

Return true if there is a conflict between two events. Otherwise, return false.

 

__Example 1:__
```
Input: event1 = ["01:15","02:00"], event2 = ["02:00","03:00"]
Output: true
Explanation: The two events intersect at time 2:00.
```
__Example 2:__
```
Input: event1 = ["01:00","02:00"], event2 = ["01:20","03:00"]
Output: true
Explanation: The two events intersect starting from 01:20 to 02:00.
```
__Example 3:__
```
Input: event1 = ["10:00","11:00"], event2 = ["14:00","15:00"]
Output: false
Explanation: The two events do not intersect.
```
 

__Constraints:__
```
evnet1.length == event2.length == 2.
event1[i].length == event2[i].length == 5
startTime1 <= endTime1
startTime2 <= endTime2
All the event times follow the HH:MM format.
```
#### EXPLANATION:

都转化成分钟, 然后再比较对应的时间范围即可.  

#### SOLUTION:
```swift
class Solution {
    func haveConflict(_ event1: [String], _ event2: [String]) -> Bool {
        var aStart = event1[0].split(separator: ":")
        var aStartTime = Int(aStart[0])! * 60 + Int(aStart[1])!
        var aEnd = event1[1].split(separator: ":")
        var aEndTime = Int(aEnd[0])! * 60 + Int(aEnd[1])!
        var bStart = event2[0].split(separator: ":")
        var bStartTime = Int(bStart[0])! * 60 + Int(bStart[1])!
        var bEnd = event2[1].split(separator: ":")
        var bEndTime = Int(bEnd[0])! * 60 + Int(bEnd[1])!
        if (aStartTime...aEndTime).contains(bStartTime)
            || (aStartTime...aEndTime).contains(bEndTime) {
            return true
        } else if  (bStartTime...bEndTime).contains(aStartTime)
                    || (bStartTime...bEndTime).contains(aEndTime) {
            return true
        }
        return false
    }
}

```
