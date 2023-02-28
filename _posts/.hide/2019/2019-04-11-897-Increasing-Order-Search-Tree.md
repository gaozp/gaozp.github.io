---
layout: post
title: 897. Increasing Order Search Tree
categories: [leetcode]
---

#### QUESTION:

Given a tree, rearrange the tree in **in-order** so that the leftmost node in the tree is now the root of the tree, and every node has no left child and only 1 right child.

```
Example 1:
Input: [5,3,6,2,4,null,8,1,null,null,null,7,9]

       5
      / \
    3    6
   / \    \
  2   4    8
 /        / \ 
1        7   9

Output: [1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]

 1
  \
   2
    \
     3
      \
       4
        \
         5
          \
           6
            \
             7
              \
               8
                \
                 9  
```

**Note:**

1. The number of nodes in the given tree will be between 1 and 100.
2. Each node will have a unique integer value from 0 to 1000.

#### EXPLANATION:

这个题目中没有说到的是，给到的例子本来就是一个二叉查找树，但是在名字中给出了BST，那么问题就变的简单了。其实就是在中序遍历。这样问题就更简单了，中序遍历后将结果整理一下就可以了。

中序遍历就不用说了，再加上新建一个数据来保存结果。记得最后返回的是头部，所以需要在刚开始遍历的时候保存头部的位置。

#### SOLUTION:

```java
class Solution {
    public TreeNode increasingBST(TreeNode root) {
        TreeNode head = increasingBSTResult;
        increasingBSTHelper(root);
        return head.right;
    }
    public TreeNode increasingBSTResult = new TreeNode(0);
    public void increasingBSTHelper(TreeNode node){
        if(node==null) return;
        increasingBSTHelper(node.left);
        TreeNode right = new TreeNode(node.val);
        increasingBSTResult.right = right;
        increasingBSTResult = right;
        increasingBSTHelper(node.right);
    }
}
```



