---
layout: post
title: 20. Valid Parentheses
categories: [leetcode]
---

#### QUESTION:

Given a string containing just the characters `'('`, `')'`, `'{'`, `'}'`, `'['` and `']'`, determine if the input string is valid.

The brackets must close in the correct order, `"()"` and `"()[]{}"` are all valid but `"(]"` and `"([)]"` are not.

#### EXPLANATION:

其实挺简单的，遇到配对的就把之前的出栈，否则就进栈就行。

#### SOLUTION:

```java
public class Solution {
    public boolean isValid(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> result = new Stack<>();
        for(char ch : chars){
            char tmp = result.isEmpty()?' ':result.peek();
            switch (ch){
                case ']':
                    if(tmp == '[')
                        result.pop();
                    else
                        result.push(ch);
                    break;
                case '}':
                    if(tmp == '{')
                        result.pop();
                    else
                        result.push(ch);
                    break;
                case ')':
                    if(tmp == '(')
                        result.pop();
                    else
                        result.push(ch);
                    break;
                default:
                    result.push(ch);
            }
        }
        return result.isEmpty();
    }
}
```

