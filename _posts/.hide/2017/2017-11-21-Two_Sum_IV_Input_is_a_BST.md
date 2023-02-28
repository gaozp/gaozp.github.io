---
layout: post
title: 653. Two Sum IV - Input is a BST
categories: [leetcode]
---

#### QUESTION:

Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to the given target.

**Example 1:**

```
Input: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 9

Output: True

```

**Example 2:**

```
Input: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 28

Output: False
```

#### EXPLANATION:

逻辑就是

1.遍历这个二叉树

2.每一个数和二叉树中的节点进行对撞

3.如果对撞成功就返回true

需要注意的是，在进行对撞的时候可以优化：如果对撞的结果小于当前值，那么就在右边的树中搜索，而左树就可以忽略，同理反之。

#### SOLUTION:

```JAVA
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode tmp = queue.poll();
            boolean find = false;
            find = findTargetHelper(root,k,tmp.val);
            if(find) return true;
            if(tmp.left!=null) queue.add(tmp.left);
            if(tmp.right!=null) queue.add(tmp.right);
        }
        return false;
    }
    
    public boolean findTargetHelper(TreeNode root,int k,int val){
        if(root == null) return false;
        if(root.val + val == k && root.val != val ) return true;
        if(root.val < k - val) return findTargetHelper(root.right,k,val);
        if(root.val > k - val) return findTargetHelper(root.left,k,val);
        return false;
    }

}
```

