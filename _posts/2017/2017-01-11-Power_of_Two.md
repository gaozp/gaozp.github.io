---
layout: post
title: 231. Power of Two
categories: [leetcode]
---

#### QUESTIONS:

Given an integer, write a function to determine if it is a power of two.

#### EXPLANATION:

其实和之前的power of three是一个流程，但是这个地方的2的倍数是可以直接使用位运算来进行的，这样就可以节省一点性能。

#### SOLUTION:

```java
    public boolean isPowerOfTwo(int n) {
        if(n==0) return false;
        if(n==1) return true;
        if(n%2==0){
            return isPowerOfTwo(n>>1);
        }else{
            return false;
        }
    }
```

