---
layout: post
title: 558. Quad Tree Intersection
categories: [leetcode]
---

#### QUESTION:
A quadtree is a tree data in which each internal node has exactly four children: topLeft, topRight, bottomLeft and bottomRight. Quad trees are often used to partition a two-dimensional space by recursively subdividing it into four quadrants or regions.

We want to store True/False information in our quad tree. The quad tree is used to represent a N * N boolean grid. For each node, it will be subdivided into four children nodes until the values in the region it represents are all the same. Each node has another two boolean attributes : isLeaf and val. isLeaf is true if and only if the node is a leaf node. The val attribute for a leaf node contains the value of the region it represents.

For example, below are two quad trees A and B:

A:
+-------+-------+   T: true
|       |       |   F: false
|   T   |   T   |
|       |       |
+-------+-------+
|       |       |
|   F   |   F   |
|       |       |
+-------+-------+
topLeft: T
topRight: T
bottomLeft: F
bottomRight: F

B:               
+-------+---+---+
|       | F | F |
|   T   +---+---+
|       | T | T |
+-------+---+---+
|       |       |
|   T   |   F   |
|       |       |
+-------+-------+
topLeft: T
topRight:
     topLeft: F
     topRight: F
     bottomLeft: T
     bottomRight: T
bottomLeft: T
bottomRight: F
 

Your task is to implement a function that will take two quadtrees and return a quadtree that represents the logical OR (or union) of the two trees.

A:                 B:                 C (A or B):
+-------+-------+  +-------+---+---+  +-------+-------+
|       |       |  |       | F | F |  |       |       |
|   T   |   T   |  |   T   +---+---+  |   T   |   T   |
|       |       |  |       | T | T |  |       |       |
+-------+-------+  +-------+---+---+  +-------+-------+
|       |       |  |       |       |  |       |       |
|   F   |   F   |  |   T   |   F   |  |   T   |   F   |
|       |       |  |       |       |  |       |       |
+-------+-------+  +-------+-------+  +-------+-------+
Note:

Both A and B represent grids of size N * N.
N is guaranteed to be a power of 2.
If you want to know more about the quad tree, you can refer to its wiki.
The logic OR operation is defined as this: "A or B" is true if A is true, or if B is true, or if both A and B are true.

#### EXPLANATION:

首先这是一个四叉树的问题，逻辑就是
1.如果q1，q2中有一个是leaf，那么如果是t，那么逻辑或下来，肯定还是t，如果是f，那么就肯定是另外的。
2.都不是leaf，那么就继续分成四叉树继续往下。
3.最后，如果四个子节点都为同样的，那么就可以将其认为是一个leaf，判断值就可以。

#### SOLUTION:
```java
class Solution {
    public Node intersect(Node quadTree1, Node quadTree2) {
        return intersectHelper(quadTree1,quadTree2);
    }
    
    public Node intersectHelper(Node q1, Node q2) {
        if (q1.isLeaf) {
            return q1.val ? q1 : q2;
        }
        if (q2.isLeaf) {
            return q2.val ? q2 : q1;
        }

        q1.topLeft = intersect(q1.topLeft, q2.topLeft);
        q1.topRight = intersect(q1.topRight, q2.topRight);
        q1.bottomLeft = intersect(q1.bottomLeft, q2.bottomLeft);
        q1.bottomRight = intersect(q1.bottomRight, q2.bottomRight);

        if (q1.topLeft.isLeaf && q1.topRight.isLeaf
                && q1.bottomLeft.isLeaf && q1.bottomRight.isLeaf
                && q1.topLeft.val == q1.topRight.val
                && q1.topRight.val == q1.bottomLeft.val
                && q1.bottomLeft.val == q1.bottomRight.val) {
            q1.isLeaf = true;
            q1.val = q1.topLeft.val;
        }
        return q1;
    }
}
```
