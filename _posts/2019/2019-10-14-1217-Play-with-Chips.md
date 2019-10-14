---
layout: post
title: 1217. Play with Chips
categories: [leetcode]
---
#### QUESTION:
There are some chips, and the i-th chip is at position chips[i].

You can perform any of the two following types of moves any number of times (possibly zero) on any chip:

Move the i-th chip by 2 units to the left or to the right with a cost of 0.
Move the i-th chip by 1 unit to the left or to the right with a cost of 1.
There can be two or more chips at the same position initially.

Return the minimum cost needed to move all the chips to the same position (any position).

 

Example 1:

Input: chips = [1,2,3]
Output: 1
Explanation: Second chip will be moved to positon 3 with cost 1. First chip will be moved to position 3 with cost 0. Total cost is 1.
Example 2:

Input: chips = [2,2,2,3,3]
Output: 2
Explanation: Both fourth and fifth chip will be moved to position two with cost 1. Total minimum cost will be 2.
 

Constraints:

1 <= chips.length <= 100
1 <= chips[i] <= 10^9
#### EXPLANATION:
这道题目真的题意很难理解。其实意思是：  
将chips中的所有chip替换到同一水平，需要多少cost。  
我们可以知道奇数换到奇数cost是0，而偶数换到偶数cost也是0，而奇偶互换cost是1.那么结果就是需要知道需要将所有奇数换到偶数还是偶数换到奇数，就是需要求偶数和奇数的个数。  
1.创建一个长度为2的数组来记录奇数和偶数的个数  
2.返回两者个数中比较小的那个  
#### SOLUTION:
```JAVA
    public static int minCostToMoveChips(int[] chips) {
        int[] tmp = new int[2];
        for(int chip:chips) tmp[chip%2]++;
        return Math.min(tmp[0],tmp[1]);
    }
```