---
layout: post
title: 1046. Last Stone Weight
---

#### QUESTION:

We have a collection of rocks, each rock has a positive integer weight.

Each turn, we choose the two **heaviest** rocks and smash them together.  Suppose the stones have weights `x` and `y` with `x <= y`.  The result of this smash is:

- If `x == y`, both stones are totally destroyed;
- If `x != y`, the stone of weight `x` is totally destroyed, and the stone of weight `y` has new weight `y-x`.

At the end, there is at most 1 stone left.  Return the weight of this stone (or 0 if there are no stones left.)

**Example 1:**

```
Input: [2,7,4,1,8,1]
Output: 1
Explanation: 
We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
we combine 1 and 1 to get 0 so the array converts to [1] then that's the value of last stone.
```

**Note:**

1. `1 <= stones.length <= 30`
2. `1 <= stones[i] <= 1000`

#### EXPLANATION:

其实我本来也没有想到这道题会这么简单。以为起码会有一个时间复杂度或者空间复杂度的限制。但是并没有。那么就比较简单了。

1.将数组排序

2.将最大的两个数进行smash

3.结果保存在倒数第二个

4.截取数组

5.反复进行到只剩最后一位。

可以优化的点：如果x==y，那么其实可以直接将两者都去掉。

#### SOLUTION:

```java
class Solution {
    public int lastStoneWeight(int[] stones) {
        while (stones.length!=1){
            Arrays.sort(stones);
            stones[stones.length-2] = stones[stones.length-1]-stones[stones.length-2];
            stones = Arrays.copyOf(stones,stones.length-1);
        }
        return stones[0];
    }
}
```

