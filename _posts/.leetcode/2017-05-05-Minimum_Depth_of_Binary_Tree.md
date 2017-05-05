---
layout: post
title: 111. Minimum Depth of Binary Tree
---

#### QUESTION:

Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

#### EXPLANATION:

其实也没有什么可以解释的：

1.遍历整个树，每一层都+1；

2.如果左树和右树都是null的话，说明是叶节点，那么就进行比对。

#### SOLUTION:

```java
public class Solution {
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        return minDepthHelper(root,1);
    }
    private int minDepthResult = Integer.MAX_VALUE;
    public int minDepthHelper(TreeNode root,int depth){
        if(root == null) return Integer.MAX_VALUE;
        if(root.left==null && root.right == null)
            minDepthResult = Math.min(depth,minDepthResult);
        minDepthHelper(root.left,depth+1);
        minDepthHelper(root.right,depth+1);
        return minDepthResult;
    }
}
```

