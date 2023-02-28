---
layout: post
title: 1033. Moving Stones Until Consecutive
categories: [leetcode]
---

#### QUESTION:

Three stones are on a number line at positions a, b, and c.

Each turn, you pick up a stone at an endpoint (ie., either the lowest or highest position stone), and move it to an unoccupied position between those endpoints.  Formally, let's say the stones are currently at positions x, y, z with x < y < z.  You pick up the stone at either position x or position z, and move that stone to an integer position k, with x < k < z and k != y.

The game ends when you cannot make any more moves, ie. the stones are in consecutive positions.

When the game ends, what is the minimum and maximum number of moves that you could have made?  Return the answer as an length 2 array: answer = [minimum_moves, maximum_moves]

 

Example 1:

Input: a = 1, b = 2, c = 5
Output: [1,2]
Explanation: Move the stone from 5 to 3, or move the stone from 5 to 4 to 3.
Example 2:

Input: a = 4, b = 3, c = 2
Output: [0,0]
Explanation: We cannot make any moves.
Example 3:

Input: a = 3, b = 5, c = 1
Output: [1,2]
Explanation: Move the stone from 1 to 4; or move the stone from 1 to 2 to 4.
 

Note:

1 <= a <= 100
1 <= b <= 100
1 <= c <= 100
a != b, b != c, c != a

#### EXPLANATION:

emmmmm这题不亏这么多点down的，压根就不能算一道算法题目。
更多的是像逻辑题目。
首先考虑到最小的步数，
1.首先是如果abc相近，那么就是0。
2.如果其中有两个相近1-2步，那么就是最小步数就是1步。
3.如果abc都离的很远，那么就是先将一个移动到另外一个旁边，然后再进行另外一个，那么就是2。
再考虑最大的步数：
1.abc相近，那么就是0
2.如果有两个相近，那么就是距离最远的离这两个的步数-1
3.如果有两个数相隔一位，那么就是距离最远的离这两个的步数-1，但是需要再走一步到中间
4.如果都不靠近，那么无论是往大端还是小端靠近，都需要c-b-1+b-a-1的步数

#### SOLUTION:
```java
class Solution {
    public int[] numMovesStones(int a, int b, int c) {
        int[] stones = new int[]{a,b,c};
        Arrays.sort(stones);
        a = stones[0];b = stones[1];c=stones[2];
        if(b-a==1 && c-b==1) return new int[]{0,0};
        if(b-a==1) return new int[]{1,c-b-1};
        if(c-b==1) return new int[]{1,b-a-1};
        if(b-a==2) return new int[]{1,c-b};
        if(c-b==2) return new int[]{1,b-a};
        return new int[]{2,c-b+b-a-2};
    }
}
```
