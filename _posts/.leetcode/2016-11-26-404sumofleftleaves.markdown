---
layout: post
title: 404. Sum of Left Leaves
---


####QUESTION:
Find the sum of all left leaves in a given binary tree.

Example:

    3
   / \
  9  20
    /  \
   15   7

There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.


####EXPLAINATION:

该问题需要注意的地方其实就是根节点其实是不算的，需要注意区分左右节点的问题。

####SOLUTION:

    public int sumOfLeftLeaves(TreeNode root) {
        if(root==null) return 0;
        if(root.left!=null&& root.left.left==null&& root.left.right==null){
            return root.left.val+ sumOfLeftLeaves(root.right);
        }
        return sumOfLeftLeaves(root.left)+sumOfLeftLeaves(root.right);
    }
    


