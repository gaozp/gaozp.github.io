---
layout: post
title: 326. Power of Three
categories: [leetcode]
---

#### QUESTION:

Given an integer, write a function to determine if it is a power of three.

**Follow up:**

Could you do it without using any loop / recursion?

#### EXPLANATION:

题目中要求了不能使用任何的循环或者迭代，但是第一个想到的还是迭代吧。说一下迭代的思路：如果是3的m次方的话，那么最后除以3出来的肯定就是1了，所以当n是1的是就是true了。同时n对3取余，如果余数是0的话，说明是可以整除的，那么就可以进入下一个循环，如果不能整除，那么就肯定不是3的m次方了。



accept之后又看了一下其他人的解决办法，具体可以参考[这篇讨论](https://discuss.leetcode.com/category/406/power-of-three) 。

#### SOLUTION:

```java
   public boolean isPowerOfThree(int n) {
        if(n==0)return false;
        if(n==1)return true;
        if(n%3==0){
            return isPowerOfThree(n/3);
        }else {
            return false;
        }
    }
```

