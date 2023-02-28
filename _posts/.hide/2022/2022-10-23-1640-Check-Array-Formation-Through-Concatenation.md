---
layout: post
title: 1640. Check Array Formation Through Concatenation
categories: [leetcode]
---
#### QUESTION:
You are given an array of distinct integers arr and an array of integer arrays pieces, where the integers in pieces are distinct. Your goal is to form arr by concatenating the arrays in pieces in any order. However, you are not allowed to reorder the integers in each array pieces[i].

Return true if it is possible to form the array arr from pieces. Otherwise, return false.

 

__Example 1:__
```
Input: arr = [15,88], pieces = [[88],[15]]
Output: true
Explanation: Concatenate [15] then [88]
```
__Example 2:__
```
Input: arr = [49,18,16], pieces = [[16,18,49]]
Output: false
Explanation: Even though the numbers match, we cannot reorder pieces[0].
```
__Example 3:__
```
Input: arr = [91,4,64,78], pieces = [[78],[4,64],[91]]
Output: true
Explanation: Concatenate [91] then [4,64] then [78]
```
 

__Constraints:__
```
1 <= pieces.length <= arr.length <= 100
sum(pieces[i].length) == arr.length
1 <= pieces[i].length <= arr.length
1 <= arr[i], pieces[i][j] <= 100
The integers in arr are distinct.
The integers in pieces are distinct (i.e., If we flatten pieces in a 1D array, all the integers in this array are distinct).
```
#### EXPLANATION:

将pieces按照arr的顺序进行排序, 再进行展平, 进行比对即可.

#### SOLUTION:
```kotlin
class Solution {
    fun canFormArray(arr: IntArray, pieces: Array<IntArray>): Boolean {
        return pieces.sortedBy { arr.indexOf(it.first()) }.map { it.map { it } }.flatten() == arr.toList()
    }
}
```
