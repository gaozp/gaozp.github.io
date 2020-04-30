---
layout: post
title: 1281. Subtract the Product and Sum of Digits of an Integer
categories: [leetcode]
---
#### QUESTION:
Given an integer number n, return the difference between the product of its digits and the sum of its digits.
 

Example 1:
```
Input: n = 234
Output: 15 
Explanation: 
Product of digits = 2 * 3 * 4 = 24 
Sum of digits = 2 + 3 + 4 = 9 
Result = 24 - 9 = 15
```
Example 2:
```
Input: n = 4421
Output: 21
Explanation: 
Product of digits = 4 * 4 * 2 * 1 = 32 
Sum of digits = 4 + 4 + 2 + 1 = 11 
Result = 32 - 11 = 21
 ```

Constraints:
```
1 <= n <= 10^5
```
#### EXPLANATION:
按照题意来即可  
思路：
1. 首先将n转化成char数组
2. 创建product和sum两个临时变量用来存放结果
3. 遍历char数组，同时计算product和sum
4. 返回product-sum

#### SOLUTION:
```java
    public static int subtractProductAndSum(int n) {
        String s = Integer.toString(n, 10);
        int product = 1;
        for(char c: s.toCharArray())
            product *= Integer.parseInt(c+"");
        int sum = 0;
        for(char c: s.toCharArray())
            sum += Integer.parseInt(c+"");
        return product-sum;
    }
```
