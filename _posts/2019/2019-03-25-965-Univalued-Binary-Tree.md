---
layout: post
title: 965. Univalued Binary Tree
categories: [leetcode]
---

#### QUESTION:

A binary tree is *univalued* if every node in the tree has the same value.

Return `true` if and only if the given tree is univalued.

**Example 1:**

![](https://assets.leetcode.com/uploads/2018/12/28/unival_bst_1.png)

```
Input: [1,1,1,1,1,null,1]
Output: true
```

**Example 2:**

![](https://assets.leetcode.com/uploads/2018/12/28/unival_bst_2.png)

```
Input: [2,2,2,5,2]
Output: false
```

**Note:**

1. The number of nodes in the given tree will be in the range `[1, 100]`.
2. Each node's value will be an integer in the range `[0, 99]`.

#### EXPLANATION:

其实这个题目一看的话就很容易了。就是在考察二叉树的遍历。但是需要记住的是，判断条件是不同的层级的节点，如果你仅仅是判断左右节点是否相同，那么很容易漏掉节点。

采用递归的方式就可以。

#### SOLUTION:

```java
class Solution {
    public boolean isUnivalTree(TreeNode root) {
        if(root == null) return false;
        return isUnivalTreeHelper(root.left,root.val)&&isUnivalTreeHelper(root.right,root.val);
    }
    
    public static boolean isUnivalTreeHelper(TreeNode root,int pre){
        if(root == null) return true;
        if(root.val!=pre) return false;
        return isUnivalTreeHelper(root.left,root.val)&&isUnivalTreeHelper(root.right,root.val);
    }
}
```

