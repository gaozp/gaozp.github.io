---
layout: post
title: 1588. Sum of All Odd Length Subarrays
categories: [leetcode]
---
#### QUESTION:
Given an array of positive integers arr, calculate the sum of all possible odd-length subarrays.

A subarray is a contiguous subsequence of the array.

Return the sum of all odd-length subarrays of arr.

 

__Example 1:__
```
Input: arr = [1,4,2,5,3]
Output: 58
Explanation: The odd-length subarrays of arr and their sums are:
[1] = 1
[4] = 4
[2] = 2
[5] = 5
[3] = 3
[1,4,2] = 7
[4,2,5] = 11
[2,5,3] = 10
[1,4,2,5,3] = 15
If we add all these together we get 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58
```
__Example 2:__
```
Input: arr = [1,2]
Output: 3
Explanation: There are only 2 subarrays of odd length, [1] and [2]. Their sum is 3.
```
__Example 3:__
```
Input: arr = [10,11,12]
Output: 66
 ```

__Constraints:__
```
1 <= arr.length <= 100
1 <= arr[i] <= 1000
```
#### EXPLANATION:
首先理一下思路, 可以采用例子中的方式, 首先确定长度,或者采用另外一种方式,也就是首先确定位置,长度是可变的.  
那么我们就可以: 首先确定需要开始计算的位置,然后每次让长度+2,计算出这段距离的和,再让长度+2, 知道长度超出数组.
#### SOLUTION:
```java
class Solution {
    fun sumOddLengthSubarrays(arr: IntArray): Int {
        var result = 0;
        for (i in arr.indices) {
            var length = 0;
            while(i+length<=arr.size-1) {
                result+=sum(arr,i,i+length)
                length+=2
            }
        }
        return result
    }

    fun sum(arr: IntArray,start: Int,end:Int) :Int {
        var sum = 0
        for (i in start..end) {
            sum+=arr[i]
        }
        return sum
    }
}
```
