---
layout: post
title: 1539. Kth Missing Positive Number
categories: [leetcode]
---
#### QUESTION:
Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.

Return the kth positive integer that is missing from this array.

 

__Example 1:__
```
Input: arr = [2,3,4,7,11], k = 5
Output: 9
Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.
```
__Example 2:__
```
Input: arr = [1,2,3,4], k = 2
Output: 6
Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.
```
 

__Constraints:__
```
1 <= arr.length <= 1000
1 <= arr[i] <= 1000
1 <= k <= 1000
arr[i] < arr[j] for 1 <= i < j <= arr.length
```
 

__Follow up:__
```
Could you solve this problem in less than O(n) complexity?
```
#### EXPLANATION:

easy的题目, 最大值那应该就是arr的最后一个加上k, 所以我们就可以用1到last. 然后通过missingcount来标记, 就可以获取到对应的第几个值.

#### SOLUTION:
```kotlin
class Solution {
    fun findKthPositive(arr: IntArray, k: Int): Int {
        var last = arr.last() + k
        var missingCount = 0
        for (i in 1..last) {
            if (!arr.contains(i)) {
                missingCount += 1
                if (missingCount == k) {
                    return i
                }
            }
        }
        return -1
    }
}
```
