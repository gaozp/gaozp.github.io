---
layout: post
title: 1110. Delete Nodes And Return Forest
categories: [leetcode]
---
#### QUESTION:
Given the root of a binary tree, each node in the tree has a distinct value.

After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).

Return the roots of the trees in the remaining forest.  You may return the result in any order.

 

Example 1:

![](https://assets.leetcode.com/uploads/2019/07/01/screen-shot-2019-07-01-at-53836-pm.png)

Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
Output: [[1,2,null,4],[6],[7]]
 

Constraints:

The number of nodes in the given tree is at most 1000.
Each node has a distinct value between 1 and 1000.
to_delete.length <= 1000
to_delete contains distinct values between 1 and 1000.
#### EXPLANATION:
题意很容易理解。删除二叉树中的对应节点，然后将剩余的树添加到集合中。  
思路：首先考虑删除一个数的情况，那么就是需要遍历这个树，找到对应的数，然后将父节点的这个子节点设置成null，同时将该节点的左右子树添加到集合中。再进行拓展，第二个数，第三个数，那么都是从这个结果的结合中进行遍历。所以这个题目就是一个循环遍历结果集，递归找到val值删除的过程。  
#### SOLUTION:
```java
class Solution {
    public List<TreeNode> delNodesResult = new ArrayList<>();
    public List<TreeNode> delNodesResultcopy = new ArrayList<>();
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        delNodesResult.add(root);
        for(int del:to_delete){
            delNodesResultcopy.clear();// 将子树遍历集合情况
            delNodesHelper(del);
            delNodesResult.addAll(delNodesResultcopy); // 添加遍历后的结果
        }
        return delNodesResult;
    }
    
    public void delNodesHelper(int del){
        for(TreeNode node : delNodesResult){
            if(delNodesInTree(null,node,del)) return; // 因为节点的所有值都是不重复的，那么就可以判断需要del的值只可能在一个子树中，找到了，后续的子树就可以不用遍历了。
        }
    }

    public boolean delNodesInTree(TreeNode parent,TreeNode node,int del){ // 因为需要将父节点置为null，所以将父节点也一并传过来
        if(node == null) return false;
        if(node.val == del) {
            if(parent!=null){ // 如果父节点为null，就说明父节点是需要删除的，那么就需要将集合中的之前存的树进行删除，再添加父节点的两个子树
                if(parent.left!= null && parent.left.val == node.val) parent.left=null;
                if(parent.right!=null && parent.right.val == node.val) parent.right = null;
            }else{
                Iterator<TreeNode> iterator = delNodesResult.iterator();
                while (iterator.hasNext()){
                    TreeNode root = iterator.next();
                    if(root.val == node.val) iterator.remove();
                }
            }
            if(node.left!=null) delNodesResultcopy.add(node.left);
            if(node.right!=null) delNodesResultcopy.add(node.right);
            return true; // 说明本次已经找到节点，无需再继续寻找
        }
        boolean left=false,right = false;
        if(node.left!=null) left = delNodesInTree(node,node.left,del);
        if(node.right!=null) right = delNodesInTree(node,node.right,del);
        return left||right;
    }
}
```
