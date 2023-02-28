---
layout: post
title: 222. Count Complete Tree Nodes
categories: [leetcode]
---
#### QUESTION:
Given a complete binary tree, count the number of nodes.

**Note:**

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

**Example:**
```
Input: 
    1
   / \
  2   3
 / \  /
4  5 6

Output: 6
```
#### EXPLANATION:
直接采用层序遍历的方式就可以获取到最终的结果，其实前序后序也是一样可以获取到结果的。
#### SOLUTION:
```java
class Solution {
    public int countNodes(TreeNode root) {
        if(root == null) return 0;
        int count = 0;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode tmp = queue.poll();
            count++;
            if(tmp.left!=null) queue.add(tmp.left);
            if(tmp.right!=null) queue.add(tmp.right);
        }
        return count;
    }
}
```
