---
layout: post
title: 563. Binary Tree Tilt
---

#### QUESTION:

Given a binary tree, return the tilt of the **whole tree**.

The tilt of a **tree node** is defined as the **absolute difference** between the sum of all left subtree node values and the sum of all right subtree node values. Null node has tilt 0.

The tilt of the **whole tree** is defined as the sum of all nodes' tilt.

**Example:**

```
Input: 
         1
       /   \
      2     3
Output: 1
Explanation: 
Tilt of node 2 : 0
Tilt of node 3 : 0
Tilt of node 1 : |2-3| = 1
Tilt of binary tree : 0 + 0 + 1 = 1

```

**Note:**

1. The sum of node values in any subtree won't exceed the range of 32-bit integer.
2. All the tilt values won't exceed the range of 32-bit integer.

#### EXPLANATION:

一开始的时候题目看错了，以为只是左右两个数的减然后再累加就可以了。但是发现提交上去的结果不对，然后再重新查看了一下题目，才发现是左右子树的和的 差的绝对值。

那么也挺简单的：

1.如果root是null就返回0

2.左树的值，右树的值

3.将左右树差取绝对值，加入结果中

4.返回的左右两树的和加上本身的值，作为上一个结果

#### SOLUTION:

```java
public class Solution {
    public int findTilt(TreeNode root) {
        findTiltHelper(root);
        return  findTiltSum;
    }
    private int findTiltSum = 0;
    public int findTiltHelper(TreeNode root){
        if(root == null) return 0;
        int left = findTiltHelper(root.left);
        int right = findTiltHelper(root.right);
        int tmp = Math.abs(left - right);
        findTiltSum += tmp;
        return root.val + left + right;
    }
}
```

