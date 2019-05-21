---
layout: post
title: 783. Minimum Distance Between BST Nodes
---

#### QUESTION:

Given a Binary Search Tree (BST) with the root node `root`, return the minimum difference between the values of any two different nodes in the tree.

**Example :**

```
Input: root = [4,2,6,1,3,null,null]
Output: 1
Explanation:
Note that root is a TreeNode object, not an array.

The given tree [4,2,6,1,3,null,null] is represented by the following diagram:

          4
        /   \
      2      6
     / \    
    1   3  

while the minimum difference in this tree is 1, it occurs between node 1 and node 2, also between node 3 and node 2.
```

**Note:**

1. The size of the BST will be between 2 and `100`.
2. The BST is always valid, each node's value is an integer, and each node's value is different.

#### EXPLANATION:

很明显，这道题有两种解决方法：

1.因为求两个数字之间的最小距离，所以可以取出所有的值，然后再进行比较。

2.在遍历的过程中就进行比较。

比较两者后，我选择了第一种方式，因为我觉得第二种方式，比如root节点的时候，你需要比较左边节点的最大值，以及右边节点的最小值。这样其实会造成每个节点都进行了很多次遍历。

#### SOLUTION:

```java
class Solution {
    Stack<Integer> minDiffInBSTStack = new Stack<>();
    public int minDiffInBST(TreeNode root) {
        minDiffInBSTHelper(root);
        int[] tmp = new int[minDiffInBSTStack.size()];
        int index = 0;
        while (!minDiffInBSTStack.isEmpty()){
            tmp[index] = minDiffInBSTStack.pop();
            index++;
        }
        Arrays.sort(tmp);
        int result = Integer.MAX_VALUE;
        for(int i = 0;i<tmp.length-1;i++){
            result = Math.min(result,Math.abs(tmp[i+1]-tmp[i]));
        }
        return result;
    }
    public void minDiffInBSTHelper(TreeNode root){
        if(root==null)return;
        minDiffInBSTStack.push(root.val);
        if(root.left!=null) minDiffInBSTHelper(root.left);
        if(root.right!=null) minDiffInBSTHelper(root.right);
    }
}
```

