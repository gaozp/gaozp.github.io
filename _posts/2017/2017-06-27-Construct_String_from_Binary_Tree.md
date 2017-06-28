---
layout: post
title: 606. Construct String from Binary Tree
---

#### QUESTION:

You need to construct a string consists of parenthesis and integers from a binary tree with the preorder traversing way.

The null node needs to be represented by empty parenthesis pair "()". And you need to omit all the empty parenthesis pairs that don't affect the one-to-one mapping relationship between the string and the original binary tree.

**Example 1:**

```
Input: Binary tree: [1,2,3,4]
       1
     /   \
    2     3
   /    
  4     

Output: "1(2(4))(3)"

Explanation: Originallay it needs to be "1(2(4)())(3()())", 
but you need to omit all the unnecessary empty parenthesis pairs. 
And it will be "1(2(4))(3)".

```

**Example 2:**

```
Input: Binary tree: [1,2,3,null,4]
       1
     /   \
    2     3
     \  
      4 

Output: "1(2()(4))(3)"

Explanation: Almost the same as the first example, 
except we can't omit the first parenthesis pair to break the one-to-one mapping relationship between the input and the output.
```

#### EXPLANATION:

刚开始看到题目的时候想到了应该是迭代遍历，然后把值加上（）左右的值。

具体的解法可以查看[这个链接](https://leetcode.com/articles/construct-string-from-binary-tree/)。

#### SOLUTION:

```java
public class Solution {
    public String tree2str(TreeNode t) {
        if (t == null) return "";
        String left = "";
        String right = "";
        if (t.left == null && t.right != null)
            left = "()";
        if (t.left != null)
            left = "(" + tree2str(t.left) + ")";
        if (t.right != null)
            right = "(" + tree2str(t.right) + ")";
        return t.val + left + right;
    }
}
```

