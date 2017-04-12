---
layout: post
title: 441. Arranging Coins
---

#### QUESTION:

You have a total of *n* coins that you want to form in a staircase shape, where every *k*-th row must have exactly *k* coins.

Given *n*, find the total number of **full** staircase rows that can be formed.

*n* is a non-negative integer and fits within the range of a 32-bit signed integer.

**Example 1:**

```
n = 5

The coins can form the following rows:
¤
¤ ¤
¤ ¤

Because the 3rd row is incomplete, we return 2.

```

**Example 2:**

```
n = 8

The coins can form the following rows:
¤
¤ ¤
¤ ¤ ¤
¤ ¤

Because the 4th row is incomplete, we return 3.
```

#### EXPLANATION:

这其实就是一个求根公式：具体是这么演变的：

1+2+3+4+5+6+7…+k=n;

k(k+1)/2=n;

k^2+k-2n = 0;

求k的值，当然了，只需要取正值就可以了。

但是这个里面是有一个坑在的：

__8.0*n是必须这样写的。__

如果你写成8\*n这样的话，会自动封箱成int类型，而这个值已经超过了int类型的范围，所以是一个不准确的值了。只有在8.0\*n这样写，会自动封箱成double类型，是一个准确的值。

#### SOLUTION:

```java
public class Solution {
    public int arrangeCoins(int n) {
        return (int)((Math.sqrt(1+8.0*n)-1)/2);
    }
}
```

