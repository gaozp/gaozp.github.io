---
layout: post
title: 102. Binary Tree Level Order Traversal
categories: [leetcode]
---

#### QUSTION:

Given a binary tree, return the *level order* traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree `[3,9,20,null,null,15,7]`,

```
    3
   / \
  9  20
    /  \
   15   7

```

return its level order traversal as:

```
[
  [3],
  [9,20],
  [15,7]
]
```

#### EXPLANATION:

就是在每一层都添加一个level的标志位，这样每次的数字都可以获取到对应的层数，再添加进对应的层数就可以了。

#### SOLUTION:

```java
public class Solution {
    List<List<Integer>> levelOrderResult = new ArrayList<>();
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null) return levelOrderResult;
        levelOrderHelper(root,0);
        return levelOrderResult; 
    }
    
    public void levelOrderHelper(TreeNode root,int level){
        ArrayList levelSum = new ArrayList();
        if(level > levelOrderResult.size()-1){
            levelOrderResult.add(level,levelSum);
        }
        levelSum = (ArrayList) levelOrderResult.get(level);
        levelSum.add(root.val);
        level++;
        if(root.left!=null) levelOrderHelper(root.left,level);
        if(root.right!=null) levelOrderHelper(root.right,level);
    }
}
```

