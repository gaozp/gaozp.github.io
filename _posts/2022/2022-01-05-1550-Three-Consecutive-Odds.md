---
layout: post
title: 1550. Three Consecutive Odds
categories: [leetcode]
---
#### QUESTION:
Given an integer array arr, return true if there are three consecutive odd numbers in the array. Otherwise, return false.
 

__Example 1:__
```
Input: arr = [2,6,4,1]
Output: false
Explanation: There are no three consecutive odds.
```
__Example 2:__
```
Input: arr = [1,2,34,3,4,5,7,23,12]
Output: true
Explanation: [5,7,23] are three consecutive odds.
 ```

__Constraints:__
```
1 <= arr.length <= 1000
1 <= arr[i] <= 1000
```
#### EXPLANATION:
easy的题目, 就不多说了. 

#### SOLUTION:
```swift
class Solution {
    func threeConsecutiveOdds(_ arr: [Int]) -> Bool {
        var count:Int = 0
        for item in arr {
            if item % 2 == 1 {
                count += 1
                if count == 3 {
                    return true
                }
            } else {
                count = 0
            }
        }
        return false
    }
}
```
