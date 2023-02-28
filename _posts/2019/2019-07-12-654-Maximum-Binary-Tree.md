---
layout: post
title: 654. Maximum Binary Tree
categories: [leetcode]
---
#### QUESTION:

Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:

The root is the maximum number in the array.
The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
Construct the maximum tree by the given array and output the root node of this tree.

Example 1:
Input: [3,2,1,6,0,5]
Output: return the tree root node representing the following tree:

      6
    /   \
   3     5
    \    / 
     2  0   
       \
        1
Note:
The size of the given array will be in the range [1,1000].

#### EXPLANATION:

在拿到题目的时候就可以观察到，其实给出的数组的顺序其实是有考究的。
最大值的左右两边，然后左右两边最大值的左右两边，其实就是整个二叉树的形状。
于是就可以拿到步骤：
1.首先算出当前的最大值，放在root上
2.将0-index的最大值放在左节点上，index-length的最大值放在右节点上
3.这样就分出两个段，再将这两段的最大值获得依次放在对应的节点上
4.重复直到无法再进行分割

#### SOLUTION:
```JAVA
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        int max = nums[0],index = 0;
        for(int i = 1;i<nums.length;i++){
            if(nums[i]>max){
                max = nums[i];
                index = i;
            }
        }
        TreeNode root = new TreeNode(max);
        constructMaximumBinaryTreeHelper(root,true,0,index-1,nums);
        constructMaximumBinaryTreeHelper(root,false,index+1,nums.length-1,nums);
        return root;
    }
    
    public static void constructMaximumBinaryTreeHelper(TreeNode root,boolean left,int start,int end,int[] nums) {
        if(start>end) return;
        int max = Integer.MIN_VALUE,index = 0;
        for(int i = start;i<=end;i++){
            if(nums[i]>max){
                max = nums[i];
                index = i;
            }
        }
        TreeNode node;
        node = new TreeNode(max);
        if(left) root.left  = node;
        else root.right = node;
        constructMaximumBinaryTreeHelper(node,true,start,index-1,nums);
        constructMaximumBinaryTreeHelper(node,false,index+1,end,nums);
    }
}
```