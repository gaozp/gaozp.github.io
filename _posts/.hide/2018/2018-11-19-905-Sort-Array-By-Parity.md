---
layout: post
title: 905. Sort Array By Parity
categories: [leetcode]
---

#### QUESTION:

Given an array `A` of non-negative integers, return an array consisting of all the even elements of `A`, followed by all the odd elements of `A`.

You may return any answer array that satisfies this condition.

**Example 1:**

```
Input: [3,1,2,4]
Output: [2,4,3,1]
The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.
```

**Note:**

1. `1 <= A.length <= 5000`
2. `0 <= A[i] <= 5000`

#### EXPLANAITION:

其实这道题就是双指针问题。

用一个指针来表示偶数，一个指针来表示奇数。

一个从头开始往后寻找，一个从后往前寻找。

找到对应的之后，就将两者进行对调。

这样就结束了循环。

#### SOLUTION:

```java
class Solution {
    public int[] sortArrayByParity(int[] A) {
        int evenNumber = 0;
        int oddNumber = A.length-1;
        while (evenNumber<oddNumber){
            while ((A[evenNumber]&1)!=1 && evenNumber<A.length-1) evenNumber++;
            while ((A[oddNumber]&1)==1 && oddNumber>0) oddNumber--;
            if(evenNumber<oddNumber){
                swap(A,evenNumber,oddNumber);
                evenNumber++;oddNumber--;
            }
        }
        return A;
    }
    public static void swap(int[] A,int a,int b){
        int tmp = A[a];
        A[a] = A[b];
        A[b] = tmp;
    }
}
```

