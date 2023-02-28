---
layout: post
title: 1008. Construct Binary Search Tree from Preorder Traversal
categories: [leetcode]
---
#### QUESTION:
Return the root node of a binary search tree that matches the given preorder traversal.

(Recall that a binary search tree is a binary tree where for every node, any descendant of node.left has a value < node.val, and any descendant of node.right has a value > node.val.  Also recall that a preorder traversal displays the value of the node first, then traverses node.left, then traverses node.right.)

 

Example 1:

Input: [8,5,1,7,10,12]
Output: [8,5,10,1,7,null,12]
![img](https://assets.leetcode.com/uploads/2019/03/06/1266.png)
 

Note: 

1 <= preorder.length <= 100
The values of preorder are distinct.

#### EXPLANATION:

题意是：填充一个二叉查找树，要求前序查找后的结果和之前的order一样。
那么前序查找的结果就是：
1.第一个数是根节点
2.然后比这个数小的都在左节点，比这个数大的都在右节点
3.循环，直到最后的叶节点

#### SOLUTION:
```JAVA
class Solution {
    public TreeNode bstFromPreorder(int[] preorder) {
        if(preorder.length==0)return null;
        TreeNode root = bstFromPreorderHelper(preorder,0,preorder.length-1);
        return root;
    }
    public static TreeNode bstFromPreorderHelper(int[] preorder,int start,int end){
        if(start==end) return new TreeNode(preorder[start]);
        if(start>end) return null;
        TreeNode root = new TreeNode(preorder[start]);
        int index = -1;
        for(int i = start+1;i<=end;i++){
            if(preorder[i]>preorder[start]){
                index = i;
                break;
            }
        }
        if(index == -1)
            root.left = bstFromPreorderHelper(preorder,start+1,end);
        else if(index==start+1)
            root.right = bstFromPreorderHelper(preorder,index,end);
        else{
            root.left = bstFromPreorderHelper(preorder,start+1,index-1);
            root.right = bstFromPreorderHelper(preorder,index,end);
        }

        return root;
    }
}
```