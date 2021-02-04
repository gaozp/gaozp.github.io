---
layout: post
title: 1678. Goal Parser Interpretation
categories: [leetcode]
---
#### QUESTION:
You own a Goal Parser that can interpret a string command. The command consists of an alphabet of "G", "()" and/or "(al)" in some order. The Goal Parser will interpret "G" as the string "G", "()" as the string "o", and "(al)" as the string "al". The interpreted strings are then concatenated in the original order.

Given the string command, return the Goal Parser's interpretation of command.

 

__Example 1:__
```
Input: command = "G()(al)"
Output: "Goal"
Explanation: The Goal Parser interprets the command as follows:
G -> G
() -> o
(al) -> al
The final concatenated result is "Goal".
```
__Example 2:__
```
Input: command = "G()()()()(al)"
Output: "Gooooal"
```
__Example 3:__
```
Input: command = "(al)G(al)()()G"
Output: "alGalooG"
 ```

__Constraints:__
```
1 <= command.length <= 100
command consists of "G", "()", and/or "(al)" in some order.
```
#### EXPLANATION:
这道题目也很简单,直接便利循环进行匹配就可以.
#### SOLUTION:
```java
class Solution {
    fun interpret(command: String): String {
        var result: String = ""
        for (i in command.indices) {
            var c = command[i]
            when (c) {
                'G' -> result += 'G'
                '(' -> {
                    var tmp = command[i + 1]
                    when (tmp) {
                        ')' -> result += 'o'
                        'a' -> result += "al"
                    }
                }
            }
        }
        return result
    }
}
```
