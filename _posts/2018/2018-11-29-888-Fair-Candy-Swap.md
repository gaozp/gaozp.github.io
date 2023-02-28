---
layout: post
title: 888. Fair Candy Swap
categories: [leetcode]
---

#### QUESTION:

Alice and Bob have candy bars of different sizes: `A[i]` is the size of the `i`-th bar of candy that Alice has, and `B[j]` is the size of the `j`-th bar of candy that Bob has.

Since they are friends, they would like to exchange one candy bar each so that after the exchange, they both have the same total amount of candy.  (*The total amount of candy a person has is the sum of the sizes of candy bars they have.*)

Return an integer array `ans` where `ans[0]` is the size of the candy bar that Alice must exchange, and `ans[1]` is the size of the candy bar that Bob must exchange.

If there are multiple answers, you may return any one of them.  It is guaranteed an answer exists. 

**Example 1:**

```
Input: A = [1,1], B = [2,2]
Output: [1,2]
```

**Example 2:**

```
Input: A = [1,2], B = [2,3]
Output: [1,2]
```

**Example 3:**

```
Input: A = [2], B = [1,3]
Output: [2,3]
```

**Example 4:**

```
Input: A = [1,2,5], B = [2,4]
Output: [5,4]
```

**Note:**

- `1 <= A.length <= 10000`
- `1 <= B.length <= 10000`
- `1 <= A[i] <= 100000`
- `1 <= B[i] <= 100000`
- It is guaranteed that Alice and Bob have different total amounts of candy.
- It is guaranteed there exists an answer.

#### EXPLANATION:

只要理解了题目意思就可以了。

题目是有A,B两个数组，交换数组中的哪两个数，会让两个数组的和相等。

并且能够保证一定存在这样的解。

那么其实问题就可以转化成 suma 和sumb的差值 

可以这样算：

sumA - A[i]+ B[j] = sumB - B[j]+A[i]

A[i]-B[j] = (sumA-sumB)/2

这样就得出了结果。

#### SOLUTION:

```
class Solution {
    public int[] fairCandySwap(int[] A, int[] B) {
        int[] result  = new int[2];
        int sumA = Arrays.stream(A).sum();
        int sumB = Arrays.stream(B).sum();
        int average = (sumA-sumB)/2;
        for(int i = 0;i<A.length;i++){
            for(int j = 0;j<B.length;j++){
                if((A[i]-B[j])==average){
                    result[0] = A[i];
                    result[1] = B[j];
                    return result;
                }
            }
        }
        return result;
    }
}
```

