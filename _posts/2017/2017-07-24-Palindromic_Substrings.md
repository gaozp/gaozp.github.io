---
layout: post
title: 647. Palindromic Substrings
---

#### QUESTION:

Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

**Example 1:**

```
Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".

```

**Example 2:**

```
Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".

```

**Note:**

1. The input string length won't exceed 1000.

#### EXPLANATION:

就是每位的字母和后面的字母都进行回文的判断就可以，那样就是O(n\*n)的时间复杂度。

但是很多人会使用stringbuilder的reverse方法，其实不如自己实现来的快。具体可以看代码。

#### SOLUTION:

```JAVA
public class Solution {
    public int countSubstrings(String s) {
        int count = 0;
        char[] chars = s.toCharArray();
        for(int i = 0;i<chars.length;i++){
            for(int j = i;j<chars.length;j++){
                if(countSubStringHelper(chars,i,j))
                    count++;
            }
        }
        return count;
    }
    public boolean countSubStringHelper(char[] chars,int i,int j){
        while (i<j){
            if(chars[i]!=chars[j])
                return false;
            i++;
            j--;
        }
        return true;
    }
}
```

