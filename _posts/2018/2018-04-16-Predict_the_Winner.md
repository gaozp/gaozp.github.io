---
layout: post
title: 486. Predict the Winner
categories: [leetcode]
---

#### QUESTION:

Given an array of scores that are non-negative integers. Player 1 picks one of the numbers from either end of the array followed by the player 2 and then player 1 and so on. Each time a player picks a number, that number will not be available for the next player. This continues until all the scores have been chosen. The player with the maximum score wins.

Given an array of scores, predict whether player 1 is the winner. You can assume each player plays to maximize his score.

**Example 1:**

```
Input: [1, 5, 2]
Output: False
Explanation: Initially, player 1 can choose between 1 and 2. 
If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will be left with 1 (or 2). 
So, final score of player 1 is 1 + 2 = 3, and player 2 is 5. 
Hence, player 1 will never be the winner and you need to return False.
```

**Example 2:**

```
Input: [1, 5, 233, 7]
Output: True
Explanation: Player 1 first chooses 1. Then player 2 have to choose between 5 and 7. No matter which number player 2 choose, player 1 can choose 233.
Finally, player 1 has more score (234) than player 2 (12), so you need to return True representing player1 can win.
```

**Note:**

1. 1 <= length of the array <= 20.
2. Any scores in the given array are non-negative integers and will not exceed 10,000,000.
3. If the scores of both players are equal, then player 1 is still the winner.

#### EXPLANATION:

这是一个动态规划的问题。

首先我们从最简单的情况考虑：

如果只有一个数，那么肯定就是p1胜利。

如果有两个数呢，那么也肯定是p1胜利，为啥呢，因为p1可以选择max(num(0),num(1))

如果有三个数呢，那么就可以分解成 要选择 0位置，还是2位置，那么究竟该怎么选择呢，如果选择了0，那么p2就会选择1,2位置的最优解，选择了2，那p2就会选择0，1位置最优解，而1，2位置的最优解我们可以得到，那就是两个数。所以就可以得到递推公式：

f(0,n) = max(num[0]-f(1,n),num[n]-f(0,n-1))

既然有了递推公式了，那么就很容易能写出来了，代码如下。

#### SOLUTION:

```JAVA
class Solution {
    public boolean PredictTheWinner(int[] nums) {
        int[] dp = new int[nums.length];
        for(int s = nums.length;s>=0;s--){
            for(int e = s+1;e<nums.length;e++){
                int a = nums[e]-dp[e-1];
                int b = nums[s]-dp[e]; 
                dp[e] = Math.max(a,b);
            }
        }
        return dp[nums.length-1]>=0;
    }
}
```

