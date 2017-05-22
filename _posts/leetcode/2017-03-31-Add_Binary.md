---
layout: post
title: 67. Add Binary
---

#### QUESTION:

Given two binary strings, return their sum (also a binary string).

For example,
a = `"11"`
b = `"1"`
Return `"100"`.

#### EXPLANATION:

其实也和之前的四则运算是一样的，但是总感觉二进制的会有更为简单的办法。

#### SOLUTION:

```JAVA
public class Solution {
    public String addBinary(String a, String b) {
        char[] aArray = a.toCharArray();
        char[] bArray = b.toCharArray();
        int aindex = aArray.length-1;int bIndex = bArray.length-1;
        int ai = 0;int bi = 0;int carry = 0;
        StringBuilder result = new StringBuilder();
        while (aindex>=0 || bIndex >= 0){
            ai = aindex >= 0? Integer.parseInt(aArray[aindex]+""):0;
            bi = bIndex >= 0? Integer.parseInt(bArray[bIndex]+""):0;
            int temp = ai+bi+carry;
            carry = temp / 2;
            temp = temp % 2;
            result.insert(0,temp);
            aindex--;bIndex--;
        }
        if(carry!=0)
            result.insert(0,1);
        return result.toString();
    }
}
```

