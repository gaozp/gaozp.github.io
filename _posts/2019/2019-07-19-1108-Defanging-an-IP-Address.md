---
layout: post
title: 1108. Defanging an IP Address
categories: [leetcode]
---
#### QUESTION:
Given a valid (IPv4) IP address, return a defanged version of that IP address.

A defanged IP address replaces every period "." with "[.]".

 

Example 1:

Input: address = "1.1.1.1"
Output: "1[.]1[.]1[.]1"
Example 2:

Input: address = "255.100.50.0"
Output: "255[.]100[.]50[.]0"
 

Constraints:

The given address is a valid IPv4 address.
#### EXPLANATION:

题意：在点的周围加上中括号。
恩，这个就比较简单了。
1.遍历
2.找到'.'
3.添加中括号

#### SOLUTION:
```JAVA
class Solution {
    public String defangIPaddr(String address) {
        char[] chars = address.toCharArray();
        char[] result = new char[chars.length+6];
        for(int i=0,index = 0;i<chars.length;i++,index++){
            if(chars[i]=='.'){
                result[index] = '[';
                index++;
                result[index] = '.';
                index++;
                result[index] = ']';
            }else result[index] = chars[i];
        }
        return new String(result);
    }
}
```