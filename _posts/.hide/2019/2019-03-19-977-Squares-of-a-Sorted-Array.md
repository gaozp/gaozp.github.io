---
layout: post
title: 977. Squares of a Sorted Array
categories: [leetcode]
---

#### QUESTION:

Given an array of integers `A` sorted in non-decreasing order, return an array of the squares of each number, also in sorted non-decreasing order.

**Example 1:**

```
Input: [-4,-1,0,3,10]
Output: [0,1,9,16,100]
```

**Example 2:**

```
Input: [-7,-3,2,3,11]
Output: [4,9,9,49,121]
```

**Note:**

1. `1 <= A.length <= 10000`
2. `-10000 <= A[i] <= 10000`
3. `A` is sorted in non-decreasing order.

#### EXPLANATION

看下题目，总结下要点：

1.原始数组是升序排列

2.原始数组中有正负数

那么难点其实就在，正负数的比较在结果中的不同位置。如果都是正数，或者都是负数就很简单了。如都是正数直接按顺序就可以，负数按reverse就可以。现在难点就是正负数夹杂。转换一下想法就是，直接比较平方后的结果。因为平方的结果就是两边大，中间小。所以从两边向中间进行排序。这样也兼顾了都是正数和负数的情况。

#### SOLUTION:

```JAVA
class Solution {
    public int[] sortedSquares(int[] A) {
        int[] result = new int[A.length];
        int i = 0;
        int j = A.length-1;
        int index = A.length-1;
        while (index>=0){
            int left = A[i]*A[i];
            int right = A[j]*A[j];
            if(left>right){
                result[index] = left;
                i++;
            }else {
                result[index] = right;
                j--;
            }
            index--;
        }
        return result;
    }
}
```

