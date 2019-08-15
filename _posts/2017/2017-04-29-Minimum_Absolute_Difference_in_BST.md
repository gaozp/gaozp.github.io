---
layout: post
title: 530. Minimum Absolute Difference in BST
categories: [leetcode]
---

#### QUESTION:

Given a binary search tree with non-negative values, find the minimum [absolute difference](https://en.wikipedia.org/wiki/Absolute_difference) between values of any two nodes.

**Example:**

```
Input:

   1
    \
     3
    /
   2

Output:
1

Explanation:
The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).

```

**Note:** There are at least two nodes in this BST.

#### EXPLANATION:

诶，其实就是最简单的算法。

1.遍历数据存入到集合中

2.排序

3.计算出两个之间最小的差就可以。

#### SOLUTION:

```java
public class Solution {
    private List<Integer> minDiffResult = new ArrayList<>();
    public int getMinimumDifference(TreeNode root) {
        getMinimumDifferenceHelper(root);
        Collections.sort(minDiffResult);
        int result = -1;
        for(int i = 1;i<minDiffResult.size();i++){
            if(result == -1)
                result = Math.abs(minDiffResult.get(i-1)-minDiffResult.get(i));
            result = Math.min(result,Math.abs(minDiffResult.get(i-1)-minDiffResult.get(i)));
        }
        return result;
    }
    public void getMinimumDifferenceHelper(TreeNode root){
        if(root ==null) return;
        getMinimumDifferenceHelper(root.left);
        getMinimumDifferenceHelper(root.right);
        minDiffResult.add(root.val);
    }
}
```

