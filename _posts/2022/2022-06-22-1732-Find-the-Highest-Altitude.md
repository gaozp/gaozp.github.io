---
layout: post
title: 1732. Find the Highest Altitude
categories: [leetcode]
---
#### QUESTION:
There is a biker going on a road trip. The road trip consists of n + 1 points at different altitudes. The biker starts his trip on point 0 with altitude equal 0.

You are given an integer array gain of length n where gain[i] is the net gain in altitude between points i​​​​​​ and i + 1 for all (0 <= i < n). Return the highest altitude of a point.

 

__Example 1:__
```
Input: gain = [-5,1,5,0,-7]
Output: 1
Explanation: The altitudes are [0,-5,-4,1,1,-6]. The highest is 1.
```
__Example 2:__
```
Input: gain = [-4,-3,-2,-1,4,3,2]
Output: 0
Explanation: The altitudes are [0,-4,-7,-9,-10,-6,-3,-1]. The highest is 0.
```

__Constraints:__
```
n == gain.length
1 <= n <= 100
-100 <= gain[i] <= 100
```
#### EXPLANATION:

easy的题目, 首先将结果数组添加初始的0, 然后遍历将每次相加的数加在最后, 进行排序, 返回最大值. 当然, 也可以在添加的过程中进行比对, 这样会节省一个排序的复杂度. 变成O(n)的线性复杂度.

#### SOLUTION:
```swift
class Solution {
    func largestAltitude(_ gain: [Int]) -> Int {
        var arr:[Int] = [0]
        for index in gain.indices {
            arr.append(gain[index]+arr.last!)
        }
        return arr.sorted().last!
    }
}
```
