---
layout: post
title: 108.Convert Sorted Array to Binary Search Tree
---

#### QUESTION:

Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

#### EXPLANATION:

BST有一个特性其实就是如果按照中序遍历的话出来的结果就应该是1234567.。。

所以只要每次都找中点，作为根节点，然后在不断递归，就可以将整个树遍历出来了。

#### SOLUTION:

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums==null || nums.length==0)return null;
        return helper(nums,0,nums.length-1);
    }
    
    public TreeNode helper(int[] nums,int from,int to){
        if(from>to){
            return null;
        }
        int middle = (from+to)/2;
        TreeNode t = new TreeNode(nums[middle]);
        t.left = helper(nums,from,middle-1);
        t.right = helper(nums,middle+1,to);
        return t;
    }
}
```

