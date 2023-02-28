---
layout: post
title: 1442. Count Triplets That Can Form Two Arrays of Equal XOR
categories: [leetcode]
---
#### QUESTION:
Given an array of integers arr.

We want to select three indices i, j and k where (0 <= i < j <= k < arr.length).

Let's define a and b as follows:

a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
Note that ^ denotes the bitwise-xor operation.

Return the number of triplets (i, j and k) Where a == b.

 

__Example 1:__
```
Input: arr = [2,3,1,6,7]
Output: 4
Explanation: The triplets are (0,1,2), (0,2,2), (2,3,4) and (2,4,4)
```
__Example 2:__
```
Input: arr = [1,1,1,1,1]
Output: 10
```
 

__Constraints:__
```
1 <= arr.length <= 300
1 <= arr[i] <= 108
```
#### EXPLANATION:

题目比较简单, 直接三个for循环嵌套, 用tmp来记录当前a和b的结果, 进行比较即可.

#### SOLUTION:
```kotlin
class Solution {
    fun countTriplets(arr: IntArray): Int {
        var  result:Int = 0
        for (i in 0 until arr.size - 1) {
            var tmpA:Int = arr[i]
            for (j in i+1 until arr.size) {
                tmpA = tmpA xor arr[j]
                var tmpB:Int = arr[j]
                for (k in j until arr.size) {
                    tmpB = tmpB xor arr[k]
                    if (tmpA == tmpB) {
                        result += 1
                    }
                }
            }
        }
        return result
    }
}
```
