---
layout: post
title: 387. First Unique Character in a String
---

####QUESTION:
Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

Examples:

s = "leetcode"
return 0.

s = "loveleetcode",
return 2.
Note: You may assume the string contain only lowercase letters.

####EXPLANATION:

就是简单的两个循环找到对应的值，注意因为会有位置重合的时候，注意排除。

####SOLUTION:

    
    public int firstUniqChar(String s) {
        char[] chars = s.toCharArray();
        for(int i = 0;i<chars.length;i++){
            boolean find = false;
            for(int j=0;j<chars.length;j++){
                if(i!=j&&chars[i]==chars[j]){
                    find = true;
                    break;
                }
            }
            if(!find)
                return i;
        }
        return -1;
    }
    


