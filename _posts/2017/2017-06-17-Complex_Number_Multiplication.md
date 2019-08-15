---
layout: post
title: 537. Complex Number Multiplication
categories: [leetcode]
---

#### QUESTION:

Given two strings representing two [complex numbers](https://en.wikipedia.org/wiki/Complex_number).

You need to return a string representing their multiplication. Note i2 = -1 according to the definition.

**Example 1:**

```
Input: "1+1i", "1+1i"
Output: "0+2i"
Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you need convert it to the form of 0+2i.

```

**Example 2:**

```
Input: "1+-1i", "1+-1i"
Output: "0+-2i"
Explanation: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.

```

**Note:**

1. The input strings will not have extra blank.
2. The input strings will be given in the form of **a+bi**, where the integer **a** and **b** will both belong to the range of [-100, 100]. And **the output should be also in this form**.

#### EXPLANATION:

其实就是找出数据然后加减就可以。具体代码就在下面，也没有什么特别的。

#### SOLUTION:

```JAVA
public class Solution {
    public String complexNumberMultiply(String a, String b) {
        String[] result = a.split("\\+");
        int m = Integer.parseInt(result[0]);
        int n = Integer.parseInt(result[1].split("i")[0]);
        result = b.split("\\+");
        int x = Integer.parseInt(result[0]);
        int y = Integer.parseInt(result[1].split("i")[0]);
        int i = m*x + n*y*(-1);
        int j = m*y + n*x;
        return i+"+"+j+"i";
    }
}
```

