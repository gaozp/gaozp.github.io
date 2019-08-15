---
layout: post
title: 961. N-Repeated Element in Size 2N Array
categories: [leetcode]
---

#### QUESTION:

In a array `A` of size `2N`, there are `N+1` unique elements, and exactly one of these elements is repeated N times.

Return the element repeated `N` times.

**Example 1:**

```
Input: [1,2,3,3]
Output: 3
```

**Example 2:**

```
Input: [2,1,2,5,3,2]
Output: 2
```

**Example 3:**

```
Input: [5,1,5,2,5,3,5,4]
Output: 5
```

**Note:**

1. `4 <= A.length <= 10000`
2. `0 <= A[i] < 10000`
3. `A.length` is even

#### EXPLANATION:

诶，我把我一开始想到的方法写下来吧，9ms的ac解。

首先是一个n长度的窗口在整个数组中移动，那么只有3种可能，在最前面，最后面和中间。那么就返回这个数。

但是后来看了别人的解决方案，才发现，因为只有一个是重复的，那么直接用hashset来去重就可以了。其实是我想复杂了，想到了N这个数字上去了，而不是关注只有一个是重复的这个上。

#### SOLUTION:

```java
class Solution {
    public int repeatedNTimes(int[] A) {
        Arrays.sort(A);
        int a = A[A.length/2];
        int b = A[A.length/2-1];
        int c = A[A.length/2+1];
        int d = A[A.length/2-2];
        if(a==b)
            return a;
        if(a==c)
            return a;
        if(b==d)
            return b;
        return -1;
    }
}
```

