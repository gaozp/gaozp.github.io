---
layout: post
title: 1287. Element Appearing More Than 25% In Sorted Array
categories: [leetcode]
---
#### QUESTION:
Given an integer array sorted in non-decreasing order, there is exactly one integer in the array that occurs more than 25% of the time, return that integer.

 

__Example 1:__
```
Input: arr = [1,2,2,6,6,6,6,7,10]
Output: 6
```
__Example 2:__
```
Input: arr = [1,1]
Output: 1
``` 

__Constraints:__
```
1 <= arr.length <= 104
0 <= arr[i] <= 105
```
#### EXPLANATION:
逻辑简单, 就不赘述了. 

#### SOLUTION:
```swift
class Solution {
    func findSpecialInteger(_ arr: [Int]) -> Int {
        var result:Int = arr[0]
        var pre:Int = arr[0]
        var preCount:Int = 1;
        var limit:Int = Int(Double(arr.count) * 0.25)
        for index in 1..<arr.count {
            if (arr[index] == pre) {
                preCount += 1
            } else {
                pre = arr[index]
                preCount = 1
            }
            if (preCount > limit) {
                result = pre
                break
            }
        }
        return result
    }
}
```
