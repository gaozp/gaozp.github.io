---
layout: post
title: 1534. Count Good Triplets
categories: [leetcode]
---
#### QUESTION:
Given an array of integers arr, and three integers a, b and c. You need to find the number of good triplets.

A triplet (arr[i], arr[j], arr[k]) is good if the following conditions are true:

0 <= i < j < k < arr.length
|arr[i] - arr[j]| <= a
|arr[j] - arr[k]| <= b
|arr[i] - arr[k]| <= c
Where |x| denotes the absolute value of x.

Return the number of good triplets.

 

__Example 1:__
```
Input: arr = [3,0,1,1,9,7], a = 7, b = 2, c = 3
Output: 4
Explanation: There are 4 good triplets: [(3,0,1), (3,0,1), (3,1,1), (0,1,1)].
```
__Example 2:__
```
Input: arr = [1,1,2,2,3], a = 0, b = 0, c = 1
Output: 0
Explanation: No triplet satisfies all conditions.
```

__Constraints:__
```
3 <= arr.length <= 100
0 <= arr[i] <= 1000
0 <= a, b, c <= 1000
```
#### EXPLANATION:

不知道该咋说, 竟然真的是三个for循环的嵌套.怪不得那么多点踩的. 

#### SOLUTION:
```swift
class Solution {
    func countGoodTriplets(_ arr: [Int], _ a: Int, _ b: Int, _ c: Int) -> Int {
        var result:Int = 0
        for indexI in 0...arr.count-3 {
            for indexJ in indexI+1...arr.count-2 {
                for indexK in indexJ+1...arr.count-1 {
                    if (abs(arr[indexI]-arr[indexJ]) <= a
                        && abs(arr[indexJ]-arr[indexK]) <= b
                        && abs(arr[indexI]-arr[indexK]) <= c) {
                        result+=1
                    }
                }
            }
        }
        return result
    }
}
```
