---
layout: post
title: 461. Hamming Distance
categories: [leetcode]
---

#### QUESTION:

The [Hamming distance](https://en.wikipedia.org/wiki/Hamming_distance) between two integers is the number of positions at which the corresponding bits are different.

Given two integers x and y, calculate the Hamming distance.

**Note:**

0 ≤ x, y < 231.

**Example:**

**Input:** x = 1, y = 4

**Output:** 2

**Explanation:**

1   (0 0 0 1)

4   (0 1 0 0)

       ↑   ↑

The above arrows point to positions where the corresponding bits are different.  



#### EXPLANATION:

这个其实挺简单的，也没有什么好解释的了，看代码吧。

#### SOLUTION:

    public int hammingDistance(int x, int y) {
        String xs = Integer.toBinaryString(x);
        String ys = Integer.toBinaryString(y);
        int i = xs.length()-1;
        int j = ys.length()-1;
        int count = 0;
        while (i>=0||j>=0){
            char xc = i>=0?xs.charAt(i):'0';
            char yc = j>=0?ys.charAt(j):'0';
            if(xc!=yc)
                count++;
            i--;
            j--;
        }
        return count;
    }