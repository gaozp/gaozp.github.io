---
layout: post
title: 1720. Decode XORed Array
categories: [leetcode]
---
#### QUESTION:
There is a hidden integer array arr that consists of n non-negative integers.

It was encoded into another integer array encoded of length n - 1, such that encoded[i] = arr[i] XOR arr[i + 1]. For example, if arr = [1,0,2,1], then encoded = [1,2,3].

You are given the encoded array. You are also given an integer first, that is the first element of arr, i.e. arr[0].

Return the original array arr. It can be proved that the answer exists and is unique.

 

Example 1:

Input: encoded = [1,2,3], first = 1
Output: [1,0,2,1]
Explanation: If arr = [1,0,2,1], then first = 1 and encoded = [1 XOR 0, 0 XOR 2, 2 XOR 1] = [1,2,3]
Example 2:

Input: encoded = [6,2,7,3], first = 4
Output: [4,2,0,7,4]
 

Constraints:

2 <= n <= 104
encoded.length == n - 1
0 <= encoded[i] <= 105
0 <= first <= 105
#### EXPLANATION:
这道题目其实是一个算术题. 只要知道一个规律就可以了:  
__x xor a = b__
__b xor a = x__
既一个数,连续异或两次同一个数,结果还是自己. 通过这个规律,我们就可以反向推出最终的结果.

#### SOLUTION:
```java
class Solution {
    fun decode(encoded: IntArray, first: Int): IntArray {
        var result : IntArray = IntArray(encoded.size+1)
        if (result.size>0) result[0] = first
        for (i in encoded.indices) result[i+1] = encoded[i] xor result[i]
        return result
    }
}
```
