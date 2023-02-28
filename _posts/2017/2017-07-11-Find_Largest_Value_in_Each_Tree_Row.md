---
layout: post
title: 515. Find Largest Value in Each Tree Row
categories: [leetcode]
---

#### QUESTION:

You need to find the largest value in each row of a binary tree.

**Example:**

```
Input: 

          1
         / \
        3   2
       / \   \  
      5   3   9 

Output: [1, 3, 9]
```

#### EXPLANATION:

这个其实就是一个树的层序遍历的基本实现，其实也没有什么难的。具体见代码就可以。

#### SOLUTION:

```JAVA
public class Solution {
    ArrayList<Integer> largestValuesResult = new ArrayList<>();
    public List<Integer> largestValues(TreeNode root) {
        largestValuesHelper(root,0);
        return largestValuesResult;
    }
    public void largestValuesHelper(TreeNode root,int level){
        if(root == null) return;
        if(level == largestValuesResult.size()){
            largestValuesResult.add(root.val);
        }else if(level < largestValuesResult.size()){
            largestValuesResult.set(level,Math.max(largestValuesResult.get(level),root.val));
        }
        if(root.left!=null)
            largestValuesHelper(root.left,level+1);
        if(root.right!=null)
            largestValuesHelper(root.right,level+1);
    }
}
```

