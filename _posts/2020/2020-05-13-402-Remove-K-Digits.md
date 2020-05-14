---
layout: post
title: 402. Remove K Digits
categories: [leetcode]
---
#### QUESTION:
Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.

Note:
The length of num is less than 10002 and will be ≥ k.
The given num does not contain any leading zero.
Example 1:
```
Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
```
Example 2:
```
Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
```
Example 3:
```
Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.
```
#### EXPLANATION:
这道题一开始想到的肯定是贪心算法，那么怎么个贪心呢。我一开是想的是：首先移除一个数，比较剩下的数的大小，取最小的数，然后再移除一个数，取最小的值，不断重复，直到k=0时。但一提交却发现，数字的长度可以有10002位，那肯定会溢出的，只能说明这个贪心算法是不对的。所以得想另外一条路。  
那我们只能一步一步的预演来抽象这个问题。之前思路是一个一个的取，那么我们可不可以考虑一位一位的取呢，首先最高位是啥？因为要确定的是最小值，那么最高位肯定是值越小越好，但是还有一个条件，那就是只能抽取k个数，那么第一位，我们是不是可以说就是 0 -> chars.length - k 这些数字中最小的那个，那第二位就是 第一位的index<sub>0</sub> -> chars.length - k -1 的值。  
那么上面的逻辑其实就可以抽象为：

1. 我们先创建一个栈
2. 把第一个num.array的第一个数放在第一个
3. 第二个数到的时候，先判断长度是否还够往前挤，如果够的话，那么判断是否比前面的数小
4. 如果比前面的数小，就把该数往前挤，这样就能得到位置
5. 重复2-4步，直到整个数组被压缩到对应位置

#### SOLUTION:
```java
    public static String removeKdigits(String num, int k) {
        char[] chars = num.toCharArray();
        int remain = num.length() - k;
        char[] result = new char[remain];
        int index = 0;
        for(int i = 0;i<chars.length;i++){
            while ((chars.length - i > remain - index) && (index > 0 && chars[i]<result[index-1])) index--;
            if(index < remain) result[index++] = chars[i];
        }

        index = -1;
        while (++index<remain){
            if(result[index] != '0') break;
        }
        String s = new String(result).substring(index);
        return s.length() == 0 ? "0" : s;
    }
```
