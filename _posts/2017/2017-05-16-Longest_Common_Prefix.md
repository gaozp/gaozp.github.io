---
layout: post
title: 14. Longest Common Prefix
categories: [leetcode]
---

#### QUESTION:

Write a function to find the longest common prefix string amongst an array of strings.

#### EXPLANATION:

1.遍历数组中的每个字符串

2.遍历字符串中的每个char，判断相同的char，如果不相同直接返回。

注意两个边界情况即可。

#### SOLUTION:

```JAVA
public class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length==0) return "";
        StringBuilder sb = new StringBuilder(strs[0]);
        for(int i = 1;i<strs.length;i++){
            n:for(int j = 0;j<sb.toString().length();j++){
                if(j>strs[i].length()-1){
                    sb = new StringBuilder(strs[i].substring(0,j));
                    break n;
                }
                if(sb.charAt(j) == strs[i].charAt(j))
                    continue;
                else{
                    sb = new StringBuilder(strs[i].substring(0, j));
                    break n;
                }
            }
        }
        return sb.toString();
    }
}
```

