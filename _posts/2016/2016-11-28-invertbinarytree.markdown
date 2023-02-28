---
layout: post
title: 226. Invert Binary Tree
categories: [leetcode]
---

####QUESTION:
Invert a binary tree.

     4
   /   \
  2     7
 / \   / \
1   3 6   9
to
     4
   /   \
  7     2
 / \   / \
9   6 3   1


####EXPLANATION:

也没有什么好解释的了，就是一个翻转二叉树的问题。

####SOLUTION:

    
    public TreeNode invertTree(TreeNode root) {
        if(root==null) return null;
        if (root.left == null && root.right == null) return root;
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
    


