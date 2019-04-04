---
layout: post
title: 908. Smallest Range I
---

#### QUESTION:

Given an array `A` of integers, for each integer `A[i]` we may choose any `x` with `-K <= x <= K`, and add `x` to `A[i]`.

After this process, we have some array `B`.

Return the smallest possible difference between the maximum value of `B` and the minimum value of `B`.

**Example 1:**

```
Input: A = [1], K = 0
Output: 0
Explanation: B = [1]
```

**Example 2:**

```
Input: A = [0,10], K = 2
Output: 6
Explanation: B = [2,8]
```

**Example 3:**

```
Input: A = [1,3,6], K = 3
Output: 0
Explanation: B = [3,3,3] or B = [4,4,4]
```

**Note:**

1. `1 <= A.length <= 10000`
2. `0 <= A[i] <= 10000`
3. `0 <= K <= 10000`

#### EXPLANATION:

题目的意思读懂了就很简单，比较迷惑的一点是x这个值并不是固定的，从第二个例子就可以看出来。

那么问题其实就简化成了：

result = (A[max]-k)-(A[min]+k);

result = A[max]-A[min]-2k

就是判断中间的distance和2k的距离。

那么逻辑清楚了，就很容易了。

#### SOLUTION:

```java
class Solution {
    public int smallestRangeI(int[] A, int K) {
        if(A.length<=1) return 0;
        Arrays.sort(A);//此处有优化空间，因为只需要计算出最大值和最小值，所以排序反而增加了复杂度。
        int distance = Math.abs(A[0]-A[A.length-1]);
        if(distance<=2*K) return 0;
        else return distance - 2*K;
    }
}
```

