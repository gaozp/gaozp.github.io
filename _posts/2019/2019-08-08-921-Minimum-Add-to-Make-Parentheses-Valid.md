---
layout: post
title: 921. Minimum Add to Make Parentheses Valid
---
#### QUESTION:
Given a string S of '(' and ')' parentheses, we add the minimum number of parentheses ( '(' or ')', and in any positions ) so that the resulting parentheses string is valid.

Formally, a parentheses string is valid if and only if:

It is the empty string, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
Given a parentheses string, return the minimum number of parentheses we must add to make the resulting string valid.

 

Example 1:

Input: "())"
Output: 1
Example 2:

Input: "((("
Output: 3
Example 3:

Input: "()"
Output: 0
Example 4:

Input: "()))(("
Output: 4
 

Note:

S.length <= 1000
S only consists of '(' and ')' characters.
#### EXPLANATION:

这个题目和有一道题目重合了，其实，只要想到如果配对就可以，如果是(,那么就先保存，如果是),则看看前一个是不是(,如果是，那么就可以组合成一对，不是就需要添加。那么从这个逻辑就可以看出，其实我们需要的数据结构是stack，先进后出的结果。逻辑就是：
1.循环遍历  
2.如果是(,就进行压栈  
3.如果是),则判断前一个是否是(,是就将前一个出栈，因为已经组成，否则将这个)也压栈  
4.返回stack的size，也就是没法组成的情况  

#### SOLUTION:
```JAVA
class Solution {
    public int minAddToMakeValid(String S) {
        Stack<Character> stack = new Stack<>();
        char[] chars = S.toCharArray();
        for(int i = 0;i<chars.length;i++){
            if(chars[i]=='(') stack.push('(');
            else if(chars[i]==')'){
                if(stack.size()!=0 && stack.peek()=='(') stack.pop();
                else stack.push(')');
            }
        }
        return stack.size();
    }
}
```
