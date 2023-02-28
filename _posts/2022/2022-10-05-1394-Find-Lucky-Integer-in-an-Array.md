---
layout: post
title: 1394. Find Lucky Integer in an Array
categories: [leetcode]
---
#### QUESTION:
Given an array of integers arr, a lucky integer is an integer that has a frequency in the array equal to its value.

Return the largest lucky integer in the array. If there is no lucky integer return -1.

 

__Example 1:__
```
Input: arr = [2,2,3,4]
Output: 2
Explanation: The only lucky number in the array is 2 because frequency[2] == 2.
```
__Example 2:__
```
Input: arr = [1,2,2,3,3,3]
Output: 3
Explanation: 1, 2 and 3 are all lucky numbers, return the largest of them.
```
__Example 3:__
```
Input: arr = [2,2,2,3,3]
Output: -1
Explanation: There are no lucky numbers in the array.
```
 

__Constraints:__
```
1 <= arr.length <= 500
1 <= arr[i] <= 500
```
#### EXPLANATION:

比较简单的题目, 主要是为了锻炼kotlin中的流式操作.

#### SOLUTION:
```kotlin
import kotlin.math.max
class Solution {
    fun findLucky(arr: IntArray): Int {
        var result:Int = -1
        var map:HashMap<Int,Int> = HashMap()
        arr.forEach { a ->
            map[a] = map.getOrDefault(a, 0)+1
        }
        map.forEach { (t, u) ->
            if (t == u) {
                result = max(result, t)
            }
        }
        return result
    }
}
```
