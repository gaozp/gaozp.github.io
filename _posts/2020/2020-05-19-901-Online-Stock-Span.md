---
layout: post
title: 901. Online Stock Span
categories: [leetcode]
---
#### QUESTION:
Write a class StockSpanner which collects daily price quotes for some stock, and returns the span of that stock's price for the current day.

The span of the stock's price today is defined as the maximum number of consecutive days (starting from today and going backwards) for which the price of the stock was less than or equal to today's price.

For example, if the price of a stock over the next 7 days were [100, 80, 60, 70, 60, 75, 85], then the stock spans would be [1, 1, 1, 2, 1, 4, 6].

Example 1:
```
Input: ["StockSpanner","next","next","next","next","next","next","next"], [[],[100],[80],[60],[70],[60],[75],[85]]
Output: [null,1,1,1,2,1,4,6]
Explanation: 
First, S = StockSpanner() is initialized.  Then:
S.next(100) is called and returns 1,
S.next(80) is called and returns 1,
S.next(60) is called and returns 1,
S.next(70) is called and returns 2,
S.next(60) is called and returns 1,
S.next(75) is called and returns 4,
S.next(85) is called and returns 6.

Note that (for example) S.next(75) returned 4, because the last 4 prices
(including today's price of 75) were less than or equal to today's price.
```

Note:
```
Calls to StockSpanner.next(int price) will have 1 <= price <= 10^5.
There will be at most 10000 calls to StockSpanner.next per test case.
There will be at most 150000 calls to StockSpanner.next across all test cases.
The total time limit for this problem has been reduced by 75% for C++, and 50% for all other languages.
```
#### EXPLANATION:
题意就是回溯过去的价格，获取到连续低于今天的价格日期数，也就是上涨的天数。那其实就很容易思考了。想象一下股票的图，基本上每个高峰都会标识出来。结合实际我们就可以大致模拟出对应的算法。  
当遇到一个高峰的时候，我们就可以标记这个高峰。如果后面有比这个高峰更高的，那我们就可以取代这个高峰。  
思路： 
1. 定义一个stack，用来保存数组，数组的第一位保存price，第二位保存个数
2. 将今天的价格与stack中的最后一个进行对比
3. 如果比当前的小，那么就可以往前合并，res+之前的结果
4. 重复2-3步，直到遇到比今天price大的数，说明下降结束
5. 将当前结果保存在最后
6. 返回res

#### SOLUTION:
```java
class StockSpanner {

        Stack<int[]> stack = new Stack<>();

        public StockSpanner() {

        }

        public int next(int price) {
            int res = 1;
            while (!stack.isEmpty() && stack.peek()[0] <= price)
                res += stack.pop()[1];
            stack.push(new int[]{price, res});
            return res;
        }
}
```
