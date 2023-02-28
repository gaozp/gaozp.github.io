---
layout: post
title: 859. Buddy Strings
categories: [leetcode]
---
#### QUESTION:
Given two strings A and B of lowercase letters, return true if and only if we can swap two letters in A so that the result equals B.

 

Example 1:

Input: A = "ab", B = "ba"
Output: true
Example 2:

Input: A = "ab", B = "ab"
Output: false
Example 3:

Input: A = "aa", B = "aa"
Output: true
Example 4:

Input: A = "aaaaaaabc", B = "aaaaaaacb"
Output: true
Example 5:

Input: A = "", B = "aa"
Output: false
 

Note:

0 <= A.length <= 20000
0 <= B.length <= 20000
A and B consist only of lowercase letters.
#### EXPLANATION:

开始的思路是：
1.找到不一样的两个位置
2.进行调整
3.查看是否可以
结果发现无法覆盖到 “aa” 和 “aa”的情况，那么这种情况只能进行穷举了，于是就进行了穷举，结果发现是可以过了，但是发现得到了timelimitexception。
所以只能将两者结合起来了，结果过了，而且还是用时比较少的。

#### SOLUTION:
```JAVA
class Solution {
    public boolean buddyStrings(String A, String B) {
                if(A.length()!=B.length()) return false;
        if(A.equals(B)){
            for(int i = 0;i<A.length();i++){
                for(int j = i+1;j<A.length();j++){
                    String tmp = buddyStringsHelper(A,i,j);
                    if(tmp.equals(B)) return true;
                }
            }
            return false;
        }else {
            int start = -1;
            int end = -1;
            int length = Math.min(A.length(),B.length());
            for(int i = 0;i<length;i++){
                if(A.charAt(i)!=B.charAt(i)){
                    if(start==-1)
                        start=i;
                    else end=i;
                }
            }
            if(start!=-1 && end!=-1){
                A = buddyStringsHelper(A,start,end);
                if(A.equals(B)) return true;
            }
            return false;
        }
    }
    
    public static String buddyStringsHelper(String s,int start,int end){
        char[] chars = s.toCharArray();
        char tmp = chars[start];
        chars[start] = chars[end];
        chars[end] = tmp;
        return new String(chars);
    }
}
```