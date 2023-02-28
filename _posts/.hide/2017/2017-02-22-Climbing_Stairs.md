---
layout: post
title: 70. Climbing Stairs
categories: [leetcode]
---

#### QUESTION:

You are climbing a stair case. It takes *n* steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

**Note:** Given *n* will be a positive integer.

#### EXPLANATION:

其实就是斐波那契函数。

#### SOLUTION:

```java
public int climbStairs(int n) {
        if(n ==2) return 2;
        if(n ==1) return 1;
        if(n ==0) return 0;
        int one = 2;
        int two = 1;
        int result = 0;
        for (int i = 2; i < n; i++) {
            result = one+two;
            two = one;
            one = result;
        }
        return result;
    }
```

