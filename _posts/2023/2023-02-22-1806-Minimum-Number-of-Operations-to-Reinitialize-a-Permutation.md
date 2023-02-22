---
layout: post
title: 1806. Minimum Number of Operations to Reinitialize a Permutation
categories: [leetcode]
---
#### QUESTION:
You are given an even integer n​​​​​​. You initially have a permutation perm of size n​​ where perm[i] == i​ (0-indexed)​​​​.

In one operation, you will create a new array arr, and for each i:

If i % 2 == 0, then arr[i] = perm[i / 2].
If i % 2 == 1, then arr[i] = perm[n / 2 + (i - 1) / 2].
You will then assign arr​​​​ to perm.

Return the minimum non-zero number of operations you need to perform on perm to return the permutation to its initial value.

 

__Example 1:__
```
Input: n = 2
Output: 1
Explanation: perm = [0,1] initially.
After the 1st operation, perm = [0,1]
So it takes only 1 operation.
```
__Example 2:__
```
Input: n = 4
Output: 2
Explanation: perm = [0,1,2,3] initially.
After the 1st operation, perm = [0,2,1,3]
After the 2nd operation, perm = [0,1,2,3]
So it takes only 2 operations.
```
__Example 3:__
```
Input: n = 6
Output: 4
```
 

__Constraints:__
```
2 <= n <= 1000
n​​​​​​ is even.
```
#### EXPLANATION:

看起来是一个中等的题目, 其实只要模拟出来了即可.

#### SOLUTION:
```swift
class Solution {
    func reinitializePermutation(_ n: Int) -> Int {
        var perm: [Int] = []
        var target: [Int] = []
        var result = 0
        for i in 0...n-1 {
            perm.append(i)
            target.append(i)
        }
        repeat {
            var tmpArr = perm
            for i in 0...n-1 {
                if i % 2 == 0 {
                    tmpArr[i] = perm[i/2]
                } else {
                    tmpArr[i] = perm[n/2 + (i-1)/2]
                }
            }
            perm = tmpArr
            result += 1
        } while perm != target
        return result
    }
}
```
