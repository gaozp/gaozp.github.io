---
layout: post
title: 538. Convert BST to Greater Tree
categories: [leetcode]
---

#### QUESTION:

Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.

**Example:**

```
Input: The root of a Binary Search Tree like this:
              5
            /   \
           2     13

Output: The root of a Greater Tree like this:
             18
            /   \
          20     13
```

#### EXPLANATION:

都知道BST的中序遍历其实就是1，2，3，4。。。那么我们可以先递归遍历右侧，那么出来的结果其实就是从最大到最小的结果了，那么就可以把当前的数加上之前获取的值就可以作为当前的root.val了。

#### SOLUTION:

```JAVA
public class Solution {
    public TreeNode convertBST(TreeNode root) {
        convertBSTHelper(root);
        return root;
    }
    int convertBSTPre = 0;
    public void convertBSTHelper(TreeNode root){
        if (root == null) return;
        convertBSTHelper(root.right);//关键地方，先循环右侧
        root.val += convertBSTPre;
        convertBSTPre = root.val;
        convertBSTHelper(root.left);
    }
}
```

