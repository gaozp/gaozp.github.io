---
layout: post
title: 49. Group Anagrams
categories: [leetcode]
---
#### QUESTION:
Given an array of strings strs, group the anagrams together. You can return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

 

__Example 1:__
```
Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
```
__Example 2:__
```
Input: strs = [""]
Output: [[""]]
```
__Example 3:__
```
Input: strs = ["a"]
Output: [["a"]]
```
 

__Constraints:__
```
1 <= strs.length <= 104
0 <= strs[i].length <= 100
strs[i] consists of lowercase English letters.
```
#### EXPLANATION:

思路肯定就是, 首先将单个str进行排序, 再把排序结果当成key, 然后把对应的坐标当成value,进行保存, 这样就相当于对strs进行了排序. 最后再将排序后的dic展开成最后的结果即可.

#### SOLUTION:
```swift
class Solution {
    func groupAnagrams(_ strs: [String]) -> [[String]] {
        var dic: Dictionary<String, [Int]> = [:]
        for (i, str) in strs.enumerated() {
            dic[String(str.sorted()), default: []].append(i)
        }
        var result: [[String]] = []
        for value in dic.values {
            var tmpArr: [String] = []
            for v in value {
                tmpArr.append(strs[v])
            }
            result.append(tmpArr)
        }
        return result
    }
}
```
