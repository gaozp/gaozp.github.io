---
layout: post
title: 700. Search in a Binary Search Tree
---

#### QUESTION:

Given the root node of a binary search tree (BST) and a value. You need to find the node in the BST that the node's value equals the given value. Return the subtree rooted with that node. If such node doesn't exist, you should return NULL.

For example, 

```
Given the tree:
        4
       / \
      2   7
     / \
    1   3

And the value to search: 2
```

You should return this subtree:

```
      2     
     / \   
    1   3
```

In the example above, if we want to search the value `5`, since there is no node with value `5`, we should return `NULL`.

Note that an empty tree is represented by `NULL`, therefore you would see the expected output (serialized tree format) as `[]`, not `null`.

#### EXPLANATION:

这题就是简单的二叉树的查找，可以采用遍历和递归的方式。我这边采用的是递归，找到递归公式就可以。

1.查看当前节点的值，如果和目标值相同则直接返回。

2.比较大小，如果大于目标值则说明在左树，在非空的情况下将左节点传入。

3.比较大小，如果小于目标值则说明在右树，在非空的情况下将右节点传入。

4.都没有说明没有找到，返回null

5.递归1~4.

#### SOLUTION:

```java
class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        if(root.val==val) return root;
        if(root.val > val && root.left !=null ) return searchBST(root.left,val);
        if(root.val < val && root.right != null ) return searchBST(root.right,val);
        return null;
    }
}
```

