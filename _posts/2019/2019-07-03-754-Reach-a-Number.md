---
layout: post
title: 754. Reach a Number
categories: [leetcode]
---

#### QUESTION:
You are standing at position 0 on an infinite number line. There is a goal at position target.

On each move, you can either go left or right. During the n-th move (starting from 1), you take n steps.

Return the minimum number of steps required to reach the destination.

Example 1:
Input: target = 3
Output: 2
Explanation:
On the first move we step from 0 to 1.
On the second step we step from 1 to 3.
Example 2:
Input: target = 2
Output: 3
Explanation:
On the first move we step from 0 to 1.
On the second move we step  from 1 to -1.
On the third move we step from -1 to 2.
Note:
target will be a non-zero integer in the range [-10^9, 10^9].

#### EXPLANATION:

1.首先正数和负数的步数是一样的
2.需要找出大于target的第一个减去target的结果是偶数的
3.因为偶数的时候,你可以将结果一半进行翻转操作。
4.相当于这样的一个操作 ===---- 相交的点是0，最后的结果是target，而双线的部分就是 目前的 index-target的余数，然后再进行重叠，那么就能够得到结果了。

#### SOLUTION:
```java
class Solution {
    public int reachNumber(int target) {
        int step = 0,index = 0;
        target = Math.abs(target);
        while (index<target||(index-target)%2!=0){
            step++;
            index+=step;
        }
        return step;
    }
}
```