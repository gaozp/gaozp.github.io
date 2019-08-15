---
layout: post
title: 172. Factorial Trailing Zeroes
categories: [leetcode]
---

#### QUESTION:

Given an integer *n*, return the number of trailing zeroes in *n*!.

**Note: **Your solution should be in logarithmic time complexity.

#### EXPLANATION:

本来使用的是最土的方式，就是计算出n的阶乘然后计算，但是题目中要求的是log级别的时间复杂度。

所以思路是这样：

n = 2： 1*2 结果是0

n=5 : 1\*2\*3\*2\*2\*5 结果是1个

n=11：结果是2个

于是得到规律，有几个5就有几个0，但是同时也会有25这种 5\*5的情况，除以5了就会多出一个5，这个时候就需要加上这个，所以得到最后的结果就是如solutions所示。

#### SOLUTION:

```java
public class Solution {
    public int trailingZeroes(int n) {
        return n==0?0:n/5+trailingZeroes(n/5);
    }
}
```

