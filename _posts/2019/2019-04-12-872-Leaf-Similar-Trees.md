---
layout: post
title: 872.Leaf-Similar Trees
---

#### QUESTION:

Consider all the leaves of a binary tree.  From left to right order, the values of those leaves form a *leaf value sequence.*

![img](https://s3-lc-upload.s3.amazonaws.com/uploads/2018/07/16/tree.png)

For example, in the given tree above, the leaf value sequence is `(6, 7, 4, 9, 8)`.

Two binary trees are considered *leaf-similar* if their leaf value sequence is the same.

Return `true` if and only if the two given trees with head nodes `root1` and `root2` are leaf-similar.

**Note:**

- Both of the given trees will have between `1` and `100`nodes.

#### EXPLANATION:

看到题目就知道其实是一个中序遍历，但是要剔除不是leaf的节点。那么怎么才能算不是leaf的节点呢，就是node.left和node.right都不为null。

这样其实就可以了。

#### SOLUTION:

```java
class Solution {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        ArrayList<Integer> values1 = new ArrayList<>();
        ArrayList<Integer> values2 = new ArrayList<>();
        leafSimilarHelper(root1,values1);
        leafSimilarHelper(root2,values2);
        for(int i = 0;i<values1.size();i++){
            if(i<values2.size()){
                if(values1.get(i)!=values2.get(i)) return false;
            }else return false;
        }
        return true;
    }
    
    
    public static void leafSimilarHelper(TreeNode root,ArrayList list){
        if(root == null) return;
        leafSimilarHelper(root.left,list);
        if(root.left==null && root.right==null) list.add(root.val);
        leafSimilarHelper(root.right,list);
    }
}
```

