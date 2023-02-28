---
layout: post
title: 687. Longest Univalue Path
categories: [leetcode]
---
#### QUESTION:

Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.

The length of path between two nodes is represented by the number of edges between them.

 

Example 1:

Input:

              5
             / \
            4   5
           / \   \
          1   1   5
Output: 2

 

Example 2:

Input:

              1
             / \
            4   5
           / \   \
          4   4   5
Output: 2

 

Note: The given binary tree has not more than 10000 nodes. The height of the tree is not more than 1000.

#### EXPLANATION:

题目大意是：能够在二叉树中找到最长的相等的值的路径
思路：
1.将所有的点进行向下寻找
2.如果是寻找的根节点，那么就需要将left+right
3.如果不是根节点，那么就需要比较left和right的大小

#### SOLUTION:

```java
class Solution {
    public int longestUnivaluePath(TreeNode root) {
        if(root==null ) return 0;
        int result = 0;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            int nodeValue = longestUnivaluePath(node,0,true);
            if(node.left!=null) queue.add(node.left);
            if(node.right!=null) queue.add(node.right);
            result = Math.max(result,nodeValue);
        }
        return result;
    }
    
    public static int longestUnivaluePath(TreeNode root,int length,boolean isRoot){
        int left = length,right = length;
        if(root.left!=null && root.left.val==root.val){
            left = longestUnivaluePath(root.left,left+1,false);
        }
        if(root.right!=null && root.right.val==root.val){
            right = longestUnivaluePath(root.right,right+1,false);
        }
        if(isRoot) return left+right;
        else return Math.max(left,right);
    }
}
```