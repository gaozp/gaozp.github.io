---
layout: post
title: 429. N-ary Tree Level Order Traversal
---

#### QUESTION:

Given an n-ary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example, given a `3-ary` tree: 

![img](https://assets.leetcode.com/uploads/2018/10/12/narytreeexample.png)

We should return its level order traversal:

```
[
     [1],
     [3,2,4],
     [5,6]
]
```

**Note:**

1. The depth of the tree is at most `1000`.
2. The total number of nodes is at most `5000`.

#### EXPLANATION:

其实一开始看到题目的时候，很容易就会想到层序遍历。但是你会发现：层序遍历只能保证顺序是对的，但是却无法保证每一层在同一个list里。后来就需要想到。那就通过每一层创建一个list，然后在单独填充。并不是说一层一填充。有了这个想法。那么问题就很容易了。

1.将树进行遍历，同时对每一层进行标记。

2.如果这一层没有list，那么就新建list，并将该值传入。

3.将子节点进行遍历

#### SOLUTION:

```java
class Solution {
    List<List<Integer>> levelOrderResult = new ArrayList<>();
    public List<List<Integer>> levelOrder(Node root) {
        levelOrderHelper(root,0);
        return levelOrderResult;
    }
    
    public  void levelOrderHelper(Node root,int level){
        if(root == null) return;
        List<Integer> integers;
        try{
            integers = levelOrderResult.get(level);
        }catch (Exception e){
            integers = new ArrayList<>();
            levelOrderResult.add(level,integers);
        }
        integers.add(root.val);
        if(root.children!=null){
            for(Node child: root.children)
                levelOrderHelper(child,level+1);
        }
    }
}
```

