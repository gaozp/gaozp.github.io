---
layout: post
title: 392. Is Subsequence
categories: [leetcode]
---
#### QUESTION:
Given a string s and a string t, check if s is subsequence of t.

You may assume that there is only lower case English letters in both s and t. t is potentially a very long (length ~= 500,000) string, and s is a short string (<=100).

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).

Example 1:
s = "abc", t = "ahbgdc"

Return true.

Example 2:
s = "axc", t = "ahbgdc"

Return false.

Follow up:
If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one to see if T has its subsequence. In this scenario, how would you change your code?

Credits:
Special thanks to @pbrother for adding this problem and creating all test cases.
#### EXPLANATION:
我看了一下related topic：binary search，动态规划，还有贪心。其实我看完题目第一个想到的是动态规划，所以就采用了动态规划的方式来进行。  
逻辑：  
1. 首先我们确定两个指针，一个a用来表示在s中的位置，一个b是t中的位置，确定结束的条件是t查找结束，或者已经找到全部s  
2. 然后我们再进行查找：首先需要找到s[a]的字符，在t[b]中，while循环直到找到或者到了t的末尾  
3. 如果能够找到就进行下一个循环，将a和b都+1进行递归  
#### SOLUTION:
```JAVA
class Solution {
    public boolean isSubsequenceResult = false;
    public boolean isSubsequence(String s, String t) {
        isSubsequenceHelper(s,t,0,0);
        return isSubsequenceResult;
    }
    
    public void isSubsequenceHelper(String s,String t,int anchor ,int target){
        if(isSubsequenceResult) return;
        if(target==s.length()){
            isSubsequenceResult = true;
            return;
        }
        if(anchor<t.length()){
            while (anchor <t.length() && t.charAt(anchor)!=s.charAt(target)) anchor++;
            if(anchor==t.length()) return;
            else
                isSubsequenceHelper(s,t,anchor+1,target+1);
        }
    }
}
```