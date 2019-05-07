---
layout: post
title: 938. Range Sum of BST
---

#### QUESTION:

Given the `root` node of a binary search tree, return the sum of values of all nodes with value between `L` and `R` (inclusive).

The binary search tree is guaranteed to have unique values.

**Example 1:**

```
Input: root = [10,5,15,3,7,null,18], L = 7, R = 15
Output: 32
```

**Example 2:**

```
Input: root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
Output: 23
```

**Note:**

1. The number of nodes in the tree is at most `10000`.
2. The final answer is guaranteed to be less than `2^31`.

#### EXPLANATION:

这个就比较简单le

因为是bst，就是二分查找树，所以当左边小于L时，那这个节点所有的左边都可以放弃了。同理右边。然后进行遍历就行。

如代码：

#### SOLUTION:

```java
class Solution {
    public int rangeSumBSTResult = 0;
    public int rangeSumBST(TreeNode root, int L, int R) {
        if(root == null) return 0;
        if(root.val<L) {
            rangeSumBST(root.right,L,R);
        }else if(root.val>R){
            rangeSumBST(root.left,L,R);
        }else if(root.val>=L && root.val <=R){
            rangeSumBSTResult+=root.val;
            rangeSumBST(root.right,L,R);
            rangeSumBST(root.left,L,R);
        }
        return rangeSumBSTResult;
    }
}
```

