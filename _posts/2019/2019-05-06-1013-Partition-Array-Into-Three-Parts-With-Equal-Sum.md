---
layout: post
title: 1013. Partition Array Into Three Parts With Equal Sum
---

#### QUESTION:

Given an array `A` of integers, return `true` if and only if we can partition the array into three **non-empty** parts with equal sums.

Formally, we can partition the array if we can find indexes `i+1 < j` with `(A[0] + A[1] + ... + A[i] == A[i+1] + A[i+2] + ... + A[j-1] == A[j] + A[j-1] + ... + A[A.length - 1])`

**Example 1:**

```
Input: [0,2,1,-6,6,-7,9,1,2,0,1]
Output: true
Explanation: 0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1
```

**Example 2:**

```
Input: [0,2,1,-6,6,7,9,-1,2,0,1]
Output: false
```

**Example 3:**

```
Input: [3,3,6,5,-2,2,5,1,-9,4]
Output: true
Explanation: 3 + 3 = 6 = 5 - 2 + 2 + 5 + 1 - 9 + 4
```

**Note:**

1. `3 <= A.length <= 50000`
2. `-10000 <= A[i] <= 10000`

#### EXPLANATION:

比较关键的条件是：`i+1 < j` with `(A[0] + A[1] + ... + A[i] == A[i+1] + A[i+2] + ... + A[j-1] == A[j] + A[j-1] + ... + A[A.length - 1])`

#### SOLUTION:

```JAVA
class Solution {
    public boolean canThreePartsEqualSum(int[] A) {
        int sum = IntStream.of(A).sum();
        int target = 0;
        if(sum%3==0) target = sum/3;
        else return false;
        int turn = 0,tmp =0;
        for(int i = 0;i<A.length;i++){
            tmp+=A[i];
            if(tmp==target) {
                turn++;
                tmp=0;
            }
        }
        return turn==3;
    }
}
```

