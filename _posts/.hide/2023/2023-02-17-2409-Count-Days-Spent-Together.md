---
layout: post
title: 2409. Count Days Spent Together
categories: [leetcode]
---
#### QUESTION:
Alice and Bob are traveling to Rome for separate business meetings.

You are given 4 strings arriveAlice, leaveAlice, arriveBob, and leaveBob. Alice will be in the city from the dates arriveAlice to leaveAlice (inclusive), while Bob will be in the city from the dates arriveBob to leaveBob (inclusive). Each will be a 5-character string in the format "MM-DD", corresponding to the month and day of the date.

Return the total number of days that Alice and Bob are in Rome together.

You can assume that all dates occur in the same calendar year, which is not a leap year. Note that the number of days per month can be represented as: [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31].

 

__Example 1:__
```
Input: arriveAlice = "08-15", leaveAlice = "08-18", arriveBob = "08-16", leaveBob = "08-19"
Output: 3
Explanation: Alice will be in Rome from August 15 to August 18. Bob will be in Rome from August 16 to August 19. They are both in Rome together on August 16th, 17th, and 18th, so the answer is 3.
```
__Example 2:__
```
Input: arriveAlice = "10-01", leaveAlice = "10-31", arriveBob = "11-01", leaveBob = "12-31"
Output: 0
Explanation: There is no day when Alice and Bob are in Rome together, so we return 0.
```

__Constraints:__
```
All dates are provided in the format "MM-DD".
Alice and Bob's arrival dates are earlier than or equal to their leaving dates.
The given dates are valid dates of a non-leap year.
```
#### EXPLANATION:

把两个人在一年中的区间算出来, 然后算两者相交的部分即可.

#### SOLUTION:
```swift
class Solution {
    func countDaysTogether(_ arriveAlice: String, _ leaveAlice: String, _ arriveBob: String, _ leaveBob: String) -> Int {
        let daysArray = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]

        func returnDay(_ date: String) -> Int {
            var day = 0
            for month in 0..<Int(date.split(separator: "-")[0])!-1 {
                day += daysArray[month]
            }
            day += Int(date.split(separator: "-")[1])!
            return day
        }

        let aliceDays = Set(returnDay(arriveAlice)...returnDay(leaveAlice))
        let bobDays = Set(returnDay(arriveBob)...returnDay(leaveBob))

        return aliceDays.intersection(bobDays).count
    }
}
```
