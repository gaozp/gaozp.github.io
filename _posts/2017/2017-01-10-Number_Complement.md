---
layout: post
title: 476. Number Complement
categories: [leetcode]
---

#### QUESTION:

Given a positive integer, output its complement number. The complement strategy is to flip the bits of its binary representation.

**Note:**

1. The given integer is guaranteed to fit within the range of a 32-bit signed integer.
2. You could assume no leading zero bit in the integer’s binary representation.

**Example 1:**

**Input:** 5

**Output:** 2

**Explanation:** The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So you need to output 2.

**Example 2:**

**Input:** 1

**Output:** 0

**Explanation:** The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you need to output 0.

#### EXPLANATION:

其实逻辑很简单，就是取反之后取到之前的位数，然后转化成数字就可以了，在网上看了下别人的方法，一行的好多，忽然觉得自己的位运算

#### SOLUTION:

```java
public int findComplement(int num) {
        String numstring = Integer.toBinaryString(num);
        String complementnumstring = Integer.toBinaryString(~num);
        String result = complementnumstring.substring(complementnumstring.length()-numstring.length(),complementnumstring.length());
        int intresult = Integer.valueOf(result,2);
        return intresult;
    }
```

