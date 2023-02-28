---
layout: post
title: 532. K-diff Pairs in an Array
categories: [leetcode]
---

#### QUESTION:

Given an array of integers and an integer **k**, you need to find the number of **unique** k-diff pairs in the array. Here a **k-diff** pair is defined as an integer pair (i, j), where **i** and **j** are both numbers in the array and their [absolute difference](https://en.wikipedia.org/wiki/Absolute_difference) is **k**.

**Example 1:**

```
Input: [3, 1, 4, 1, 5], k = 2
Output: 2
Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
Although we have two 1s in the input, we should only return the number of unique pairs.

```

**Example 2:**

```
Input:[1, 2, 3, 4, 5], k = 1
Output: 4
Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).

```

**Example 3:**

```
Input: [1, 3, 1, 5, 4], k = 0
Output: 1
Explanation: There is one 0-diff pair in the array, (1, 1).

```

**Note:**

1. The pairs (i, j) and (j, i) count as the same pair.
2. The length of the array won't exceed 10,000.
3. All the integers in the given input belong to the range: [-1e7, 1e7].

#### EXPLANATION:

其实第一个答案是达到时间上限的，后来又重新写了下，就是添加了break in的逻辑，因为在如果两者都达到了k的范围之内的话，其实就没有必要再计算了，这样可以节省很多的时间复杂度，但是这个地方还是有一个问题，就是break了之后，其实下一个循环i和i+1其实也是重复的操作。因为起码在j左右才会有k的差值。

#### SOLUTION:

```JAVA
public class Solution {
    public int findPairs(int[] nums, int k) {
        Arrays.sort(nums);
        HashSet<String> result = new HashSet<>();
        for (int i = 0; i < nums.length - 1; i++) {
            in:
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] - nums[i] > k)
                    break in;
                if (nums[j] - nums[i] == k) {
                    result.add(nums[i] + ":" + nums[j]);
                    if (k != 0)
                        result.add(nums[j] + ":" + nums[i]);
                }
            }
        }
        if (k != 0)
            return result.size() / 2;
        return result.size();
    }
}
```

