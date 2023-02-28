---
layout: post
title: 624. Maximum Distance in Arrays
categories: [leetcode]
---

#### QUESTION:

Given `m` arrays, and each array is sorted in ascending order. Now you can pick up two integers from two different arrays (each array picks one) and calculate the distance. We define the distance between two integers `a` and `b` to be their absolute difference `|a-b|`. Your task is to find the maximum distance.

**Example 1:**

```
Input: 
[[1,2,3],
 [4,5],
 [1,2,3]]
Output: 4
Explanation: 
One way to reach the maximum distance 4 is to pick 1 in the first or third array and pick 5 in the second array.

```

**Note:**

1. Each given array will have at least 1 number. There will be at least two non-empty arrays.
2. The total number of the integers in **all** the `m` arrays will be in the range of [2, 10000].
3. The integers in the `m` arrays will be in the range of [-10000, 10000].

#### EXPLANATION:

算法其实很简单：

1.将第一组数作为最大和最小值

2.后面的每组进行对比，最值肯定是在每组的最大值和曾经的最小值  还有 每组的最小值和曾经的最大值 之间产生的。同时，在这个时候的每组进行的都不会是选出同一组的值。

3.返回最值即可。

#### SOLUTION:

```java
public class Solution {
    public int maxDistance(List<List<Integer>> arrays) {
        int result = Integer.MIN_VALUE;
        int min = arrays.get(0).get(0);
        int max = arrays.get(0).get(arrays.get(0).size()-1);
        
        for(int i = 1;i<arrays.size();i++){
            result = Math.max(result,Math.abs(arrays.get(i).get(0)-max));
            result = Math.max(result,Math.abs(arrays.get(i).get(arrays.get(i).size()-1)-min));
            max = Math.max(max,arrays.get(i).get(arrays.get(i).size()-1));
            min = Math.min(min,arrays.get(i).get(0));
        }
        return result;
    }
}
```

