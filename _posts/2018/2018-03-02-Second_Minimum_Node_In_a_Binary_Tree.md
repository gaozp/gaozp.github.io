---
layout: post
title: 671. Second Minimum Node In a Binary Tree
categories: [leetcode]
---

#### QUESTION:

Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree has exactly `two`or `zero` sub-node. If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes.

Given such a binary tree, you need to output the **second minimum** value in the set made of all the nodes' value in the whole tree.

If no such second minimum value exists, output -1 instead.

**Example 1:**

```
Input: 
    2
   / \
  2   5
     / \
    5   7

Output: 5
Explanation: The smallest value is 2, the second smallest value is 5.

```

**Example 2:**

```
Input: 
    2
   / \
  2   2

Output: -1
Explanation: The smallest value is 2, but there isn't any second smallest value.

```

#### EXPLANATION:

这个思路比较简单

就是遍历之后找到第二个最小的数。看了下其他人写的

其中比较重要的信息就是 当前节点是左右两个节点中比较小的那个。

所以根节点肯定就是最小的，那么只需要寻找到第二个小的。

左右两个节点同时找最小的，再进行比较就可以。

#### SOLUTION:

```JAVA
class Solution {
    int smallest = 0;
    int secondSmallest = Integer.MAX_VALUE;
    public int findSecondMinimumValue(TreeNode root) {
        if(root==null) return -1;
        smallest = root.val;
        findSecondMinimumValueHelper(root);
        return secondSmallest==Integer.MAX_VALUE?-1:secondSmallest;
    }
    public void findSecondMinimumValueHelper(TreeNode root){
        if(root.val>smallest && root.val<secondSmallest) secondSmallest = root.val;
        if(root.left!=null) findSecondMinimumValueHelper(root.left);
        if(root.right!=null) findSecondMinimumValueHelper(root.right);
    }

}

    public int findSecondMinimumValue(TreeNode root) {

        if (root.left != null && root.right != null) {
            int left = root.left.val, right = root.right.val;
            if (root.left.val == root.val) {
                left = findSecondMinimumValue(root.left);
            }
            if (root.right.val == root.val) {
                right = findSecondMinimumValue(root.right);
            }
            if (left != -1 && right != -1) {
                return Math.min(left, right);
            }
            else return left == -1 ? right : left;
        }
        else return -1;
    }
```

