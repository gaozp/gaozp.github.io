---
layout: post
title: 951. Flip Equivalent Binary Trees
---
#### QUESTION:
For a binary tree T, we can define a flip operation as follows: choose any node, and swap the left and right child subtrees.

A binary tree X is flip equivalent to a binary tree Y if and only if we can make X equal to Y after some number of flip operations.

Write a function that determines whether two binary trees are flip equivalent.  The trees are given by root nodes root1 and root2.

 

Example 1:

Input: root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 = [1,3,2,null,6,4,5,null,null,null,null,8,7]
Output: true
Explanation: We flipped at nodes with values 1, 3, and 5.
Flipped Trees Diagram
 ![](https://assets.leetcode.com/uploads/2018/11/29/tree_ex.png)

Note:

Each tree will have at most 100 nodes.
Each value in each tree will be a unique integer in the range [0, 99].

#### EXPLANATION:

题目看到的时候，是说在无限的情况下，两者相等，那么我们就可以逆向思维：既然经过了变动你们相等，那么我们就反过来。往你那个方向变，看能不能变换成功。  
所以思路：  
1.判断当前节点是否相等，不相等返回false（只有根节点需要判断，因为后面的left，right的判断已经包含了这次值的判断）  
2.如果左右节点进行了互换，那么就对互换的节点进行分别进行判断  
3.如果没有进行互换，则同样进行子节点的判断  
4.结果就是左右节点的&运算，必须左右都为true才为true

#### SOLUTION:
```JAVA
class Solution {
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if(root1==null && root2==null) return true;
        if(root1 != null && root2 !=null && root1.val == root2.val){
            return flipEquivHelper(root1,root2);
        }else return false;
    }
    
    public static boolean flipEquivHelper(TreeNode root1,TreeNode root2){
        int left1 = root1.left==null?-1:root1.left.val;
        int right1 = root1.right==null?-1:root1.right.val;
        int left2 = root2.left==null?-1:root2.left.val;
        int right2 = root2.right==null?-1:root2.right.val;
        if(left1==left2 && right1 == right2 ){
            boolean left = false;
            if(left1!=-1) left = flipEquivHelper(root1.left,root2.left);
            else left=true;
            boolean right = false;
            if(right1!=-1) right = flipEquivHelper(root1.right,root2.right);
            else right = true;
            return left&right;
        }else if(left1==right2 && right1==left2){
            boolean left = false;
            if(left1!=-1) left = flipEquivHelper(root1.left,root2.right);
            else left = true;
            boolean right = false;
            if(right1!=-1) right = flipEquivHelper(root1.right,root2.left);
            else right = true;
            return  left&right;
        }else return false;
    }
}
```
