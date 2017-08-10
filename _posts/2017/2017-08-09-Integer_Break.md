---
layout: post
title: 343. Integer Break
---

#### QUESTION:

Given a positive integer *n*, break it into the sum of **at least** two positive integers and maximize the product of those integers. Return the maximum product you can get.

For example, given *n* = 2, return 1 (2 = 1 + 1); given *n* = 10, return 36 (10 = 3 + 3 + 4).

**Note**: You may assume that *n* is not less than 2 and not larger than 58.

#### EXPLANATION:

其实通过几个实例的话，就可以得到3越多的话，就可以达到最后结果越大的状态。

具体的证明过程可以参考[这篇blog](http://blog.csdn.net/liyuanbhu/article/details/51198124)。

#### SOLUTION:

```java
public class Solution {
    public int integerBreak(int n) {
        if(n==2 || n ==3) return n-1;
        int result = 1;
        while (n>4){
            result*=3;
            n-=3;
        }
        return result*n;
    }
}
```

