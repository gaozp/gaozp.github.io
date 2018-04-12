---
layout: post
title: 714. Best Time to Buy and Sell Stock with Transaction Fee
---

#### QUESTION:

Your are given an array of integers `prices`, for which the `i`-th element is the price of a given stock on day `i`; and a non-negative integer `fee` representing a transaction fee.

You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction. You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)

Return the maximum profit you can make.

**Example 1:**

```
Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
Output: 8
Explanation: The maximum profit can be achieved by:
Buying at prices[0] = 1Selling at prices[3] = 8Buying at prices[4] = 4Selling at prices[5] = 9The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
```

**Note:**

`0 < prices.length <= 50000`.

`0 < prices[i] < 50000`.

`0 <= fee < 50000`.

#### EXPLANATION:

这是一个dp的题目。

首先第i天，我们的收益可以分为：

1.我们手上持有股票  A[I]

2.我们手上不持有股票 B[I]

#####A[I]：

如果我们手上持有股票，持有股票又可以分为两种情况：

1.我们在第i天买入的，那么A[i] = B[i-1]-price[i]

2.我们在第i天持有，那么A[i] = A[i-1]

那么最大收益就是A[i] = MAX（A[i-1]，B[i-1]-price[i]）

##### B[I]

如果我们手上不持有股票，那么就可以分为两种情况：

1.第i天将手上的股票卖出去了 B[i] = A[i-1]+prict[i]-fee

2.继续保持没有股票 B[i] = B[i-1]

那么最大收益就是B[i] = MAX(A[i-1]+prict[i]-fee,B[i-1])



根据题目的要求，是要我们最后手上是没有股票的，那么就是B[price.length -1]

#### SOLUTION:

```JAVA
class Solution {
    public int maxProfit(int[] prices, int fee) {
        int cash = 0, hold = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            cash = Math.max(cash, hold + prices[i] - fee);
            hold = Math.max(hold, cash - prices[i]);
        }
        return cash;
    }
}
```

