---
layout: post
title: 572. Subtree of Another Tree
---

#### QUESTION:

Given two non-empty binary trees **s** and **t**, check whether tree **t** has exactly the same structure and node values with a subtree of **s**. A subtree of **s** is a tree consists of a node in **s** and all of this node's descendants. The tree **s** could also be considered as a subtree of itself.

**Example 1:**
Given tree s:

```
     3
    / \
   4   5
  / \
 1   2

```

Given tree t:

```
   4 
  / \
 1   2

```

Return true, because t has the same structure and node values with a subtree of s.

**Example 2:**
Given tree s:

```
     3
    / \
   4   5
  / \
 1   2
    /
   0

```

Given tree t:

```
   4
  / \
 1   2

```

Return false

#### EXPLANATION:

我的这个解法比较笨拙

1.判断是否有相等的根节点，如果根节点相等，那么久继续判断是否是相等的树。



这样难免会有很多重复遍历的地方。

看到一个比较讨巧的就是把所有的节点的值平铺开来，然后再判断s节点的集合中是否包含t节点的平铺。这确实是一个很讨巧的方式，将树形结构的问题替换成了平铺结构，很有想象力。

#### SOLUTION:

```java
public class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.push(s);
        while (!queue.isEmpty()){
            TreeNode tmp = queue.poll();
            if(tmp.val == t.val){
                if(isSubtreeHelper(tmp,t))
                    return true;
            }
            if(tmp.left!=null)
                queue.push(tmp.left);
            if(tmp.right!=null)
                queue.push(tmp.right);
        }
        return false;
    }
    
    public static boolean isSubtreeHelper(TreeNode s,TreeNode t){
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.push(s);
        LinkedList<TreeNode> otherQueue = new LinkedList<>();
        otherQueue.add(t);
        while (!queue.isEmpty()|| !otherQueue.isEmpty()){
            TreeNode tmp = queue.poll();
            TreeNode oTmp = otherQueue.poll();
            if ((tmp != null && oTmp == null) || (tmp == null && oTmp != null) || tmp.val != oTmp.val)
                return false;
            if(tmp.left!=null)
                queue.push(tmp.left);
            if(tmp.right!=null)
                queue.push(tmp.right);

            if(oTmp.left!=null)
                otherQueue.push(oTmp.left);
            if(oTmp.right!=null)
                otherQueue.push(oTmp.right);

        }
        return true;
    }
}
```

