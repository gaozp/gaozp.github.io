---
layout: post
title: 1161. Maximum Level Sum of a Binary Tree
categories: [leetcode]
---
#### QUESTION:
Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.

Return the smallest level X such that the sum of all the values of nodes at level X is maximal.

 

Example 1:

![img](https://assets.leetcode.com/uploads/2019/05/03/capture.JPG)

Input: [1,7,0,7,-8,null,null]
Output: 2
Explanation: 
Level 1 sum = 1.
Level 2 sum = 7 + 0 = 7.
Level 3 sum = 7 + -8 = -1.
So we return the level with the maximum sum which is level 2.
 

Note:

The number of nodes in the given tree is between 1 and 10^4.
-10^5 <= node.val <= 10^5
#### EXPLANATION:
这个题目就比较简单了，虽然是middle的难度，其实就是层序遍历树的思想。既然需要层序遍历，那么就需要一个集合来将每层的节点添加到其中。因为需要确定层数，所以需要知道每次遍历的层数，所以需要一个tmplist来交替，这样就可以知道每层遍历的结束。再计算出每层的和就可以。  
逻辑：  
1. 定义一个level，和result，level用来确定层数，而result用来计算结果  
2. 定义个list来进行遍历，将root节点放在其中  
3. 当list不为空的时候，说明还没有遍历结束，进入循环  
4. 定义一个tmplist来保存下一层的结果，同时将level++  
5. 循环list计算出结果，同时如果有子节点，就需要将其添加到tmplist中，来进行下一层的计算  
6. 如果当前结果比result大，那么就需要将当前level赋值给result  
7. 将tmplist赋值给list，这样就可以继续循环4了，一直到树遍历结束  

#### SOLUTION:
```java
class Solution {
    public int maxLevelSum(TreeNode root) {
        if(root==null) return 0;
        int level = 0,result = 0;
        int sum = Integer.MIN_VALUE;
        ArrayList<TreeNode> list = new ArrayList<>();
        list.add(root);
        while (!list.isEmpty()){
            level++;
            ArrayList<TreeNode> tmp = new ArrayList<>();
            int tmpSum = 0;
            for(TreeNode node: list){
                tmpSum+=node.val;
                if(node.left!=null) tmp.add(node.left);
                if(node.right!=null) tmp.add(node.right);
            }
            if(tmpSum>sum){
                sum = tmpSum;
                result = level;
            }
            list = tmp;
        }
        return result;
    }
}
```
