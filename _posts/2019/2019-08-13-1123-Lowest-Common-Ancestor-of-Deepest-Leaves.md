---
layout: post
title: 1123. Lowest Common Ancestor of Deepest Leaves
---
#### QUESTION:
Given a rooted binary tree, return the lowest common ancestor of its deepest leaves.

Recall that:

The node of a binary tree is a leaf if and only if it has no children
The depth of the root of the tree is 0, and if the depth of a node is d, the depth of each of its children is d+1.
The lowest common ancestor of a set S of nodes is the node A with the largest depth such that every node in S is in the subtree with root A.
 

Example 1:

Input: root = [1,2,3]
Output: [1,2,3]
Explanation: 
The deepest leaves are the nodes with values 2 and 3.
The lowest common ancestor of these leaves is the node with value 1.
The answer returned is a TreeNode object (not an array) with serialization "[1,2,3]".
Example 2:

Input: root = [1,2,3,4]
Output: [4]
Example 3:

Input: root = [1,2,3,4,5]
Output: [2,4,5]
 

Constraints:

The given tree will have between 1 and 1000 nodes.
Each node of the tree will have a distinct value between 1 and 1000.
#### EXPLANATION:

题目大意就是：求最深节点的最小公共节点。
首先我们就需要知道每个节点左右节点的最大深度，如果这个节点的左右最大深度都是已知的最大深度，那么就说明是这个节点。  
逻辑如下：  
1.递归遍历整个树，并且需要记录深度  
2.判断当前节点的左右深度  
3.如果当前节点就是最大深度，那么就先用当前节点进行初步结果  
4.如果父节点中有左右节点都是最大深度，那么就说明具有多个最深的leaf，那么这个节点就是结果  

#### SOLUTION:
```java
class Solution {
    
    int deepest = 0;
    TreeNode lca;
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        lcaDeepestLeavesHelper(root,0);
        return lca;
    }
    
    public int lcaDeepestLeavesHelper(TreeNode root,int depth) {
        deepest = Math.max(depth,deepest);
        if(root==null) return depth;
        int left = lcaDeepestLeavesHelper(root.left,depth+1);
        int right = lcaDeepestLeavesHelper(root.right,depth+1);
        if(left==deepest && right==deepest) lca = root;
        return Math.max(left,right);
    }
}
```
