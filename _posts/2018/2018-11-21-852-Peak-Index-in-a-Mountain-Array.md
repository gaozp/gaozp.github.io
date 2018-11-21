---
layout: post
title: 852. Peak Index in a Mountain Array
---

#### QUESTION:

Let's call an array `A` a *mountain* if the following properties hold:

- `A.length >= 3`
- There exists some `0 < i < A.length - 1` such that `A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1]`

Given an array that is definitely a mountain, return any `i` such that `A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1]`.

**Example 1:**

```
Input: [0,1,0]
Output: 1
```

**Example 2:**

```
Input: [0,2,1,0]
Output: 1
```

**Note:**

1. `3 <= A.length <= 10000`
2. `0 <= A[i] <= 10^6`
3. A is a mountain, as defined above.

#### EXPLANATION:

这个题目可以使用二分法进行：

首先找到一个中间的点，判断左右两边的大小。通过大小来判断应该往那边寻找。

1.如果是上行趋势，那么就将start放在这里

2.如果是吓醒趋势，那么就将end放在这里

直到找到start>end的时候就是已经找到了对应的点了。

代码也很简单。

#### SOLUTION:

```java
class Solution {
    public int peakIndexInMountainArray(int[] A) {
        int start = 0;
        int end = A.length - 1;
        int mid = A.length / 2;
        while (start < end) {
            if (A[mid] > A[mid - 1] && A[mid] > A[mid + 1])
                return mid;
            if (A[mid] > A[mid - 1] && A[mid] < A[mid + 1])
                start = mid;
            if (A[mid] < A[mid - 1] && A[mid] > A[mid + 1])
                end = mid;
            mid = (start + end) / 2;
        }
        return -1;
    }
}
```

