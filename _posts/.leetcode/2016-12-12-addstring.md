---
layout: post
title: 415. Add Strings
---

#### QUESTION:

Given two non-negative numbers num1 and num2 represented as string, return the sum of num1 and num2.

**Note:**

1. The length of both num1 and num2 is < 5100.
2. Both num1 and num2 contains only digits 0-9.
3. Both num1 and num2 does not contain any leading zero.
4. You **must not use any built-in BigInteger library** or **convert the inputs to integer** directly.



#### EXPLANATION:

其实一开始比较难想到的就是不能使用任何的库和转换，那么只能通过计算获取到当前的char代表的数字，然后通过10进制的加法，就可以了，之前也做过二进制和十六进制的，其实也不是问题，还是转换的问题。直接用char减去‘0’就可以获取到了，这样其实后面的算法就比较简单了，相加，加上之前的进位，得到的结果对10取余装入结果，进位计算出来就行。

#### SOLUTION:

```JAVA
public String addStrings(String num1, String num2) {
        int carry = 0;
        char[] num1array = num1.toCharArray();
        char[] num2array = num2.toCharArray();
        int i = num1array.length-1;int j=num2array.length-1;
        StringBuilder sb = new StringBuilder();
        while (i>=0||j>=0||carry==1){
            int a = i>=0 ? num1array[i--]-'0':0;
            int b = j>=0 ? num2array[j--]-'0':0;
            int sum = a+b+carry;
            sb.insert(0,sum%10);
            carry = sum / 10;
        }
        return sb.toString();
    }
```

