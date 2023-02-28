---
layout: post
title: 374. Guess Number Higher or Lower
categories: [leetcode]
---

#### QUESTION:

We are playing the Guess Game. The game is as follows:

I pick a number from **1** to **n**. You have to guess which number I picked.

Every time you guess wrong, I'll tell you whether the number is higher or lower.

You call a pre-defined API `guess(int num)` which returns 3 possible results (`-1`, `1`, or `0`):

```
-1 : My number is lower
 1 : My number is higher
 0 : Congrats! You got it!

```

**Example:**

```
n = 10, I pick 6.

Return 6.
```

#### EXPLANATION:

1.第一个坑是：其实第一次的时候是把题目的 guess 反回的值理解反了

2.第二个坑是：int类型的数据可能会溢出



看了下其他人的discuss，python的都不用管int溢出的问题，真是羡慕。

#### SOLUTION:

```java
/* The guess API is defined in the parent class GuessGame.
   @param num, your guess
   @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
      int guess(int num); */

public class Solution extends GuessGame {
    public int guessNumber(int n) {
            int low = 1,high = n;
            while (low<high){
                int tmp = low+(high-low)/2;
                int result = guess(tmp);
                if(result == 1)
                    low = tmp+1;
                if (result == -1)
                    high = tmp;
                if(result==0)
                    return tmp;
            }
            return low;
    }
}
```

