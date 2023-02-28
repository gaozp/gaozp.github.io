---
layout: post
title: 122. Best Time to Buy and Sell Stock II
categories: [leetcode]
---

#### QUESTION:

Say you have an array for which the *i*th element is the price of a given stock on day *i*.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

#### EXPLANATION:

其实就是[贪心算法](http://baike.baidu.com/link?url=UGT26YEme-4-Dr_KmQS3DGMRREZ5ehcc0royLJtpbVHXQ1x8Qdf-UuP-Juiy0PIfLMRGo9wS7B74qoXC4OkfjNs9kBTi3Y5OebJRaiQ4PQgNiemF4rnQjBiXdd2xaN4r#4_2)，但是贪心算法是需要证明其中的正确性的。

做了这些算法之后发现，其实很多的算法问题只是数学问题，如果数学能够学的好的话，这些应该还是可以简单的。

#### SOLUTION:

```java
public int maxProfit(int[] prices) {
        int result = 0;
        if (prices == null || prices.length == 1) return result;
        for (int i = 1; i < prices.length; i++) {
            result = prices[i]-prices[i-1] >0?result+prices[i]-prices[i-1]:result;
        }
        return result;
    }
```

