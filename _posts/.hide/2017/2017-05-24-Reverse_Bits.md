---
layout: post
title: 190. Reverse Bits
categories: [leetcode]
---

#### QUESTION:

Reverse bits of a given 32 bits unsigned integer.

For example, given input 43261596 (represented in binary as **00000010100101000001111010011100**), return 964176192 (represented in binary as **00111001011110000010100101000000**).

**Follow up**:
If this function is called many times, how would you optimize it?

Related problem: [Reverse Integer](https://leetcode.com/problems/reverse-integer/)

#### EXPLANATION:

其实java原生就是带有这个api的，但是还是看一下具体是如何做的把。

1.每次取出第i位的数字

2.与1进行&，然后再左移对应位数（这时候就相当于对该数字做了reverse的操作）

3.再与之前的结果进行或操作。得到最后的数字。

#### SOLUTION:

```java
public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        return Integer.reverse(n);
    }
}

public static int reverseBits(int n) {
        int reverseResult = 0;
        for (int rightShift = 0; rightShift < 32; rightShift++) {
            int temp = n >> rightShift;
            int keepDigit = temp & 1;
            keepDigit = keepDigit << (31 - rightShift);
            reverseResult = reverseResult | keepDigit;
        }
        return reverseResult;
    }
```

