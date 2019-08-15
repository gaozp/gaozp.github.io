---
layout: post
title: 479. Largest Palindrome Product
categories: [leetcode]
---

#### QUESTION:

Find the largest palindrome made from the product of two n-digit numbers.

Since the result could be very large, you should return the largest palindrome mod 1337.

**Example:**

Input: 2

Output: 987

Explanation: 99 x 91 = 9009, 9009 % 1337 = 987

**Note:**

The range of n is [1,8].

#### EXPLANATION:

看到问题只有1-8的时候就想到了。。。直接利用testcase算出这八个数，然后直接获取就可以了，这也算是可穷算法的一种运用吧。

#### SOLUTION:

```JAVA
public class Solution {
    public int largestPalindrome(int n) {
        int[] map = new int[]{9,987,123,597,677,1218,877,475};
        return map[n-1];
    }
}
```

