---
layout: post
title: 669. Trim a Binary Search Tree
---

#### QUESTION:

Given a binary search tree and the lowest and highest boundaries as `L` and `R`, trim the tree so that all its elements lies in `[L, R]` (R >= L). You might need to change the root of the tree, so the result should return the new root of the trimmed binary search tree.

**Example 1:**

```
Input: 
    1
   / \
  0   2

  L = 1
  R = 2

Output: 
    1
      \
       2

```

**Example 2:**

```
Input: 
    3
   / \
  0   4
   \
    2
   /
  1

  L = 1
  R = 3

Output: 
      3
     / 
   2   
  /
 1

```

#### EXPLANATION:

自己写的办法比较蠢啦，哈哈，可以看看别人的算法，确实还是挺快。速度上倒是没有什么差距，但是明显的递归思想的应用上，确实还是有点欠缺。

#### SOLUTION:

```JAVA
class Solution {
    TreeNode trimResult = null;
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if(root == null) return trimResult;
        if(root.val>=L && root.val<=R) trimResult = new TreeNode(root.val);
        if(root.left!=null) TrimBSTHelper(root.left,L,R,trimResult);
        if(root.right!=null) TrimBSTHelper(root.right,L,R,trimResult);
        return trimResult;
    }
    public void TrimBSTHelper(TreeNode root,int L,int R,TreeNode result){
        if(root == null) return;
        if(root.val>=L && root.val <=R) {
            if (result == null){
                trimResult = new TreeNode(root.val);
                if(root.left!=null) TrimBSTHelper(root.left,L,R,trimResult);
                if(root.right!=null) TrimBSTHelper(root.right,L,R,trimResult);
            }else{
                if (root.val < result.val) {
                    result.left = new TreeNode(root.val);
                    if (root.left != null) TrimBSTHelper(root.left, L, R, result.left);
                    if (root.right != null) TrimBSTHelper(root.right, L, R, result.left);
                }
                if (root.val > result.val) {
                    result.right = new TreeNode(root.val);
                    if (root.left != null) TrimBSTHelper(root.left, L, R, result.right);
                    if (root.right != null) TrimBSTHelper(root.right, L, R, result.right);
                }
            }
        }else{
            if (root.left != null) TrimBSTHelper(root.left, L, R, result);
            if (root.right != null) TrimBSTHelper(root.right, L, R, result);
        }
    }
}

class Solution {
      public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) return null;

        if (root.val < L) return trimBST(root.right, L, R);
        if (root.val > R) return trimBST(root.left, L, R);

        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);

        return root;
    }

}

```

