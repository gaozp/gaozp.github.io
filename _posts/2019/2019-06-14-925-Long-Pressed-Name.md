---
layout: post
title: 925. Long Pressed Name
categories: [leetcode]
---

#### QUESTION:
Your friend is typing his name into a keyboard.  Sometimes, when typing a character c, the key might get long pressed, and the character will be typed 1 or more times.

You examine the typed characters of the keyboard.  Return True if it is possible that it was your friends name, with some characters (possibly none) being long pressed.

Example 1:

Input: name = "alex", typed = "aaleex"
Output: true
Explanation: 'a' and 'e' in 'alex' were long pressed.
Example 2:

Input: name = "saeed", typed = "ssaaedd"
Output: false
Explanation: 'e' must have been pressed twice, but it wasn't in the typed output.
Example 3:

Input: name = "leelee", typed = "lleeelee"
Output: true
Example 4:

Input: name = "laiden", typed = "laiden"
Output: true
Explanation: It's not necessary to long press any character.
 

Note:

name.length <= 1000
typed.length <= 1000
The characters of name and typed are lowercase letters.
 
#### EXPLANATION:

首先可以确定的一点是：typed长度肯定比name的长度要长。那么就可以从这个角度出发：name的每个字符都需要找到对应的type字符。那么算法可以这样
1.拿出name中的每一个字符。
2.去typed中去寻找
3.找到的话就进行+1，这样继续第一步。
4.如果找到最后都没有找到就返回false
5.如果name还没有到最后，而typed已经到了最后说明少字符，可以直接返回false

#### SOLUTION:
```java
class Solution {
    public boolean isLongPressedName(String name, String typed) {
        int index = 0;
        for(int i = 0;i<name.length();i++){
            if(index>=typed.length()) return false;
            if(name.charAt(i)==typed.charAt(index)) {
                index++;
                continue;
            }
            while (typed.charAt(index)!=name.charAt(i) && index<typed.length()-1) index++;
            if(typed.charAt(index)==name.charAt(i))
                index++;
            else return false;
        }
        return true;
    }
}
```
