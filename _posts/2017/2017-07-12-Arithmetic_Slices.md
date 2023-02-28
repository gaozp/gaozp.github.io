---
layout: post
title: 413. Arithmetic Slices
categories: [leetcode]
---

#### QUESTION:

A sequence of number is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.

For example, these are arithmetic sequence:

```
1, 3, 5, 7, 9
7, 7, 7, 7
3, -1, -5, -9
```

The following sequence is not arithmetic.

```
1, 1, 2, 5, 7
```

A zero-indexed array A consisting of N numbers is given. A slice of that array is any pair of integers (P, Q) such that 0 <= P < Q < N.

A slice (P, Q) of array A is called arithmetic if the sequence:
A[P], A[p + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q.

The function should return the number of arithmetic slices in the array A.

**Example:**

```
A = [1, 2, 3, 4]

return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.
```

#### EXPLANATION:

1.首先判断出三个数是一个等差数列。

2.等差数列后的每增加一个数，如果和之前的差相同，那么就也是等差数列。



但是我感觉还有更简单的办法。比如1，2，3，4这个数列，其实第一个数i=0循环完的时候，count是2，第二次循环的时候2，3，4也是一个，所以结果是3，但是明显的2，3，4其实已经是在第一次循环的时候进行过了。

所以第一个完全可以算出来的，同时可以把第一层循环的i值直接拉到j，节省循环。

#### SOLUTION:

```JAVA
public class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        int count = 0;
        for(int i = 0;i<A.length-2;i++){
            if(A[i]-A[i+1] != A[i+1]-A[i+2])
                continue;
            count++;
            in: for(int j = i+3;j<A.length;j++){
                if(A[j]-A[j-1] != A[j-1]-A[j-2])
                    break in;
                count++;
            }
        }
        return count;
    }
}
```

