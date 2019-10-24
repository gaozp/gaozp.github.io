---
layout: post
title: 1111. Maximum Nesting Depth of Two Valid Parentheses Strings
categories: [leetcode]
---
#### QUESTION:
A string is a valid parentheses string (denoted VPS) if and only if it consists of "(" and ")" characters only, and:

It is the empty string, or
It can be written as AB (A concatenated with B), where A and B are VPS's, or
It can be written as (A), where A is a VPS.
We can similarly define the nesting depth depth(S) of any VPS S as follows:

depth("") = 0
depth(A + B) = max(depth(A), depth(B)), where A and B are VPS's
depth("(" + A + ")") = 1 + depth(A), where A is a VPS.
For example,  "", "()()", and "()(()())" are VPS's (with nesting depths 0, 1, and 2), and ")(" and "(()" are not VPS's.

 

Given a VPS seq, split it into two disjoint subsequences A and B, such that A and B are VPS's (and A.length + B.length = seq.length).

Now choose any such A and B such that max(depth(A), depth(B)) is the minimum possible value.

Return an answer array (of length seq.length) that encodes such a choice of A and B:  answer[i] = 0 if seq[i] is part of A, else answer[i] = 1.  Note that even though multiple answers may exist, you may return any of them.

 

Example 1:

Input: seq = "(()())"
Output: [0,1,1,1,1,0]
Example 2:

Input: seq = "()(())()"
Output: [0,0,0,1,1,0,1,1]
 

Constraints:

1 <= seq.size <= 10000

#### EXPLANATION:
这道题目题意比较大，你首先需要理解题目定义的一些东西，然后才能找到规律。题意就不说了。思路就是采用贪心算法，思路是：  
1. 首先需要找出每个位置的深度，然后还得知道最大深度是多少。  
2. 在知道最大深度的情况下，采用贪心算法，要求max（a,b）那么只要a，b两个都是最小就可以了。因为已经知道了最大深度，那么a，b最小的；情况就是两者都在maxvalue/2附近，这样求出来的max（a,b)就会是最小的，于是就将深度小于maxvale/2的赋值给a，剩下的赋值给b，这样就可以拿到最小的max(a,b)了。

#### SOLUTION:
```java
class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        Stack<Character> stack = new Stack<>();
        int[] depth = new int[seq.length()];
        int maxValue = 0;
        for(int i = 0;i<seq.length();i++){
            if(seq.charAt(i)=='('){
                stack.push(seq.charAt(i));
                maxValue = Math.max(stack.size(),maxValue);
                depth[i] = stack.size();
            }else{
                maxValue = Math.max(stack.size(),maxValue);
                depth[i] = stack.size();
                stack.pop();
            }
        }
        for(int i = 0 ;i<depth.length;i++){
            if(depth[i]<=maxValue/2) depth[i] =1;
            else depth[i] = 0;
        }
        return depth;
    }
}
```
