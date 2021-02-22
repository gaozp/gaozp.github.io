---
layout: post
title: 1282. Group the People Given the Group Size They Belong To
categories: [leetcode]
---
#### QUESTION:
There are n people that are split into some unknown number of groups. Each person is labeled with a unique ID from 0 to n - 1.

You are given an integer array groupSizes, where groupSizes[i] is the size of the group that person i is in. For example, if groupSizes[1] = 3, then person 1 must be in a group of size 3.

Return a list of groups such that each person i is in a group of size groupSizes[i].

Each person should appear in exactly one group, and every person must be in a group. If there are multiple answers, return any of them. It is guaranteed that there will be at least one valid solution for the given input.

 

__Example 1:__
```
Input: groupSizes = [3,3,3,3,3,1,3]
Output: [[5],[0,1,2],[3,4,6]]
Explanation: 
The first group is [5]. The size is 1, and groupSizes[5] = 1.
The second group is [0,1,2]. The size is 3, and groupSizes[0] = groupSizes[1] = groupSizes[2] = 3.
The third group is [3,4,6]. The size is 3, and groupSizes[3] = groupSizes[4] = groupSizes[6] = 3.
Other possible solutions are [[2,1,6],[5],[0,4,3]] and [[5],[0,6,2],[4,3,1]].
```
__Example 2:__
```
Input: groupSizes = [2,1,3,3,3,2]
Output: [[1],[0,5],[2,3,4]]
``` 

__Constraints:__
```
groupSizes.length == n
1 <= n <= 500
1 <= groupSizes[i] <= n
```
#### EXPLANATION:
这道题目不愧是medium的题目, 确实比easy的题目难度大了一点.  
这道题目的思路:  
1. 首先创建一个数组, 这个数组用来存放该size的对应的人的编号. 
2. 遍历整个groupsize, 将对应的index放入到前面创建的集合中, groupsize[index] 就是对应的位置. 
3. 当当前位置的集合满足了index的话,那么就说明已经存满了. 就可以放入到result中.

#### SOLUTION:
```java
class Solution {
    fun groupThePeople(groupSizes: IntArray): List<List<Int>> {
        var array : Array<ArrayList<Int>> = Array(groupSizes.size+1) {i -> ArrayList<Int>() }
        var result : ArrayList<List<Int>> = ArrayList()
        // 
        for (index in groupSizes.indices) array[groupSizes[index]].add(index)

        for (index in array.indices) {
            var sum = 0
            var arraylist:ArrayList<Int> = ArrayList()
            for (i in 0 until array[index].size) {
                if (sum < index) {
                    arraylist.add(array[index].get(i))
                }
                sum ++
                if (sum == index) {
                    result.add(arraylist)
                    arraylist = ArrayList()
                    sum = 0
                }
            }
        }
        return result
    }
}
```
