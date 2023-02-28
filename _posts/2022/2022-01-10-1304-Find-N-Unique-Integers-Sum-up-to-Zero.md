---
layout: post
title: 1304. Find N Unique Integers Sum up to Zero
categories: [leetcode]
---
#### QUESTION:
Given an integer n, return any array containing n unique integers such that they add up to 0.

 

__Example 1:__
```
Input: n = 5
Output: [-7,-1,1,3,4]
Explanation: These arrays also are accepted [-5,-1,1,2,3] , [-3,-1,2,-2,4].
```
__Example 2:__
```
Input: n = 3
Output: [-1,0,1]
```
__Example 3:__
```
Input: n = 1
Output: [0]
```

__Constraints:__
```
1 <= n <= 1000
```
#### EXPLANATION:
easy的题目, 没什么特别需要说的. 
1. 首先肯定是添加+x.-x
2. 如果是奇数,那么再添加上0就可以.
3. 这个x的取值其实就是n/2, 其实因为n最大才1000, 所以完全可以往前数n/2个数

#### SOLUTION:
```swift
class Solution {
    func sumZero(_ n: Int) -> [Int] {
        var result:[Int] = []
        for item in 1..<n/2+1 {
            result.append(-item)
            result.append(item)
        }
        if n%2 == 1 {
            result.append(0)
        }
        return result
    }
}
```
