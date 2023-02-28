---
layout: post
title: 1010. Pairs of Songs With Total Durations Divisible by 60
categories: [leetcode]
---
#### QUESTION:
In a list of songs, the i-th song has a duration of time[i] seconds. 

Return the number of pairs of songs for which their total duration in seconds is divisible by 60.  Formally, we want the number of indices i < j with (time[i] + time[j]) % 60 == 0.

 

Example 1:

Input: [30,20,150,100,40]
Output: 3
Explanation: Three pairs have a total duration divisible by 60:
(time[0] = 30, time[2] = 150): total duration 180
(time[1] = 20, time[3] = 100): total duration 120
(time[1] = 20, time[4] = 40): total duration 60
Example 2:

Input: [60,60,60]
Output: 3
Explanation: All three pairs have a total duration of 120, which is divisible by 60.
 

Note:

1 <= time.length <= 60000
1 <= time[i] <= 500

#### EXPLANATION:

一开始使用的time[i]+time[j]导致超时，于是就想想有没有其他的办法。
那么如果两个数整除60，那么可能的余数就是1~59。我们只需要记录下每个数的余数。那下一个数只需要对比一个余数就可以。
这样就可以将时间复杂度降低到O(N)。

#### SOLUTION:
```
class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        int[] index = new int[60];
        int result = 0;
        for(int i:time){
            result+= index[(600-i)%60];
            index[i%60]+=1;
        }
        return result;
    }
}
```