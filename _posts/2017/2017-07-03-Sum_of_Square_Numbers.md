---
layout: post
title: 633. Sum of Square Numbers
---

#### QUESTION:

Given a non-negative integer `c`, your task is to decide whether there're two integers `a` and `b` such that a2 + b2 = c.

**Example 1:**

```
Input: 5
Output: True
Explanation: 1 * 1 + 2 * 2 = 5

```

**Example 2:**

```
Input: 3
Output: False
```

#### EXPLANATION:

思路的话其实就是a平方加上b平方的结果可以分解成：

因为a是平方的，所以a = 1+3+5+7.。。。b也是这样。

那么就可以获取到一个最大的b，然后b开始减，然后让a开始加，直到最后就行。

#### QUESTION:

```java
public class Solution {
    public boolean judgeSquareSum(int c) {
        if(c==0) return true;
        long i = 1;
        long sumi = 1;
        while (sumi<c-1){
            i+=2;
            sumi+=i;
        }
        if(sumi == c)
            return true;
        long j = 1;
        long sumj = 1;
        while (sumi>=sumi/2 && i>=1){
            while (sumi+sumj<c){
                j+=2;
                sumj+=j;
            }
            if(sumi+sumj==c)
                return true;
            sumi-=i;
            i-=2;
        }
        return false;
    }
}


    public static boolean judgeSquareSum(int c) {
        if (c < 0) {
            return false;
        }
        int left = 0, right = (int)Math.sqrt(c);
        while (left <= right) {
            int cur = left * left + right * right;
            if (cur < c) {
                left++;
            } else if (cur > c) {
                right--;
            } else {
                return true;
            }
        }
        return false;
    }
```

