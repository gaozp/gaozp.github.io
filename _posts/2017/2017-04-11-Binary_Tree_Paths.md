---
layout: post
title: 257. Binary Tree Paths
---

#### QUESTION:

Given a binary tree, return all root-to-leaf paths.

For example, given the following binary tree:

```
   1
 /   \
2     3
 \
  5

```

All root-to-leaf paths are:

```
["1->2->5", "1->3"]
```

#### EXPLANATION:

这其实是一个深度优先的二叉树搜索算法。

1.首先按照深度优先DFS的方法遍历二叉树，同时完成string的构成

2.再判断该节点是否是最后的叶节点，如果是，就添加到最后的结果中



有的说是string的组合会过多的消耗内存和性能，是不是使用stringbuilder或者stringbuffer好一点，其实如果使用stringbuild的话，在每次递归的时候就不会复制出多个结果了，其实最后是不会获取到完整结果的。

#### SOLUTION:

```java
public static List<String> result = new ArrayList<>();
    public static List<String> binaryTreePaths(TreeNode root) {
        if(root == null) return result;
        binaryTreePathsHelper(root,"");
        return result;
    }

    public static void binaryTreePathsHelper(TreeNode root,String path){
        if (root.left != null)
            binaryTreePathsHelper(root.left, path+root.val + "->");
        if (root.right != null)
            binaryTreePathsHelper(root.right, path+root.val + "->");
        if(root.left==null&& root.right==null){
            path = path+root.val;
            result.add(path);
        }
    }

```

