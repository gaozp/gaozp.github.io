---
layout: post
title: 1624. Largest Substring Between Two Equal Characters
categories: [leetcode]
---
#### QUESTION:
Given a string s, return the length of the longest substring between two equal characters, excluding the two characters. If there is no such substring return -1.

A substring is a contiguous sequence of characters within a string.

 

__Example 1:__
```
Input: s = "aa"
Output: 0
Explanation: The optimal substring here is an empty substring between the two 'a's.
```
__Example 2:__
```
Input: s = "abca"
Output: 2
Explanation: The optimal substring here is "bc".
```
__Example 3:__
```
Input: s = "cbzxy"
Output: -1
Explanation: There are no characters that appear twice in s.
```
 

__Constraints:__
```
1 <= s.length <= 300
s contains only lowercase English letters.
```
#### EXPLANATION:

easy的题目, for循环就可以, 用hashmap来记录位置, 只要能获取到, 说明遇到了. 否则就说明没遇到, 把位置记录住即可. 

#### SOLUTION:
```kotlin
class Solution {
    fun maxLengthBetweenEqualCharacters(s: String): Int {
        var map: HashMap<Char, Int> = HashMap()
        var result = -1
        s.forEachIndexed { index, c -> 
            if (map.get(c) != null) {
                result = max(result, index - map.get(c)!! - 1)
            } else {
                map.put(c, index)
            }
        }
        return result
    }
}
```
