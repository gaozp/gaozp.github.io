---
layout: post
title: 201. Bitwise AND of Numbers Range
categories: [leetcode]
---

#### QUESTION:

Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

For example, given the range [5, 7], you should return 4.

#### EXPLANATION:

思路就是每个数都有32位，每一位进行对比，那么如果有一个是0，那么整个就是0了。所以时间复杂度就是32*n

也就是O(n)，也算是线性的时间复杂度了。但是提交了之后还是会有时间上限的问题。

然后思路就是如果n的最高位比m的最高位还需要左移1位，那么其实就肯定是0了。这个时候又有问题了。这个时候TLE问题倒是解决了，int越界的问题又出来了。

于是就添加了Integer.max_value的判断。在ac之后，发现了大神的解决方案。

1.大神的思路用了递归的思想，这个就不说了。

2.很关键的一点是：如果越界，那么两个数字一起越界，那么他们也是可以相等的。

#### SOLUTION:

```JAVA
class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        if(Integer.toBinaryString(n).length()>Integer.toBinaryString(m).length()) return 0;
        if(m==n) return n;
        int result = 0;
        for (int j = 0; j < 32; j++) {
            boolean flag = false;
            for (int i = m; i< Integer.MAX_VALUE && i<= n;i++) {//解决int值越界的问题
                if (((i >>> j) & 1) == 0) {
                    flag = true;
                    break;
                }
            }
            if (!flag) result+= 1<<j;
        }
        return result;
    }
}

//大神的解决方案
class Solution {
  public int rangeBitwiseAnd(int m, int n) {
    return (n > m) ? (rangeBitwiseAnd(m >>> 1, n >>> 1) << 1) : m;
  }
}
```

