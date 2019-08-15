---
layout: post
title: reverse string
categories: [leetcode]
---

####QUESTION:  
function that takes a string as input and returns the string reversed.

Example:
Given s = "hello", return "olleh".

####SOLUTION:  
    
    public String reverseString(String s) {
        char[] chars = s.toCharArray();
        for (int i = (chars.length-1)>>1;i>=0;--i){
            char tmp = chars[i];
            chars[i] = chars[chars.length-1-i];
            chars[chars.length-1-i]= tmp;
        }
        return new String(chars);
    }


####EXPLAIN:  
也没有什么需要解释的，从中间开始两边互换，(chars.length-1)>>1 获取到中间位置，然后就是左右互换的操作。

