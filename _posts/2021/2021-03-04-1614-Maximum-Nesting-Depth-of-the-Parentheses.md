---
layout: post
title: 1614. Maximum Nesting Depth of the Parentheses
categories: [leetcode]
---
#### QUESTION:
A string is a valid parentheses string (denoted VPS) if it meets one of the following:

It is an empty string "", or a single character not equal to "(" or ")",
It can be written as AB (A concatenated with B), where A and B are VPS's, or
It can be written as (A), where A is a VPS.
We can similarly define the nesting depth depth(S) of any VPS S as follows:

depth("") = 0
depth(C) = 0, where C is a string with a single character not equal to "(" or ")".
depth(A + B) = max(depth(A), depth(B)), where A and B are VPS's.
depth("(" + A + ")") = 1 + depth(A), where A is a VPS.
For example, "", "()()", and "()(()())" are VPS's (with nesting depths 0, 1, and 2), and ")(" and "(()" are not VPS's.

Given a VPS represented as string s, return the nesting depth of s.

 

__Example 1:__
```
Input: s = "(1+(2*3)+((8)/4))+1"
Output: 3
Explanation: Digit 8 is inside of 3 nested parentheses in the string.
```
__Example 2:__
```
Input: s = "(1)+((2))+(((3)))"
Output: 3
```
__Example 3:__
```
Input: s = "1+(2*3)/(2-1)"
Output: 1
```
__Example 4:__
```
Input: s = "1"
Output: 0
 ```

__Constraints:__
```
1 <= s.length <= 100
s consists of digits 0-9 and characters '+', '-', '*', '/', '(', and ')'.
It is guaranteed that parentheses expression s is a VPS.
```
#### EXPLANATION:
遍历字符串, 如果是'(' 就计入深度,同时更新深度的最大值. 如果是')'就减少深度. 比较简单.
#### SOLUTION:
```java
class Solution {
    fun maxDepth(s: String): Int {
        var result : Int = 0
        var stack : Int = 0
        for (a in s.toCharArray()){
            when (a) {
                '(' -> {
                    stack++
                    result = Math.max(result, stack)
                }
                ')' -> stack--
            }
        }
        return result
    }
}
```
