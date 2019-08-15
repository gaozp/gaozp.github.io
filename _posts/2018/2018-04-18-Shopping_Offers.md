---
layout: post
title: 638. Shopping Offers
categories: [leetcode]
---

#### QUESTION:

In LeetCode Store, there are some kinds of items to sell. Each item has a price.

However, there are some special offers, and a special offer consists of one or more different kinds of items with a sale price.

You are given the each item's price, a set of special offers, and the number we need to buy for each item. The job is to output the lowest price you have to pay for **exactly** certain items as given, where you could make optimal use of the special offers.

Each special offer is represented in the form of an array, the last number represents the price you need to pay for this special offer, other numbers represents how many specific items you could get if you buy this offer.

You could use any of special offers as many times as you want.

**Example 1:**

```
Input: [2,5], [[3,0,5],[1,2,10]], [3,2]
Output: 14
Explanation: 
There are two kinds of items, A and B. Their prices are $2 and $5 respectively. 
In special offer 1, you can pay $5 for 3A and 0B
In special offer 2, you can pay $10 for 1A and 2B. 
You need to buy 3A and 2B, so you may pay $10 for 1A and 2B (special offer #2), and $4 for 2A.
```

**Example 2:**

```
Input: [2,3,4], [[1,1,0,4],[2,2,1,9]], [1,2,1]
Output: 11
Explanation: 
The price of A is $2, and $3 for B, $4 for C. 
You may pay $4 for 1A and 1B, and $9 for 2A ,2B and 1C. 
You need to buy 1A ,2B and 1C, so you may pay $4 for 1A and 1B (special offer #1), and $3 for 1B, $4 for 1C. 
You cannot add more items, though only $9 for 2A ,2B and 1C.
```

**Note:**

1. There are at most 6 kinds of items, 100 special offers.
2. For each item, you need to buy at most 6 of them.
3. You are **not** allowed to buy more items than you want, even if that would lower the overall price.

#### EXPLANATION:

其实在我看来，这并不能算是一个动态规划的问题，更像是一个回溯的问题。

只要把逻辑整理出来了就行了。

1.我们拿需要购买的数量去比对offer的数量如果能够满足就使用该

2.将数量减去进入下一个循环

3.如果数量不足够进行优惠，那么就直接买了。

然后计算最小值。

相当于一个回溯的算法。

#### SOLUTION:

```JAVA
class Solution {
    public int shoppingOffersResult = Integer.MAX_VALUE;
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        shoppingOffersHelper(price,special,needs,0);
        return shoppingOffersResult;
    }
    public void shoppingOffersHelper(List<Integer> price, List<List<Integer>> special, List<Integer> needs,int cost) {
        if(done(needs)){
            shoppingOffersResult = Math.min(cost,shoppingOffersResult);
        }else{
            int i;
            for(i = 0;i<special.size();i++){
                List<Integer> spe = special.get(i);
                List<Integer> cover = canCover(spe, needs);
                if (cover!=null) {
                    shoppingOffersHelper(price,special,cover,cost + spe.get(spe.size() - 1));
                }
            }
            if(i==special.size()){
                for(int j = 0;j<needs.size();j++) cost+=price.get(j)*needs.get(j);
                needs = new ArrayList<>();
                shoppingOffersHelper(price,special,needs,cost);
            }
        }
    }

    public boolean done(List<Integer> needs){
        for(int i =0;i<needs.size();i++){
            if(needs.get(i)!=0) return false;
        }
        return true;
    }

    public List<Integer> canCover(List<Integer> spe,List<Integer> need){
        List<Integer> result = new ArrayList<>();
        for(int i = 0;i<need.size();i++){
            int tmp = need.get(i) - spe.get(i);
            if(tmp<0) return null;
            result.add(tmp);
        }
        return result;
    }
}
```

