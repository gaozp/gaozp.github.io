---
layout: post
title: 1221. Split a String in Balanced Strings
categories: [leetcode]
---
#### QUESTION:
Balanced strings are those who have equal quantity of 'L' and 'R' characters.

Given a balanced string s split it in the maximum amount of balanced strings.

Return the maximum amount of splitted balanced strings.

 

Example 1:

Input: s = "RLRRLLRLRL"
Output: 4
Explanation: s can be split into "RL", "RRLL", "RL", "RL", each substring contains same number of 'L' and 'R'.
Example 2:

Input: s = "RLLLLRRRLR"
Output: 3
Explanation: s can be split into "RL", "LLLRRR", "LR", each substring contains same number of 'L' and 'R'.
Example 3:

Input: s = "LLLLRRRR"
Output: 1
Explanation: s can be split into "LLLLRRRR".
Example 4:

Input: s = "RLRRRLLRLL"
Output: 2
Explanation: s can be split into "RL", "RRRLLRLL", since each substring contains an equal number of 'L' and 'R'
 

Constraints:

1 <= s.length <= 1000
s[i] = 'L' or 'R'
#### EXPLANATION:
easy的题目，看到这种配对的，可以直接考虑采用stack的数据结构。  
在配对的时候，出栈一个，然后查看stack的size，如果是0，则说明已经配对完成，需要将result++；
#### SOLUTION:
```java
class Solution {
    public int balancedStringSplit(String s) {
        if(s.length()==0) return 0;
        int result = 0;
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        stack.push(chars[0]);
        for(int i = 1;i<chars.length;i++){
            if(stack.size()==0 || chars[i]==stack.peek())
                stack.push(chars[i]);
            else
                stack.pop();
            if(stack.size()==0) result++;
        }
        return result;
    }
}
```
