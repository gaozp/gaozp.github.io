---
layout: post
title: 1346. Check If N and Its Double Exist
categories: [leetcode]
---
#### QUESTION:
Given an array arr of integers, check if there exist two indices i and j such that :

i != j
0 <= i, j < arr.length
arr[i] == 2 * arr[j]
 

__Example 1:__
```
Input: arr = [10,2,5,3]
Output: true
Explanation: For i = 0 and j = 2, arr[i] == 10 == 2 * 5 == 2 * arr[j]
```
__Example 2:__
```
Input: arr = [3,1,7,11]
Output: false
Explanation: There is no i and j that satisfy the conditions.
```
 

__Constraints:__
```
2 <= arr.length <= 500
-103 <= arr[i] <= 103
```
#### EXPLANATION:

easy的题目, 一个for循环就能解决.

#### SOLUTION:
```swift
class Solution {
    func checkIfExist(_ arr: [Int]) -> Bool {
        var set:Set<Int> = Set()
        for a in arr {
            if set.contains(a * 2) || (a % 2 == 0 && set.contains(a/2)) {
                return true
            } else {
                set.insert(a)
            }
        }
        return false
    }
}
```
