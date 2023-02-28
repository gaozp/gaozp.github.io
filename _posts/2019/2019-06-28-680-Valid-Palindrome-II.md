---
layout: post
title: 680. Valid Palindrome II
categories: [leetcode]
---

#### QUESTION:
Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.

Example 1:
Input: "aba"
Output: True
Example 2:
Input: "abca"
Output: True
Explanation: You could delete the character 'c'.
Note:
The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
#### EXPLANATION:

题目思路比较简单：
既然最多移除一位，那么就可以分成两种情况来。
1.首先首尾进行对比，找到不相同的那一位。分成两种情况。
2.去掉首的，查看剩下的是否还是回文。
3.去掉尾的，查看剩下的是否还是回文。
4.只要有一个是可行的，那么就返回true。

#### SOLUTION:
```java
class Solution {
    public boolean validPalindrome(String s) {
        char[] chars = s.toCharArray();
        int start = 0,end = chars.length-1;
        while (start<end){
            if(chars[start]!=chars[end]) break;
            start++;end--;
        }
        if(start>=end) return true;
        int tmpStart = start+1,tmpEnd = end;
        boolean resultA = true;
        while (tmpStart<tmpEnd){
            if(chars[tmpStart]!=chars[tmpEnd]){
                resultA = false;
                break;
            }
            tmpStart++;tmpEnd--;
        }
        tmpStart = start;tmpEnd = end-1;
        boolean resultB = true;
        while (tmpStart<tmpEnd){
            if(chars[tmpStart]!=chars[tmpEnd]){
                resultB = false;
                break;
            }
            tmpStart++;tmpEnd--;
        }
        return resultA|resultB;
    }
}
```