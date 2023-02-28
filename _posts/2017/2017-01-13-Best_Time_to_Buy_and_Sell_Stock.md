---
layout: post
title: 121. Best Time to Buy and Sell Stock
categories: [leetcode]
---

#### QUESTION:

Say you have an array for which the *i*th element is the price of a given stock on day *i*.

If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.

**Example 1:**

Input: [7, 1, 5, 3, 6, 4]

Output: 5

max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)

**Example 2:**

Input: [7, 6, 4, 3, 1]

Output: 0

In this case, no transaction is done, i.e. max profit = 0.



#### EXPLANATION:

其实本来是使用了双循环的，但是因为说是time limit，所以后来想了一下，只要一个循环就可以了，标记出最小的数字，然后往后循环，获取到当前价格的最大收益，保存起来，然后再进入下一个数字，这样一遍循环完就可以得到最后最大的收益了。

#### SOLUTION:

```java
    public int maxProfit(int[] prices) {
        if(prices.length==0) return 0;
        int min = prices[0];
        int result = 0;
        for(int i = 0;i<prices.length;i++){
            min = Math.min(min,prices[i]);
            result = Math.max(result,prices[i]-min);
        }
        return result;
    }
```

