---
layout: post
title: 107. Binary Tree Level Order Traversal II
---

#### QUESTION:

Given a binary tree, return the *bottom-up level order* traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

For example:
Given binary tree `[3,9,20,null,null,15,7]`,

```
    3
   / \
  9  20
    /  \
   15   7
```

return its bottom-up level order traversal as:

```
[
  [15,7],
  [9,20],
  [3]
]
```

#### EXPLANATION:

其实就是把I的结果reverse一下，这样就可以获取到当前的结果了。

#### SOLUTION:

```java
public class Solution {
    List<List<Integer>> levelOrderResult = new ArrayList<>();
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if(root == null) return levelOrderResult;
        levelOrderHelper(root,0);
        Collections.reverse(levelOrderResult);
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

