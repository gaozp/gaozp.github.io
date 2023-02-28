---
layout: post
title: 791. Custom Sort String
categories: [leetcode]
---
#### QUESTION:
You are given two strings order and s. All the characters of order are unique and were sorted in some custom order previously.

Permute the characters of s so that they match the order that order was sorted. More specifically, if a character x occurs before a character y in order, then x should occur before y in the permuted string.

Return any permutation of s that satisfies this property.

 

__Example 1:__
```
Input: order = "cba", s = "abcd"
Output: "cbad"
Explanation: 
"a", "b", "c" appear in order, so the order of "a", "b", "c" should be "c", "b", and "a". 
Since "d" does not appear in order, it can be at any position in the returned string. "dcba", "cdba", "cbda" are also valid outputs.
```
__Example 2:__
```
Input: order = "cbafg", s = "abcd"
Output: "cbad"
```
 

__Constraints:__
```
1 <= order.length <= 26
1 <= s.length <= 200
order and s consist of lowercase English letters.
All the characters of order are unique.
```
#### EXPLANATION:

根据题意, 将s中所有的字母先放在一个dic中, 再根据order去将dic中的数字取出来放在结果中. 有了思路就很好办了. 这里需要注意的是. 不是abcdabcd, 而是aabbccdd, 这样的. 这样就容易很多了.

#### SOLUTION:
```swift
class Solution {
    func customSortString(_ order: String, _ s: String) -> String {
        var str = ""
        var dic:Dictionary<Character, Int> = [:]
        for ch in s {
            dic[ch,default: 0] += 1
        }
        for o in order {
            while let count = dic[o], count > 0 {
                str += String(o)
                dic[o] = count - 1
                if count - 1 == 0 {
                    dic.removeValue(forKey: o)
                }
            }
        }
        for key in dic.keys {
            while let count = dic[key], count > 0 {
                str += String(key)
                dic[key] = count - 1
                if count - 1 == 0 {
                    dic.removeValue(forKey: key)
                }
            }
        }
        return str
    }
}
```
