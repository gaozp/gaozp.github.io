---
layout: post
title: 113. Path Sum II
---

#### QUESTION:

Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

For example:

Given the below binary tree and 

```
sum = 22
```

,

```
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1

```

return

```
[
   [5,4,11,2],
   [5,8,4,5]
]
```

#### EXPLANATION:

首先建立一个stack，从左边开始入栈，然后一直入栈，到最后的时候，查看和是否是和sum相等，如果相等就添加到结果中，否则就出栈，一直循环到最后就可以。

#### SOLUTION:

```java
public class Solution {
    public List<List<Integer>> pathSumResult = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if(root == null) return pathSumResult;
        Stack<Integer> stack = new Stack<>();
        pathSum(root,sum,stack);
        return pathSumResult;
    }
    
    public void pathSum(TreeNode root, int sum,Stack<Integer> stack) {
        stack.push(root.val);
        if(root.left == null && root.right == null){
            if(sum - root.val ==0) {
                pathSumResult.add(new ArrayList<>(stack));
            }
        }
        if(root.left != null) pathSum(root.left,sum-root.val,stack);
        if(root.right != null) pathSum(root.right,sum-root.val,stack);
        stack.pop();
    }
}
```

