---
layout: post
title: 1576. Replace All ?'s to Avoid Consecutive Repeating Characters
categories: [leetcode]
---
#### QUESTION:
Given a string s containing only lowercase English letters and the '?' character, convert all the '?' characters into lowercase letters such that the final string does not contain any consecutive repeating characters. You cannot modify the non '?' characters.

It is guaranteed that there are no consecutive repeating characters in the given string except for '?'.

Return the final string after all the conversions (possibly zero) have been made. If there is more than one solution, return any of them. It can be shown that an answer is always possible with the given constraints.

 

__Example 1:__
```
Input: s = "?zs"
Output: "azs"
Explanation: There are 25 solutions for this problem. From "azs" to "yzs", all are valid. Only "z" is an invalid modification as the string will consist of consecutive repeating characters in "zzs".
```
__Example 2:__
```
Input: s = "ubv?w"
Output: "ubvaw"
Explanation: There are 24 solutions for this problem. Only "v" and "w" are invalid modifications as the strings will consist of consecutive repeating characters in "ubvvw" and "ubvww".
```
 

__Constraints:__
```
1 <= s.length <= 100
s consist of lowercase English letters and '?'.
```
#### EXPLANATION:

for循环中对?进行匹配, 同时需要注意的是各种边界情况的处理即可.

#### SOLUTION:
```kotlin
class Solution {
    fun modifyString(s: String): String {
        var findLetter = fun(a: Char, b: Char): Char {
            var tmp: Array<Char> = arrayOf('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z')
            return tmp.filter { it -> it != a && it != b }[0]
        }
        var result = s.toMutableList()
        for (index in result.indices) {
            if (result[index] == '?') {
                if (index - 1 >= 0 && index + 1 <= result.size -1) {
                    if (result[index-1] != result[index+1]) {
                        result[index] = findLetter(result[index-1],result[index+1])
                    } else {
                        result[index] = findLetter(result[index-1],'-')
                    }
                } else if (index - 1 < 0 && index + 1 < result.size - 1) {
                    result[index] = findLetter(result[index+1],'-')
                } else if (index + 1 > s.length - 1 && index - 1 >= 0) {
                    result[index] = findLetter(result[index-1],'-')
                } else {
                    result[index] = 'a'
                }
            }
        }
        return String(result.toCharArray())
    }
}
```
