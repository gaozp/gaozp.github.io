---
layout: post
title: 567. Permutation in String
categories: [leetcode]
---
#### QUESTION:
Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.

 

Example 1:
```
Input: s1 = "ab" s2 = "eidbaooo"
Output: True
Explanation: s2 contains one permutation of s1 ("ba").
```
Example 2:
```
Input:s1= "ab" s2 = "eidboaoo"
Output: False
```

Note:
```
The input strings only contain lower case letters.
The length of both given strings is in range [1, 10,000].
```
#### EXPLANATION:
其实这道题和[438. Find All Anagrams in a String](http://gaozhipeng.me/posts/Find_All_Anagrams_in_a_String/)属于同一个题型，首先想到的肯定是创建一个数组，来标记目标字母的个数。那后面就需要对这个数组进行+-操作，如果数组中此时正好为0，并且长度也是一样，那么就说明是排列组合中的一个。  
思路：
1. 创建一个26位的数组，用来记录目标字符串的字符
2. 创建一个start，一个end，用来记录双指针，同时记录一个count，用来记录是否符合条件
3. 首先需要移动end指针，如果是目标字符，那么就将count--；
4. 当长度==目标长度的时候，我们就需要移动start指针，将start指针位置进行++，同时对count进行操作
5. 最后可以进行判断，如果count=0，就说明是找到了对应的，因为长度是一定的

#### SOLUTION:
```java
class Solution {
    public boolean checkInclusion(String p, String s) {
        int[] chars = new int[26];

        if (s == null || p == null || s.length() < p.length())
            return false;
        for (char c : p.toCharArray())
            chars[c-'a']++;

        int start = 0, end = 0, count = p.length();
        while (end < s.length()) {
            if (end - start == p.length() && chars[s.charAt(start++)-'a']++ >= 0)
                count++;
            if (--chars[s.charAt(end++)-'a'] >= 0)
                count--;
            if (count == 0)
                return true;
        }
        return false;
    }
}
```
