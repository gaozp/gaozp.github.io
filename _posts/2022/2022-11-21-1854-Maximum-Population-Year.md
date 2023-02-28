---
layout: post
title: 1854. Maximum Population Year
categories: [leetcode]
---
#### QUESTION:
You are given a 2D integer array logs where each logs[i] = [birthi, deathi] indicates the birth and death years of the ith person.

The population of some year x is the number of people alive during that year. The ith person is counted in year x's population if x is in the inclusive range [birthi, deathi - 1]. Note that the person is not counted in the year that they die.

Return the earliest year with the maximum population.

 

__Example 1:__
```
Input: logs = [[1993,1999],[2000,2010]]
Output: 1993
Explanation: The maximum population is 1, and 1993 is the earliest year with this population.
```
__Example 2:__
```
Input: logs = [[1950,1961],[1960,1971],[1970,1981]]
Output: 1960
Explanation: 
The maximum population is 2, and it had happened in years 1960 and 1970.
The earlier year between them is 1960.
```
 

__Constraints:__
```
1 <= logs.length <= 100
1950 <= birthi < deathi <= 2050
```
#### EXPLANATION:

首先将1950 - 2050排在数组里, 然后循环logs , 将每一年的人数加上, 最后再一个for循环, 来判断第一个出现max的索引.

#### SOLUTION:
```swift
class Solution {
    func maximumPopulation(_ logs: [[Int]]) -> Int {
        var years:[Int] = Array(repeating: 0, count: 100)
        for log in logs {
            var tmp = log[0]
            while tmp < log[1] {
                years[tmp - 1950] += 1
                tmp += 1
            }
        }
        var result: Int = 1950
        for index in 0...years.count - 1 {
            if (years[index] == years.max()) {
                return result + index
            }
        }
        return -1
    }
}
```
