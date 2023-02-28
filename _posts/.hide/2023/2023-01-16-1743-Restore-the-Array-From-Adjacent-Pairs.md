---
layout: post
title: 1743. Restore the Array From Adjacent Pairs
categories: [leetcode]
---
#### QUESTION:
There is an integer array nums that consists of n unique elements, but you have forgotten it. However, you do remember every pair of adjacent elements in nums.

You are given a 2D integer array adjacentPairs of size n - 1 where each adjacentPairs[i] = [ui, vi] indicates that the elements ui and vi are adjacent in nums.

It is guaranteed that every adjacent pair of elements nums[i] and nums[i+1] will exist in adjacentPairs, either as [nums[i], nums[i+1]] or [nums[i+1], nums[i]]. The pairs can appear in any order.

Return the original array nums. If there are multiple solutions, return any of them.

 

__Example 1:__
```
Input: adjacentPairs = [[2,1],[3,4],[3,2]]
Output: [1,2,3,4]
Explanation: This array has all its adjacent pairs in adjacentPairs.
Notice that adjacentPairs[i] may not be in left-to-right order.
```
__Example 2:__
```
Input: adjacentPairs = [[4,-2],[1,4],[-3,1]]
Output: [-2,4,1,-3]
Explanation: There can be negative numbers.
Another solution is [-3,1,4,-2], which would also be accepted.
```
__Example 3:__
```
Input: adjacentPairs = [[100000,-100000]]
Output: [100000,-100000]
```
 

__Constraints:__
```
nums.length == n
adjacentPairs.length == n - 1
adjacentPairs[i].length == 2
2 <= n <= 105
-105 <= nums[i], ui, vi <= 105
There exists some nums that has adjacentPairs as its pairs.
```
#### EXPLANATION:

1. 首先明确, 只有开头和结尾的两个数是只有一个对象,中间的数都是有两个的或者更多, 比如123, 1,3就只有一个对象2, 而2则需要链接1,3  
2. 那么就相当于通过一个图, 去将线索整起来. 首先需要构建这个图, 这个图就是每个对象的对方,   
3. 再查找这个图中只有一个节点的, 要么是起点, 要么是终点. 这都没有关系.
4. 通过这个点开始往前寻找, 将寻找过的点放在一个set里, 因为题目中说过, result是一个adjacent的.   
5. 通过set去去掉重复判断, 最终将result填充完整  


#### SOLUTION:
```swift
class Solution {
    func restoreArray(_ adjacentPairs: [[Int]]) -> [Int] {
        var set = Set<Int>()
        var result: [Int] = []
        var dic: Dictionary<Int, Set<Int>> = [:]
        for adjacentPair in adjacentPairs {
            dic[adjacentPair[0], default: []].insert(adjacentPair[1])
            dic[adjacentPair[1], default: []].insert(adjacentPair[0])
        }
        var current = dic.first!.key
        for (key,value) in dic {
            if value.count == 1 {
                current = key
            }
        }
        set.insert(current)
        result.append(current)
        while set.count != adjacentPairs.count + 1 {
            var tmp = -1
            for i in dic[current] ?? [] {
                if !set.contains(i) {
                    tmp = i
                    break
                }
            }
            set.insert(tmp)
            result.append(tmp)
            current = tmp
        }
        return result
    }
}
```
