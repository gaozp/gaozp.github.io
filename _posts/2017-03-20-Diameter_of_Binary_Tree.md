---
layout: post
title: 543. Diameter of Binary Tree
---

#### QUESTION:

Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the **longest**path between any two nodes in a tree. This path may or may not pass through the root.

**Example:**
Given a binary tree 

```
          1
         / \
        2   3
       / \     
      4   5    

```

Return **3**, which is the length of the path [4,2,1,3] or [5,2,1,3].

**Note:** The length of path between two nodes is represented by the number of edges between them.

#### EXPLANATION:

1.每个节点的最远距离其实就是左边的最大深度+右边的最大深度

2.每个节点同时又会为上一个节点提供左或者右分支的最大深度+1的路径

#### SOLUTION:

```JAVA
public class Solution {
    public int max_depth = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        diameterOfBinaryTreeHelper(root);
        return max_depth;
    }
    public int diameterOfBinaryTreeHelper(TreeNode root){
        if(root ==null) return 0;
        int left = diameterOfBinaryTreeHelper(root.left);
        int right = diameterOfBinaryTreeHelper(root.right);
        int cur = left+right;
        max_depth = Math.max(cur,max_depth);
        return Math.max(left,right)+1;
    };
}
```

