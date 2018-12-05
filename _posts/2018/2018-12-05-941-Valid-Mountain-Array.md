---
layout: post
title: 941. Valid Mountain Array
---

#### QUESTION:

Given an array `A` of integers, return `true` if and only if it is a *valid mountain array*.

Recall that A is a mountain array if and only if:

- `A.length >= 3`

- There exists some

  ```
  i
  ```

  with 

  ```
  0 < i < A.length - 1
  ```

   such that:

  - `A[0] < A[1] < ... A[i-1] < A[i]`
  - `A[i] > A[i+1] > ... > A[B.length - 1]`

**Example 1:**

```
Input: [2,1]
Output: false
```

**Example 2:**

```
Input: [3,5,5]
Output: false
```

**Example 3:**

```
Input: [0,3,2,1]
Output: true
```

**Note:**

1. `0 <= A.length <= 10000`
2. `0 <= A[i] <= 10000 `

#### EXPLANATION:

我的解决办法并不是特别的好，我的思路是

1.首先肯定是升序排列的，我们先顶一个order = false来标记为升序

2.如果碰到降序的地方，那么就标记为true。

3.如果在降序的过程中，又发现了升序的操作，那么就有2个峰值，直接返回false

上述算法漏掉了两个特殊的情况，那就是，连续升序和连续降序的情况，这种情况就需要单独拿出来处理。

4.如果第一开始不是升序，或者最后不是降序，那么就同样返回false

看了最后的提交结果后，发现其实有更加容易的解决办法。

#### SOLUTION:

```java
    public static boolean validMountainArray(int[] A) {
        if(A.length<3) return false;
        boolean order = false;
        int tmp = A[0];
        for(int i = 1;i<A.length-1;i++){
            if(A[i]==tmp) return false;
            if(order&&A[i]>=tmp) return false;
            else if(!order && A[i]<tmp){
                order = true;
            }
            tmp = A[i];
        }
        if(A[1]<A[0] || A[A.length-1]>A[A.length-2]) return false;
        return order;
    }
//比较容易理解的解法
    public boolean validMountainArray(int[] A) {
        int left = 0, right = A.length - 1;
            while(left + 1 < A.length && A[left] < A[left + 1]){
                left++;
            }
            while(right > 0 && A[right] < A[right - 1]){
                right--;
            }
        return left > 0 && left == right && right < A.length - 1;
    }
```

