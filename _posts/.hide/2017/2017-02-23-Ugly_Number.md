---
layout: post
title: 263. Ugly Number
categories: [leetcode]
---

#### QUESTION:

Write a program to check whether a given number is an ugly number.

Ugly numbers are positive numbers whose prime factors only include `2, 3, 5`. For example, `6, 8` are ugly while `14` is not ugly since it includes another prime factor `7`.

Note that `1` is typically treated as an ugly number.

#### EXPLANATION:

就是不断的除以这三个数，但是有可能会有timelimit问题出来。

#### SOLUTION:

```java
public boolean isUgly(int num) {
        for (int i=2; i<6 && num>0; i++)
            while (num % i == 0)
                num /= i;
            return num == 1;
    }
```

