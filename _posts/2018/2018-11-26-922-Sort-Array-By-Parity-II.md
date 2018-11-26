---
layout: post
title: 922. Sort Array By Parity II
---

#### QUESTION:

Given an array `A` of non-negative integers, half of the integers in A are odd, and half of the integers are even.

Sort the array so that whenever `A[i]` is odd, `i` is odd; and whenever `A[i]` is even, `i` is even.

You may return any answer array that satisfies this condition.

**Example 1:**

```
Input: [4,2,5,7]
Output: [4,5,2,7]
Explanation: [4,7,2,5], [2,5,4,7], [2,7,4,5] would also have been accepted. 
```

**Note:**

1. `2 <= A.length <= 20000`
2. `A.length % 2 == 0`
3. `0 <= A[i] <= 1000`

#### EXPLANATION:

我的想法就是一个循环，取出每个数然后填到对应的位置上，那么就需要两个指针来指代目前已经填到的位置。

所以就是：

1.取出数字，看是奇数还是偶数

2.填到对应的位置上

3.对应的坐标+2到下一个位置

4.重复1，2，3.直到结束

#### SOLUTION:

```java
class Solution {
    public int[] sortArrayByParityII(int[] A) {
        int[] result = new int[A.length];
        int even = 0;
        int odd = 1;
        for(int i = 0;i<A.length;i++){
            if(A[i]%2==0){
                result[even] = A[i];
                even+=2;
            } else {
                result[odd] = A[i];
                odd+=2;
            }
        }
        return  result;
    }
}
```

