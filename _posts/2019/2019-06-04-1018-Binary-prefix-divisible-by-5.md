---
layout: post
title: 1018. Binary Prefix Divisible By 5
categories: [leetcode]
---

#### QUESTION:
Given an array A of 0s and 1s, consider N_i: the i-th subarray from A[0] to A[i] interpreted as a binary number (from most-significant-bit to least-significant-bit.)

Return a list of booleans answer, where answer[i] is true if and only if N_i is divisible by 5.

Example 1:

Input: [0,1,1]
Output: [true,false,false]
Explanation: 
The input numbers in binary are 0, 01, 011; which are 0, 1, and 3 in base-10.  Only the first number is divisible by 5, so answer[0] is true.
Example 2:

Input: [1,1,1]
Output: [false,false,false]
Example 3:

Input: [0,1,1,1,1,1]
Output: [true,false,false,false,true,false]
Example 4:

Input: [1,1,1,0,1]
Output: [false,false,false,false,false]
 

Note:

1 <= A.length <= 30000
A[i] is 0 or 1

#### EXPLANATION:
这道题目看起来比较容易，但是需要注意integer的边界值的情况。
所以注意到这一点之后就不能直接进行转化。那么其实就可以看到，是需要进行一个一个的循环进行下去的，这样才能保证在范围之内。
算法就是：
当在右边添加一位时，如之前的是 2^i+2^i-1+...2^0 添加一位之后，就是前面的都需要重新乘以2，那么其实之前的余数也是乘以2，然后在加上这次的A[I]所以，算法就很容易写出来了。

#### SOLUTION:
```
class Solution {
    public List<Boolean> prefixesDivBy5(int[] A) {
        boolean[] template = new boolean[]{true,false,false,false,false};
        ArrayList<Boolean> result = new ArrayList<>();
        int carry = 0;
        for(int i = 0;i<A.length;i++){
            carry = (2*carry + A[i])%5;
            result.add(template[carry]);
        }
        return result;
    }
}
```