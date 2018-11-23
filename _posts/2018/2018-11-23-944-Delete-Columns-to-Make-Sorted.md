---
layout: post
title: 944. Delete Columns to Make Sorted
---

#### QUESTION:

We are given an array `A` of `N` lowercase letter strings, all of the same length.

Now, we may choose any set of deletion indices, and for each string, we delete all the characters in those indices.  The remaining rows of strings form columns when read north to south.

For example, if we have an array `A = ["``abcdef``","uvwxyz"]`and deletion indices `{0, 2, 3}`, then the final array after deletions is `["``bef``"``,"``vyz``"]`, and the remaining columns of `A`are `["b"``,"``v"]`, `["e","y"]`, and `["f","z"]`.  (Formally, the `c`-th column is `[A[0][c], A[1][c], ..., A[A.length-1][c]]`.)

Suppose we chose a set of deletion indices `D` such that after deletions, each remaining column in A is in **non-decreasing** sorted order.

Return the minimum possible value of `D.length`.

**Example 1:**

```
Input: ["cba","daf","ghi"]
Output: 1
Explanation: 
After choosing D = {1}, each column ["c","d","g"] and ["a","f","i"] are in non-decreasing sorted order.
If we chose D = {}, then a column ["b","a","h"] would not be in non-decreasing sorted order.
```

**Example 2:**

```
Input: ["a","b"]
Output: 0
Explanation: D = {}
```

**Example 3:**

```
Input: ["zyx","wvu","tsr"]
Output: 3
Explanation: D = {0, 1, 2}
```

**Note:**

1. `1 <= A.length <= 100`
2. `1 <= A[i].length <= 1000`

#### EXPLANATION:

这道题的难度并不在于怎么解-- 而是在于怎么理解题目。

其实比较容易理解的方式就是：

每一列都要是升序的，否则就需要去掉。

那么就很容易了：

1.循环每一列

2.取出每一列的每个字母进行比较

3.如果降序了就+1

4.返回最终的结果

#### SOLUTION:

```java
class Solution {
    public int minDeletionSize(String[] A) {
        if(A.length==0) return 0;
        int result = 0;
        int length = A[0].length();
        for (int i = 0; i < length; i++) {
            char tmp = 96;
            inner:
            for (int j = 1; j < A.length; j++) {
                char c = A[0].charAt(i);
                if (c >= tmp)
                    tmp = c;
                else {
                    result++;
                    break inner;
                }
            }
        }
        return result;
    }
}
```

