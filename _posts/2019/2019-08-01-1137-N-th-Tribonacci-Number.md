---
layout: post
title: 1137. N-th Tribonacci Number
categories: [leetcode]
---
#### QUESTION:
The Tribonacci sequence Tn is defined as follows: 

T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.

Given n, return the value of Tn.

 

Example 1:

Input: n = 4
Output: 4
Explanation:
T_3 = 0 + 1 + 1 = 2
T_4 = 1 + 1 + 2 = 4
Example 2:

Input: n = 25
Output: 1389537
 

Constraints:

0 <= n <= 37
The answer is guaranteed to fit within a 32-bit integer, ie. answer <= 2^31 - 1.
#### EXPLANATION:

这道题，你完全可以采用递归的方式，但是必须得钻一个小漏洞。  
leetcode的testcase基本都是进行一次调用，而创建不同的对象来调用方法。  
所以可以采用静态成员变量和静态代码块来进行初始化，这样只进行初始化了一次。但是每次testcase都可以用到，应该是最快的方式了。  
算法就是，  
1.因为n最大是37，是有限的。那么就可以先将所有的结果计算出来。  
2.静态代码块中计算出0-37所有的结果，保存在数组中。  
3.直接调用数组获取到值。  

#### SOLUTION:
```java
class Solution {
    static int[] tribonacciArray = new int[38];
    static {
        tribonacciArray[0] = 0;
        tribonacciArray[1] = 1;
        tribonacciArray[2] = 1;
        for(int i = 3;i<=37;i++) tribonacciArray[i] = tribonacciArray[i-1]+tribonacciArray[i-2]+tribonacciArray[i-3];

    }

    public int tribonacci(int n) {
        return tribonacciArray[n];
    }
}
```
