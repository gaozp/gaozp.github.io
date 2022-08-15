---
layout: post
title: 1925. Count Square Sum Triples
categories: [leetcode]
---
#### QUESTION:
A square triple (a,b,c) is a triple where a, b, and c are integers and a2 + b2 = c2.

Given an integer n, return the number of square triples such that 1 <= a, b, c <= n.

 

__Example 1:__
```
Input: n = 5
Output: 2
Explanation: The square triples are (3,4,5) and (4,3,5).
```
__Example 2:__
```
Input: n = 10
Output: 4
Explanation: The square triples are (3,4,5), (4,3,5), (6,8,10), and (8,6,10).
```
 

__Constraints:__
```
1 <= n <= 250
```
#### EXPLANATION:

easy的题目, 三个for循环的嵌套就可以模拟出结果.

#### SOLUTION:
```swift
class Solution {
    func countTriples(_ n: Int) -> Int {
        var result:Int = 0
        for numI in 1...n {
            for numJ in 1...n {
                for numK in 1...n {
                    if numI * numI + numJ * numJ == numK * numK {
                        result += 1
                    }
                }
            }
        }
        return result
    }
}
```
