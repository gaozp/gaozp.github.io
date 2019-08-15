---
layout: post
title: 667. Beautiful Arrangement II
categories: [leetcode]
---

#### QUESTION:

Given two integers `n` and `k`, you need to construct a list which contains `n` different positive integers ranging from `1` to `n` and obeys the following requirement: 
Suppose this list is [a1, a2, a3, ... , an], then the list [|a1 - a2|, |a2 - a3|, |a3 - a4|, ... , |an-1 - an|] has exactly `k` distinct integers.

If there are multiple answers, print any of them.

**Example 1:**

```
Input: n = 3, k = 1
Output: [1, 2, 3]
Explanation: The [1, 2, 3] has three different positive integers ranging from 1 to 3, and the [1, 1] has exactly 1 distinct integer: 1.
```

**Example 2:**

```
Input: n = 3, k = 2
Output: [1, 3, 2]
Explanation: The [1, 3, 2] has three different positive integers ranging from 1 to 3, and the [2, 1] has exactly 2 distinct integers: 1 and 2.
```

**Note:**

1. The `n` and `k` are in the range 1 <= k < n <= 10000.

#### EXPLANATION:

思路说一下：

从上面我们可以看出，如果有n个数，那么需要k个不同的数，正常应该是

k,k-1,k-2,k-3....

那么规律就来了，比如n=9，k=7的时候就是：

1，1+7，1+7-6；1+7-6+5

也就是总是等于前面一个数+或者-  k的递减数列。

然后再进入第二个循环.

这样，我们

#### SOLUTION:

```java
class Solution {
    public int[] constructArray(int n, int k) {
        int[] result = new int[n];
        int index= 0;
        int times = 0;
        while (index<n){//当前数组还没有结束
            int gap = 0;
            result[index] = 1+times*(k+1);//将每次循环第一位数进行填充
            index++;
            times++;//进入下一个循环
            if(result[index-1]+k<=n) gap = k;//如果gap还在n之内，那么就直接用k进行累减
            else gap = n-result[index-1];//如果gap已经在n之外了，那么我们只能用n-result[index-1]作为gap了
            boolean order = true;//标记当前需要做的是加还是减
            while (gap > 0 && index <n){//每次k为gap的循环
                result[index] = order? result[index-1]+gap:result[index-1]-gap;
                gap--;
                index++;
                order = !order;
            }
        }
        return result;
    }
}
```

