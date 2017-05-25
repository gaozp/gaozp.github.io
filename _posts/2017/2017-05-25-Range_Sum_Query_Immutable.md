---
layout: post
title: 303. Range Sum Query - Immutable
---

#### QUESTION:

Given an integer array *nums*, find the sum of the elements between indices *i* and *j* (*i* ≤ *j*), inclusive.

**Example:**

```
Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3

```

**Note:**

1. You may assume that the array does not change.
2. There are many calls to *sumRange* function.

#### EXPLANATION:

其实一开始以为很简单，直接就提交了for循环求值的，结果就发现了timelimit。想想也是，怎么可能会这么简单呢。

之后又想到了建一个table，每获取到一个值就存在里面，比如2-5，算出来之后就存在里面，下次再求0-1的时候再存在里面，当求0-5的时候就可以直接使用这两个数了。但是这样也有一个问题，需要考虑table的存取效率，以及有可能一个数是由好多个数组成的。最后就想到了如下的方法。

每个数都是（0-j）的和减去（0-i）的和。

所以就像下面一样，是可以ac的。

#### SOLUTION:

```java
    public class NumArray {
        int[] data;
        int[] tmp;
        public NumArray(int[] nums) {
            data = nums;
            tmp = new int[nums.length];
            for(int i = 0;i<nums.length;i++){
                tmp[i] = i>0?nums[i]+tmp[i-1]:nums[0];
            }
        }

        public int sumRange(int i, int j) {
            return i==0?tmp[j]:tmp[j]-tmp[i-1];
        }
    }
```

