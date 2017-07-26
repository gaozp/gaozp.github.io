---
layout: post
title: 637. Average of Levels in Binary Tree
---

#### QUESTION:

Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.

**Example 1:**

```
Input:
    3
   / \
  9  20
    /  \
   15   7
Output: [3, 14.5, 11]
Explanation:
The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].

```

**Note:**

1. The range of node's value is in the range of 32-bit signed integer.

#### EXPLANATION:

很容易想到的就是层序遍历。数的层序遍历也就不多说了，就是用一个queue来保存每层的节点。然后结束的时候就可以的queue.size相当于这层的层数。

但是我这个地方用的是另外一个方法，在遍历数的时候就把个数和和都一起求出来了，然后再计算得到结果就可以。但是发现还没有层序遍历来的快。诶。

#### SOLUTION:

```JAVA
public class Solution {
    public double[][] averageOfLevelsCount = new double[1000][2];
    public int averageOfLevelsLevel = 0;
    public List<Double> averageOfLevels(TreeNode root) {
        averageofLevelsHelper(root,0);
        System.out.println(averageOfLevelsLevel);
        ArrayList<Double> result = new ArrayList<>();
        for(int i = 0;i<=averageOfLevelsLevel;i++){
            result.add(averageOfLevelsCount[i][1]/averageOfLevelsCount[i][0]);
        }
        return result;
    }
    
    public void averageofLevelsHelper(TreeNode root,int level){
        if(root==null) return;
        averageOfLevelsLevel = Math.max(level,averageOfLevelsLevel);
        averageOfLevelsCount[level][0]++;
        averageOfLevelsCount[level][1]+=root.val;
        averageofLevelsHelper(root.left,level+1);
        averageofLevelsHelper(root.right,level+1);
    }
}
```

