---
layout: post
title: 2053. Kth Distinct String in an Array
categories: [leetcode]
---
#### QUESTION:
A distinct string is a string that is present only once in an array.

Given an array of strings arr, and an integer k, return the kth distinct string present in arr. If there are fewer than k distinct strings, return an empty string "".

Note that the strings are considered in the order in which they appear in the array.

 

__Example 1:__
```
Input: arr = ["d","b","c","b","c","a"], k = 2
Output: "a"
Explanation:
The only distinct strings in arr are "d" and "a".
"d" appears 1st, so it is the 1st distinct string.
"a" appears 2nd, so it is the 2nd distinct string.
Since k == 2, "a" is returned. 
```
__Example 2:__
```
Input: arr = ["aaa","aa","a"], k = 1
Output: "aaa"
Explanation:
All strings in arr are distinct, so the 1st string "aaa" is returned.
```
__Example 3:__
```
Input: arr = ["a","b","a"], k = 3
Output: ""
Explanation:
The only distinct string is "b". Since there are fewer than 3 distinct strings, we return an empty string "".
```

__Constraints:__
```
1 <= k <= arr.length <= 1000
1 <= arr[i].length <= 5
arr[i] consists of lowercase English letters.
```
#### EXPLANATION:

1.首先用一个字典算出每个string出现的次数  
2.再按顺序看当前的string是不是单独的, 如果是单独的, 就看一下当前是第几个单独出现的  
3.如果是第k个单独出现的, 就返回当前string  
4.否则就返回空字符串

#### SOLUTION:
```swift
class Solution {
    func kthDistinct(_ arr: [String], _ k: Int) -> String {
        var result:String = ""
        var dic:Dictionary<String,Int> = [:]
        for a in arr {
            if dic.keys.contains(a) {
                dic[a] = dic[a]!+1
            } else {
                dic[a] = 1
            }
        }
        var index:Int = 0
        for a in arr {
            if dic[a] == 1 {
                index += 1
                if index == k {
                    return a
                }
            }
        }
        return result
    }
}
```
