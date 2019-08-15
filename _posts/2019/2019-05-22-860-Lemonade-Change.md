---
layout: post
title: 860. Lemonade Change
categories: [leetcode]
---

#### QUESTION:

At a lemonade stand, each lemonade costs `$5`. 

Customers are standing in a queue to buy from you, and order one at a time (in the order specified by `bills`).

Each customer will only buy one lemonade and pay with either a `$5`, `$10`, or `$20` bill.  You must provide the correct change to each customer, so that the net transaction is that the customer pays $5.

Note that you don't have any change in hand at first.

Return `true` if and only if you can provide every customer with correct change.

**Example 1:**

```
Input: [5,5,5,10,20]
Output: true
Explanation: 
From the first 3 customers, we collect three $5 bills in order.
From the fourth customer, we collect a $10 bill and give back a $5.
From the fifth customer, we give a $10 bill and a $5 bill.
Since all customers got correct change, we output true.
```

**Example 2:**

```
Input: [5,5,10]
Output: true
```

**Example 3:**

```
Input: [10,10]
Output: false
```

**Example 4:**

```
Input: [5,5,10,10,20]
Output: false
Explanation: 
From the first two customers in order, we collect two $5 bills.
For the next two customers in order, we collect a $10 bill and give back a $5 bill.
For the last customer, we can't give change of $15 back because we only have two $10 bills.
Since not every customer received correct change, the answer is false.
```

**Note:**

- `0 <= bills.length <= 10000`
- `bills[i]` will be either `5`, `10`, or `20`.

#### EXPLANATION:

采用的是贪心算法，首先需要记录的点是，当前有多少的零钱。那么就可以采用数组或者直接用数字来标识剩余的钱。

情况就可以分为3种：

1.收到5元，那么就直接收下。

2.收到19元，找回5元

3.收到20，找零10+5 或者是5+5+5

采用贪心算法，如果有10元那么就使用10元的。

#### SOLUTION:

```java
class Solution {
    public boolean lemonadeChange(int[] bills) {
        int[] cash = new int[3];
        for(int i=0;i<bills.length;i++){
            switch (bills[i]){
                case 5:
                    cash[0]++;
                    break;
                case 10:
                    cash[0]--;
                    cash[1]++;
                    break;
                case 20:
                    if(cash[1]>0&&cash[0]>0){
                        cash[1]--;
                        cash[0]--;
                    }else {
                        cash[0]-=3;
                    }
                    cash[2]++;
                    break;
            }
            if(!lemonadeChangeHelper(cash)) return false;
        }
        return true;
    }
    
        public static boolean lemonadeChangeHelper(int[] cash){
        for (int item:cash) {
            if(item<0) return false;
        }
        return true;
    }
}
```

