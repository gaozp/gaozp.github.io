---
layout: post
title: 1315. Sum of Nodes with Even-Valued Grandparent
categories: [leetcode]
---
#### QUESTION:
Given a binary tree, return the sum of values of nodes with even-valued grandparent.  (A grandparent of a node is the parent of its parent, if it exists.)

If there are no nodes with an even-valued grandparent, return 0.

 

**Example 1:**

![img](https://assets.leetcode.com/uploads/2019/07/24/1473_ex1.png) 

```
Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
Output: 18
Explanation: The red nodes are the nodes with even-value grandparent while the blue nodes are the even-value grandparents.
 
```

**Constraints:**

```
The number of nodes in the tree is between 1 and 10^4.
The value of nodes is between 1 and 100.
```
#### EXPLANATION:
这道题目虽然是一个medium的题目，但是其实并没有那么难，有80%的通过率，说到底其实就是一个dfs遍历的问题，无论是先序还是后续其实都可以，树的遍历就不用多说了。在遍历的时候判断下当前的数是不是偶数，是的话就加上孙子的值，需要注意的是查看null的情况。  
思路：  
1. 遍历树，无论前序还是后序
2. 查看当前节点是不是偶数，如果是的话，查看4个孙子的值，如果有就加在结果上。
3. 将两个儿子进行迭代遍历


#### SOLUTION:
```java
class Solution {
    int sumEvenGrandparentResult = 0;
    public int sumEvenGrandparent(TreeNode root) {
        if(root == null) return 0;
        sumEvenGrandparentHelper(root);
        return sumEvenGrandparentResult;
    }
    
    public void sumEvenGrandparentHelper(TreeNode root){
        if(root.val % 2 ==0 ){
            if(root.left!=null){
                if(root.left.left!=null) sumEvenGrandparentResult+=root.left.left.val;
                if(root.left.right!=null) sumEvenGrandparentResult+=root.left.right.val;
            }
            if(root.right!=null){
                if(root.right.left!=null) sumEvenGrandparentResult+=root.right.left.val;
                if(root.right.right!=null) sumEvenGrandparentResult+=root.right.right.val;
            }
        }
        if(root.left!=null) sumEvenGrandparentHelper(root.left);
        if(root.right!=null) sumEvenGrandparentHelper(root.right);
    }
}
```
