---
layout: post
title: 477. Total Hamming Distance
categories: [leetcode]
---

#### QUESTION:

The [Hamming distance](https://en.wikipedia.org/wiki/Hamming_distance) between two integers is the number of positions at which the corresponding bits are different.

Now your job is to find the total Hamming distance between all pairs of the given numbers.

**Example:**

```
Input: 4, 14, 2

Output: 6

Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is 0010 (just
showing the four bits relevant in this case). So the answer will be:
HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.

```

**Note:**

1. Elements of the given array are in the range of `0 `to `10^9`
2. Length of the array will not exceed `10^4`.

#### EXPLANATION:

这道题如果用之前的异或解法的话肯定会得到timelimit exception。那么只能从另外的思路来想了。

一开始我的想法是用一个map，来存放已经计算过的，这样如果遇到已经计算的就可以直接取出来了，结果发现map的操作还没有直接算来的快。

后来就想到了：可以算出每一位的差别，然后加起来。

就拿例子来说：

4是0100 ，14是1110，2是0010. 那么第一位1的个数是1，那么差别就是1*2，因为14和其他两个是不同的。再算出第二位，只有2是0，与其他两个是不同的，那么也是2，第三位的也是2，第四位全相同就不说了，加起来就是6了。

所以最终的算法就是这样。

#### SOLUTION:

```java
public class Solution {
    public int totalHammingDistance(int[] nums) {
        int n = 31;
        int len = nums.length;
        int[] countOfOnes = new int[n];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < n; j++) {
                countOfOnes[j] += (nums[i] >> j) & 1;
            }
        }
        int sum = 0;
        for (int count: countOfOnes) {
            sum += count * (len - count);
        }
        return sum;
    }
    
}
```

