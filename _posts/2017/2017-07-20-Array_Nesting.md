---
layout: post
title: 565. Array Nesting
---

#### QUESTION:

A zero-indexed array A consisting of N different integers is given. The array contains all integers in the range [0, N - 1].

Sets S[K] for 0 <= K < N are defined as follows:

S[K] = { A[K], A[A[K]], A[A[A[K]]], ... }.

Sets S[K] are finite for each K and should NOT contain duplicates.

Write a function that given an array A consisting of N integers, return the size of the largest set S[K] for this array.

**Example 1:**

```
Input: A = [5,4,0,3,1,6,2]
Output: 4
Explanation: 
A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.

One of the longest S[K]:
S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}

```

**Note:**

1. N is an integer within the range [1, 20,000].
2. The elements of A are all distinct.
3. Each element of array A is an integer within the range [0, N-1].

#### EXPLANATION:

这道题目的话找到规律就可以了，就像例子一样：

s0 = a0 a5 a6 a2

s1 = a1 a4

s2 = a2 a0 a5 a6

s3 = a3

s4 = a4 a1

s5 = a5 a6 a2 a0

s6 = a6 a2 a0 a5

看出来了么，index相同的几个数总是在一个循环里面。所以，我们只要记录index就行，

所以将走过的key都设置为true，这样就可以避免重复的操作了。这个是不损坏原数据的基础上进行的，看了下别人的，大多数也是将走过的就直接设置成-1的情况。

#### SOLUTION:

```JAVA
public class Solution {
    public int arrayNesting(int[] nums) {
        boolean[] keys = new boolean[nums.length];
        int result = 0;
        for(int i = 0;i<nums.length;i++){
            int key = i;
            if(keys[i])
                continue;
            int count = 0;
            while (!keys[nums[key]]){
                key = nums[key];
                keys[key] = true;
                count++;
            }
            result = Math.max(result,count);
        }
        return result;
    }
}
```

