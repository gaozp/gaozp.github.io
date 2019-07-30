---
layout: post
title: 814. Binary Tree Pruning
---
#### QUESTION:
We are given the head node root of a binary tree, where additionally every node's value is either a 0 or a 1.

Return the same tree where every subtree (of the given tree) not containing a 1 has been removed.

(Recall that the subtree of a node X is X, plus every node that is a descendant of X.)

Example 1:
Input: [1,null,0,0,1]
Output: [1,null,0,null,1]
 
Explanation: 
Only the red nodes satisfy the property "every subtree not containing a 1".
The diagram on the right represents the answer.
![](https://s3-lc-upload.s3.amazonaws.com/uploads/2018/04/06/1028_2.png)

Example 2:
Input: [1,0,1,0,0,0,1]
Output: [1,null,1,null,1]
![](https://s3-lc-upload.s3.amazonaws.com/uploads/2018/04/06/1028_1.png)


Example 3:
Input: [1,1,0,1,1,0,1,0]
Output: [1,1,0,1,1,null,1]
![](https://s3-lc-upload.s3.amazonaws.com/uploads/2018/04/05/1028.png)


Note:

The binary tree will have at most 100 nodes.
The value of each node will only be 0 or 1.

#### EXPLANATION:

首先从题意可以得到：肯定是先进行子树的对比，然后得到结果后，返回，再进行右树的对比，得到结果，最后与自己的值进行对比，如果是0，那么说明可以去除该节点，如果是1，那么就不能去除。这个逻辑就可以清晰的得到，这个肯定就是二叉树的后序遍历了。
那么知道了后序遍历，只需要进行判断就行。  
1.如果节点为null，那么就是true  
2.如果左节点为true，那么就可以删除  
3.如果右节点为true，那么可以删除  
4.如果本节点为0并且左右节点都为true，那么返回true，给上一个节点删除  
5.否则返回false  

#### SOLUTION:
```JAVA
class Solution {
    public TreeNode pruneTree(TreeNode root) {
        if(root == null) return null;
        pruneTreeHelper(root);
        return root;
    }
    
    public static boolean pruneTreeHelper(TreeNode node){
        if(node == null) return true;
        boolean left = pruneTreeHelper(node.left);
        boolean right = pruneTreeHelper(node.right);
        if(left) node.left = null;
        if(right) node.right = null;
        if(node.val==0 && left && right) return true;
        return false;
    }
}
```
