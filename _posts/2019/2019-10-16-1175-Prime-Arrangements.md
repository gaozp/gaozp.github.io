---
layout: post
title: 1175. Prime Arrangements
categories: [leetcode]
---
#### QUESTION:
Return the number of permutations of 1 to n so that prime numbers are at prime indices (1-indexed.)

(Recall that an integer is prime if and only if it is greater than 1, and cannot be written as a product of two positive integers both smaller than it.)

Since the answer may be large, return the answer modulo 10^9 + 7.

 

Example 1:

Input: n = 5
Output: 12
Explanation: For example [1,2,5,4,3] is a valid permutation, but [5,2,3,4,1] is not because the prime number 5 is at index 1.
Example 2:

Input: n = 100
Output: 682289015
 

Constraints:

1 <= n <= 100
#### EXPLANATION:
题意是，求质数在质数位置，合数在合数位置的排列组合，那么就很容易可以得到质数个数i，合数个数j=（n-i),结果result = i*j%10000000007.
#### SOLUTION:
```JAVA
class Solution {
    static int[] PRIM_NUMS = new int[]{2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97};
    public int numPrimeArrangements(int n) {
        int prim_count = 0;
        for(int i : PRIM_NUMS){
            if(i<=n) prim_count++;
        }
        int compo_count = n-prim_count;
        long result = 1,tmp = 1000000007;
        for(int i = prim_count;i>1;i--) result=(result*i)%tmp;
        for(int i = compo_count;i>1;i--) result=(result*i)%tmp;
        return (int)result;
    }
}
```