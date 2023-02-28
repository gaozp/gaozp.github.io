---
layout: post
title: 110. Balanced Binary Tree
categories: [leetcode]
---

#### QUESTION:

Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of *every* node never differ by more than 1.

#### EXPLANATION:

首先求出两边树的高度，然后进行对比。

这种方法的话会重复查询很多次子数的高度。所以还有第二种办法。

第二种是采用了后序遍历，如果有一个子树不是平衡二叉树，那么整个树也就不是了，所以第二种解法就避免了很多不必要的重复遍历。

#### SOLUTION:

```java
public class Solution {
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        int left = treeHeight(root.left);
        int right = treeHeight(root.right);
        if(Math.abs(left-right)>1)
            return false;
        return isBalanced(root.left)&&isBalanced(root.right);
    }
    public int treeHeight(TreeNode root){
        if(root == null)
            return 0;
        int left = treeHeight(root.left);
        int right = treeHeight(root.right);
        return left>right?left+1:right+1;
    }
}


public class Solution {
    public boolean isBalanced(TreeNode root) {
        return treeHeight(root) != -1;
    }
    public int treeHeight(TreeNode root){
        if(root == null)return 0;
        int left = treeHeight(root.left);
        if(left ==-1) return -1;
        int right = treeHeight(root.right);
        if(right == -1) return -1;
        if(Math.abs(left-right)>1) return -1;
        return Math.max(left,right)+1;
    }
}
```

