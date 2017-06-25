---
layout: post
title: 513. Find Bottom Left Tree Value
---

#### QUESTION:

Given a binary tree, find the leftmost value in the last row of the tree.

**Example 1:**

```
Input:

    2
   / \
  1   3

Output:
1

```

**Example 2: **

```
Input:

        1
       / \
      2   3
     /   / \
    4   5   6
       /
      7

Output:
7

```

**Note:** You may assume the tree (i.e., the given root node) is not **NULL**.

#### EXPLANATION:

其实就是树的遍历，同时需要记下树的层数，这样就可以了。

#### SOLUTION:

```JAVA
public class Solution {
    int leftvalue = -1;
    int level = -1;
    public int findBottomLeftValue(TreeNode root) {
        leftvalue = root.val;
        findBottomLefeValueHelper(root,0);
        return leftvalue;
    }
    public void findBottomLefeValueHelper(TreeNode root,int a){
        if(root == null) return;
        if(root.left!=null){
            if(a>level){
                leftvalue = root.left.val;
                level = a;
            }
            findBottomLefeValueHelper(root.left,a+1);
        }
        if(root.right !=null){
            if(a>level){
                leftvalue = root.right.val;
                level = a;
            }
            findBottomLefeValueHelper(root.right,a+1);
        }
    }
}
```

