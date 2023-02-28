---
layout: post
title: 1385. Find the Distance Value Between Two Arrays
categories: [leetcode]
---
#### QUESTION:
Given two integer arrays arr1 and arr2, and the integer d, return the distance value between the two arrays.

The distance value is defined as the number of elements arr1[i] such that there is not any element arr2[j] where |arr1[i]-arr2[j]| <= d.

 

__Example 1:__
```
Input: arr1 = [4,5,8], arr2 = [10,9,1,8], d = 2
Output: 2
Explanation: 
For arr1[0]=4 we have: 
|4-10|=6 > d=2 
|4-9|=5 > d=2 
|4-1|=3 > d=2 
|4-8|=4 > d=2 
For arr1[1]=5 we have: 
|5-10|=5 > d=2 
|5-9|=4 > d=2 
|5-1|=4 > d=2 
|5-8|=3 > d=2
For arr1[2]=8 we have:
|8-10|=2 <= d=2
|8-9|=1 <= d=2
|8-1|=7 > d=2
|8-8|=0 <= d=2
```
__Example 2:__
```
Input: arr1 = [1,4,2,3], arr2 = [-4,-3,6,10,20,30], d = 3
Output: 2
```
__Example 3:__
```
Input: arr1 = [2,1,100,3], arr2 = [-5,-2,10,-3,7], d = 6
Output: 1
```
 

__Constraints:__
```
1 <= arr1.length, arr2.length <= 500
-1000 <= arr1[i], arr2[j] <= 1000
0 <= d <= 100
```
#### EXPLANATION:

这个就比较简单了, 两个for循环的嵌套即可.

#### SOLUTION:
```swift
class Solution {
    func findTheDistanceValue(_ arr1: [Int], _ arr2: [Int], _ d: Int) -> Int {
        var result = 0
        for arri in arr1 {
            var flag: Bool = true
            for arrj in arr2 {
                if (abs(arrj - arri) <= d) {
                    flag = false
                    break
                }
            }
            if (flag) {
                result += 1
            }
        }
        return result
    }
}
```
