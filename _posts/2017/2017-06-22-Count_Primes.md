---
layout: post
title: 204. Count Primes
---

#### QUESTION:

**Description:**

Count the number of prime numbers less than a non-negative number, **n**.

**Credits:**
Special thanks to [@mithmatt](https://leetcode.com/discuss/user/mithmatt) for adding this problem and creating all test cases.

#### EXPLANATION:

具体的算法可以查看[这个视频](https://www.youtube.com/watch?v=eKp56OLhoQs)，其实就是筛选法的一种变形。

1.首先2是素数，所以可以把2的所有倍数都去除。

2.紧接着找到下一位，下一位就是3，同时去除3的倍数。

3.如此进行到

#### SOLUTION:

```java
public class Solution {
    public int countPrimes(int n) {
        if (n < 3)
            return 0;
            
        boolean[] f = new boolean[n];
        int count = n / 2;
        for (int i = 3; i * i < n; i += 2) {
            if (f[i])
                continue;
            
            for (int j = i * i; j < n; j += 2 * i) {
                if (!f[j]) {
                    --count;
                    f[j] = true;
                }
            }
        }
        return count;
    }
    
}
```

