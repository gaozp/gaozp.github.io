---
layout: post
title: 590. N-ary Tree Postorder Traversal
categories: [leetcode]
---

#### QUESTION:

Given an n-ary tree, return the *postorder* traversal of its nodes' values.

For example, given a `3-ary` tree:

![img](https://assets.leetcode.com/uploads/2018/10/12/narytreeexample.png)

 Return its postorder traversal as: `[5,6,3,2,4,1]`.

**Note:**

Recursive solution is trivial, could you do it iteratively?

#### EXPLANATION:

就和之前的先序排列一样，这个是后序排列，只需要在最后的时候添加到list的操作就可以。

#### SOLUTION:

```java
    static List<Integer> postorderResult = new ArrayList<>();

    public static List<Integer> postorder(Node root) {
        if(root == null) return null;
        for(int i = 0;i<root.children.size();i++){
            postorder(root.children.get(i));
        }
        postorderResult.add(root.val);
        return postorderResult;
    }
```

