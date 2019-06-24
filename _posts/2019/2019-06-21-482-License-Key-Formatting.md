---
layout: post
title: 482. License Key Formatting
---
#### QUESTION:
You are given a license key represented as a string S which consists only alphanumeric character and dashes. The string is separated into N+1 groups by N dashes.

Given a number K, we would want to reformat the strings such that each group contains exactly K characters, except for the first group which could be shorter than K, but still must contain at least one character. Furthermore, there must be a dash inserted between two groups and all lowercase letters should be converted to uppercase.

Given a non-empty string S and a number K, format the string according to the rules described above.

Example 1:
Input: S = "5F3Z-2e-9-w", K = 4

Output: "5F3Z-2E9W"

Explanation: The string S has been split into two parts, each part has 4 characters.
Note that the two extra dashes are not needed and can be removed.
Example 2:
Input: S = "2-5g-3-J", K = 2

Output: "2-5G-3J"

Explanation: The string S has been split into three parts, each part has 2 characters except the first part as it could be shorter as mentioned above.
Note:
The length of string S will not exceed 12,000, and K is a positive integer.
String S consists only of alphanumerical characters (a-z and/or A-Z and/or 0-9) and dashes(-).
String S is non-empty.
#### EXPLANATION:

这个题目其实挺容易的。理解了题目的意思就可以。题意：将给定的字符串重新格式化成以K个为单位的大写字母。既然要反向获取，那就要使用stack的数据结构了。
1.将给定字符串放入到stack
2.stackpop出来，同时计算长度
3.达到k的时候，并且并不是最前面的时候添加‘-’
4.返回结果的字符串

#### SOLUTION:
```java
class Solution {
    public String licenseKeyFormatting(String S, int K) {
        Stack<Character> stack = new Stack<>();
        for(char c: S.toCharArray()){
            if(c=='-') continue;
            stack.add(c);
        }

        int index = 0;
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()){
            Character pop = stack.pop();
            sb.insert(0,pop);
            index++;
            if(index==K && !stack.isEmpty()){
                sb.insert(0,'-');
                index=0;
            }
        }
        return sb.toString().toUpperCase();
    }
}
```
