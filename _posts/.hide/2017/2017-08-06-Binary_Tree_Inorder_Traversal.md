---
layout: post
title: 94. Binary Tree Inorder Traversal
categories: [leetcode]
---

#### QUESTION:

Given a binary tree, return the *inorder* traversal of its nodes' values.

For example:
Given binary tree `[1,null,2,3]`,

```
   1
    \
     2
    /
   3

```

return `[1,3,2]`.

#### EXPLANATION:

二叉树的中序遍历，其实也没有什么好说的。

#### SOLUTION:

```JAVA
public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        inorderTraversalHelper(root,result);
        return result;
    }
    public void inorderTraversalHelper(TreeNode root,ArrayList<Integer> set){
        if(root==null) return;
        inorderTraversalHelper(root.left,set);
        set.add(root.val);
        inorderTraversalHelper(root.right,set);
    }
}
```

