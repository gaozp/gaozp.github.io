---
layout: post
title: 1022. Sum of Root To Leaf Binary Numbers
---

#### QUESTION:

Given a binary tree, each node has value `0` or `1`.  Each root-to-leaf path represents a binary number starting with the most significant bit.  For example, if the path is `0 -> 1 -> 1 -> 0 -> 1`, then this could represent `01101` in binary, which is `13`.

For all leaves in the tree, consider the numbers represented by the path from the root to that leaf.

Return the sum of these numbers.

**Example 1:**

![img](https://assets.leetcode.com/uploads/2019/04/04/sum-of-root-to-leaf-binary-numbers.png)

```
Input: [1,0,1,0,1,0,1]
Output: 22
Explanation: (100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
```

**Note:**

1. The number of nodes in the tree is between `1` and `1000`.
2. node.val is `0` or `1`.
3. The answer will not exceed `2^31 - 1`.

#### EXPLANATION:

这个题目就是一个DFS的问题。

什么才是leaf呢，就是既没有左节点也没有右节点。

那么怎么才能得到需要加上的数字呢。其实就是将每一条线路做一个标记。当到了最后的leaf的时候就将数字加上。

那么就很容易写出来了。

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
    
    public int sumRootToLeafResult = 0;
    public int sumRootToLeaf(TreeNode root) {
        String val = "";
        if(root!=null) val = root.val+"";
        if(root.left!=null) sumRootToLeafHelper(root.left,val);
        if(root.right!=null) sumRootToLeafHelper(root.right,val);
        if(root.left==null && root.right==null) sumRootToLeafResult += Integer.parseInt(val,2);
        return sumRootToLeafResult;
    }
    
    
    public void sumRootToLeafHelper(TreeNode root,String val) {
        val += root.val;
        if(root.left!=null) sumRootToLeafHelper(root.left,val);
        if(root.right!=null) sumRootToLeafHelper(root.right,val);
        if(root.left==null && root.right==null) sumRootToLeafResult += Integer.parseInt(val,2);
    }
}
```

