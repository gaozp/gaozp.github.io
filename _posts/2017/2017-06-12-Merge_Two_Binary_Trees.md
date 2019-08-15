---
layout: post
title: 617. Merge Two Binary Trees
categories: [leetcode]
---

#### QUESTION:

Given two binary trees and imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not.

You need to merge them into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of new tree.

**Example 1:**

```
Input: 
	Tree 1                     Tree 2                  
          1                         2                             
         / \                       / \                            
        3   2                     1   3                        
       /                           \   \                      
      5                             4   7                  
Output: 
Merged tree:
	     3
	    / \
	   4   5
	  / \   \ 
	 5   4   7

```

**Note:** The merging process must start from the root nodes of both trees.

#### EXPLANATION:

其实思路很简单，就是把两者相加，如果任一一棵树有值就可以创建新的结点。本来是打算使用t1作为result，然后将t2的值加上去。但是想想这样的话其实是污染了原始数据，并且新建一个结果对算法的复杂度并没有影响，只是会提高了数据的占用，于是还是使用了后者的方法。

#### SOLUTION:

```JAVA
public class Solution {
    
    private TreeNode mergeTreeResult = new TreeNode(0);
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1==null&&t2==null) return null;
        return mergeTreesHelper(mergeTreeResult,t1,t2);
    }
    
    public TreeNode mergeTreesHelper(TreeNode pre,TreeNode t1, TreeNode t2){
        if (t1 == null && t2 != null){
            t1 = new TreeNode(0);
        }
        if (t1 != null && t2 == null){
            t2 = new TreeNode(0);
        }
        if (t1 != null && t2 != null)
            pre.val = t1.val + t2.val;
        if(t1.left!=null || t2.left!=null){
            pre.left = new TreeNode(0);
            mergeTreesHelper(pre.left,t1.left,t2.left);
        }
        if(t1.right!=null || t2.right!=null){
            pre.right = new TreeNode(0);
            mergeTreesHelper(pre.right,t1.right,t2.right);
        }
        return mergeTreeResult;
    }

}
```

