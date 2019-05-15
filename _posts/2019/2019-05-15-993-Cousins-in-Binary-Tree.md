---
layout: post
title: 993. Cousins in Binary Tree
---

#### QUESTION:

In a binary tree, the root node is at depth `0`, and children of each depth `k` node are at depth `k+1`.

Two nodes of a binary tree are *cousins* if they have the same depth, but have **different parents**.

We are given the `root` of a binary tree with unique values, and the values `x` and `y` of two different nodes in the tree.

Return `true` if and only if the nodes corresponding to the values `x` and `y` are cousins.

**Example 1:![img](https://assets.leetcode.com/uploads/2019/02/12/q1248-01.png)**

```
Input: root = [1,2,3,4], x = 4, y = 3
Output: false
```

**Example 2:![img](https://assets.leetcode.com/uploads/2019/02/12/q1248-02.png)**

```
Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
Output: true
```

**Example 3:**

**![img](https://assets.leetcode.com/uploads/2019/02/13/q1248-03.png)**

```
Input: root = [1,2,3,null,4], x = 2, y = 3
Output: false
```

**Note:**

1. The number of nodes in the tree will be between `2` and `100`.
2. Each node has a unique integer value from `1` to `100`.

#### EXPLANATION:

两个点

1.需要判断两个值在不在一个level

2.需要判断是不是一个root

首先解决第一个问题：层序遍历，记住对应的depth。

再来解决第二个问题：怎么判断root呢，既然每个值都是单一的，那么，我们就可以记录和保存值，那保存root的值怎么保存呢，就是每次遍历的时候都遍历子节点，这样就可以保存父节点的值了。

#### SOLUTION:

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
               Queue<TreeNode> q1 = new ArrayDeque();
        Queue<TreeNode> q2 = new ArrayDeque();
        q1.add(root);
        int turn = 0;
        int[][] indexX = new int[1][2];
        int[][] indexY = new int[1][2];
        while (!q1.isEmpty() || !q2.isEmpty()){
            Queue<TreeNode> outQueue = turn%2==0 ? q1: q2;
            Queue<TreeNode> inQueue = turn%2==0 ? q2:q1;
            while (!outQueue.isEmpty()){
                TreeNode poll = outQueue.poll();
                if((poll.left!=null && poll.left.val == x )|| (poll.right!=null && poll.right.val==x)){
                    indexX[0][0] = turn+1;
                    indexX[0][1] = poll.val;
                }
                if((poll.left!=null && poll.left.val == y )|| (poll.right!=null && poll.right.val==y)){
                    indexY[0][0] = turn+1;
                    indexY[0][1] = poll.val;
                }
                if(poll.left!=null) inQueue.add(poll.left);
                if(poll.right!=null) inQueue.add(poll.right);
            }
            turn++;
        }
        return indexX[0][0]==indexY[0][0] && indexX[0][1]!=indexY[0][1];
    }
}
```

