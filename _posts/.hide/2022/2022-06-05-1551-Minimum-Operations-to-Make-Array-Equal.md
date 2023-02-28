---
layout: post
title: 1551. Minimum Operations to Make Array Equal
categories: [leetcode]
---
#### QUESTION:
You have an array arr of length n where arr[i] = (2 * i) + 1 for all valid values of i (i.e., 0 <= i < n).

In one operation, you can select two indices x and y where 0 <= x, y < n and subtract 1 from arr[x] and add 1 to arr[y] (i.e., perform arr[x] -=1 and arr[y] += 1). The goal is to make all the elements of the array equal. It is guaranteed that all the elements of the array can be made equal using some operations.

Given an integer n, the length of the array, return the minimum number of operations needed to make all the elements of arr equal.

 

__Example 1:__
```
Input: n = 3
Output: 2
Explanation: arr = [1, 3, 5]
First operation choose x = 2 and y = 0, this leads arr to be [2, 3, 4]
In the second operation choose x = 2 and y = 0 again, thus arr = [3, 3, 3].
```
__Example 2:__
```
Input: n = 6
Output: 9
```

__Constraints:__
```
1 <= n <= 10^4
```
#### EXPLANATION:

首先观察到的第一个点就是其实得需要区分单双数, 如果是单数, 那么平均值就是中间的数, 那么差值就是0. 如果是双数, 那么平均值就是中间两数的差值, 也就是1. 所以需要先区分出对应的初始值. 再通过初始值去进行一个斐波那契的加法就可以. 斐波那契的加法个数就是 单数/2+1 , 双数就是 双数/2 . 那么久很容易写出来了. 结果向上取整即可. 

#### SOLUTION:
```swift
class Solution {
    func minOperations(_ n: Int) -> Int {
        var initNum:Int = n % 2 == 0 ? -1 : -2;
        var result:Int = 0;
        for index in 1...Int(ceil(Double(n)/2)) {
            initNum += 2
            result += initNum
        }
        return result
    }
}
```
