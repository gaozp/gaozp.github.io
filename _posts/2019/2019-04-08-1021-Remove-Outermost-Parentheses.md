---
layout: post
title: 1021. Remove Outermost Parentheses
categories: [leetcode]
---

#### QUESTION:

A valid parentheses string is either empty `("")`, `"(" + A + ")"`, or `A + B`, where `A` and `B` are valid parentheses strings, and `+` represents string concatenation.  For example, `""`, `"()"`, `"(())()"`, and `"(()(()))"` are all valid parentheses strings.

A valid parentheses string `S` is **primitive** if it is nonempty, and there does not exist a way to split it into `S = A+B`, with `A` and `B`nonempty valid parentheses strings.

Given a valid parentheses string `S`, consider its primitive decomposition: `S = P_1 + P_2 + ... + P_k`, where `P_i` are primitive valid parentheses strings.

Return `S` after removing the outermost parentheses of every primitive string in the primitive decomposition of `S`.

**Example 1:**

```
Input: "(()())(())"
Output: "()()()"
Explanation: 
The input string is "(()())(())", with primitive decomposition "(()())" + "(())".
After removing outer parentheses of each part, this is "()()" + "()" = "()()()".
```

**Example 2:**

```
Input: "(()())(())(()(()))"
Output: "()()()()(())"
Explanation: 
The input string is "(()())(())(()(()))", with primitive decomposition "(()())" + "(())" + "(()(()))".
After removing outer parentheses of each part, this is "()()" + "()" + "()(())" = "()()()()(())".
```

**Example 3:**

```
Input: "()()"
Output: ""
Explanation: 
The input string is "()()", with primitive decomposition "()" + "()".
After removing outer parentheses of each part, this is "" + "" = "".
```

 

**Note:**

1. `S.length <= 10000`
2. `S[i]` is `"("` or `")"`
3. `S` is a valid parentheses string

#### EXPLANATION:

其实这个问题就很简单，只要有一个思路就可以：

怎么样才能算一个primitive的string呢。那就是括号需要对应。怎么才能对应呢？

那就是n个"("对上n个")"，那结果就显而易见了。

遇到"("的时候，标记+1

遇到")"的时候，标记-1

当标记为0的时候，要不然是开始，要不然就是结束。

#### SOLUTION:

```JAVA
class Solution {
    public String removeOuterParentheses(String S) {
        int index = 0,start=0,end=0;
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<S.length();i++){
            if(index==0) start = i;
            char tmp = S.charAt(i);
            if(tmp=='(') index++;
            if(tmp==')') index--;
            if(index==0) {
                end = i;
                sb.append(S.substring(start+1,end));
            }
        }
        return sb.toString();
    }
}
```

