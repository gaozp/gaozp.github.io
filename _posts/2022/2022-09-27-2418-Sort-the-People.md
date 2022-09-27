---
layout: post
title: 2418. Sort the People
categories: [leetcode]
---
#### QUESTION:
You are given an array of strings names, and an array heights that consists of distinct positive integers. Both arrays are of length n.

For each index i, names[i] and heights[i] denote the name and height of the ith person.

Return names sorted in descending order by the people's heights.

 

__Example 1:__
```
Input: names = ["Mary","John","Emma"], heights = [180,165,170]
Output: ["Mary","Emma","John"]
Explanation: Mary is the tallest, followed by Emma and John.
```
__Example 2:__
```
Input: names = ["Alice","Bob","Bob"], heights = [155,185,150]
Output: ["Bob","Alice","Bob"]
Explanation: The first Bob is the tallest, followed by Alice and the second Bob.
```
 

__Constraints:__
```
n == names.length == heights.length
1 <= n <= 103
1 <= names[i].length <= 20
1 <= heights[i] <= 105
names[i] consists of lower and upper case English letters.
All the values of heights are distinct.
```
#### EXPLANATION:

用冒泡排序的方式, 将name和height一起进行调整即可.

#### SOLUTION:
```kotlin
    fun sortPeople(names: Array<String>, heights: IntArray): Array<String> {
        for (i in heights.indices) {
            for (j in i until heights.size) {
                if (heights[j] > heights[i]) {
                    var tmp:Int = heights[j]
                    heights[j] = heights[i]
                    heights[i] = tmp

                    var tmpName:String = names.get(j)
                    names.set(j, names.get(i))
                    names.set(i, tmpName)
                }
            }
        }
        return names
    }
```
