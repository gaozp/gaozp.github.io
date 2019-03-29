---
layout: post
title: 589. N-ary Tree Preorder Traversal
---

#### QUESTION:

Given an n-ary tree, return the *preorder* traversal of its nodes' values.

For example, given a `3-ary` tree:

![img](https://assets.leetcode.com/uploads/2018/10/12/narytreeexample.png)

Return its preorder traversal as: `[1,3,5,6,2,4]`.

**Note:**

Recursive solution is trivial, could you do it iteratively?

#### EXPLANATION:

就是一个DFS的算法，深度优先的遍历，只要学过树的结构的其实都是很容易的。

#### SOLUTION:

```
class Solution {
    List<Integer> preorderResult = new ArrayList<>();
    public List<Integer> preorder(Node root) {
        if(root == null) return new ArrayList<>();
        preorderResult.add(root.val);
        for(int i =0;i<root.children.size();i++){
            preorder(root.children.get(i));
        }
        return preorderResult;
    }
}
```

