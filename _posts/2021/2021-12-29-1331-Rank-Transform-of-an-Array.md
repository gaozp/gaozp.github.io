---
layout: post
title: 1331. Rank Transform of an Array
categories: [leetcode]
---
#### QUESTION:
Given an array of integers arr, replace each element with its rank.

The rank represents how large the element is. The rank has the following rules:

Rank is an integer starting from 1.
The larger the element, the larger the rank. If two elements are equal, their rank must be the same.
Rank should be as small as possible.
 

__Example 1:__
```
Input: arr = [40,10,20,30]
Output: [4,1,2,3]
Explanation: 40 is the largest element. 10 is the smallest. 20 is the second smallest. 30 is the third smallest.
```
__Example 2:__
```
Input: arr = [100,100,100]
Output: [1,1,1]
Explanation: Same elements share the same rank.
```
__Example 3:__
```
Input: arr = [37,12,28,9,100,56,80,5,12]
Output: [5,3,4,2,8,6,7,1,3]
``` 

__Constraints:__
```
0 <= arr.length <= 10^5
-10^9 <= arr[i] <= 10^9
```
#### EXPLANATION:
逻辑比较简单, 就不多赘述了. 

#### SOLUTION:
```java
class Solution {
    func arrayRankTransform(_ arr: [Int]) -> [Int] {
        var tmp:[Int] = arr
        tmp.sort()
        var dic:Dictionary = [Int:Int]()
        var pre:Int = Int.min
        var rank:Int = 0
        for item in tmp {
            if item != pre {
                pre = item
                rank += 1
                dic.updateValue(rank, forKey: pre)
            }
        }
        var result:[Int] = arr
        for index in result.indices {
            result[index] = dic[result[index]]!
        }
        return result
    }
}
```
