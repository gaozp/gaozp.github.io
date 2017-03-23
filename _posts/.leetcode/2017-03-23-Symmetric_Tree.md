---
layout: post
title: 101. Symmetric Tree
---

#### QUESTION:

Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree `[1,2,2,3,4,4,3]` is symmetric:

```
    1
   / \
  2   2
 / \ / \
3  4 4  3

```

But the following `[1,2,2,null,3,null,3]` is not:

```
    1
   / \
  2   2
   \   \
   3    3
```

#### EXPLANATION:

递归和循环的解决办法都在下面了，看代码吧。

#### SOLUTION:

```java
public class Solution {
    
     
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return isSymmetricHelper(root.left,root.right);
    }
    public boolean isSymmetricHelper(TreeNode A,TreeNode B) {
        if (A == null || B == null)
            return A == B;
        if (A.val != B.val)
            return false;
        return isSymmetricHelper(A.left, B.right) && isSymmetricHelper(A.right, B.left);
    };
}


public static boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        LinkedList<TreeNode> temp1 = new LinkedList<>();
        LinkedList<TreeNode> temp2 = new LinkedList<>();
        temp1.add(root.left);temp2.add(root.right);
        while(!temp1.isEmpty() && !temp2.isEmpty()){
            TreeNode A = temp1.poll();
            TreeNode B = temp2.poll();
            if(A==null && B == null) continue;
            if(A == null || B == null) return A==B;
            if(A.val!=B.val) return false;
            temp1.offer(A.left);
            temp1.offer(A.right);
            temp2.offer(B.right);
            temp2.offer(B.left);
        }
        return true;
    };
```

