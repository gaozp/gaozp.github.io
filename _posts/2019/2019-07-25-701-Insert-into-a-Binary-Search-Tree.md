---
layout: post
title: 701. Insert into a Binary Search Tree
---
#### QUESTION:

Given the root node of a binary search tree (BST) and a value to be inserted into the tree, insert the value into the BST. Return the root node of the BST after the insertion. It is guaranteed that the new value does not exist in the original BST.

Note that there may exist multiple valid ways for the insertion, as long as the tree remains a BST after insertion. You can return any of them.

For example, 

Given the tree:
        4
       / \
      2   7
     / \
    1   3
And the value to insert: 5
You can return this binary search tree:

         4
       /   \
      2     7
     / \   /
    1   3 5
This tree is also valid:

         5
       /   \
      2     7
     / \   
    1   3
         \
          4
Accepted
51,287
Submissions
67,143

#### EXPLANATION:

BST的插入，其实也是基础了。同时可以看到有一个点，就是可以保证插入的值在bst中是不存在的，那么，必然可以找到一个位置。  
可以采用递归的方式来进行：  
1.首先对节点进行判断，如果是null，那么就可以添加该节点  
2.进行判断，如果比当前节点小，那么就往左边查找  
3.如果比当前节点大，那么就往右边查找  
4.再其他的话，那么就只能是相等了，但是在该题中并不会出现

#### SOLUTION:
```java
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root==null){
            root = new TreeNode(val);
        }else if(val<root.val){
            root.left = insertIntoBST(root.left,val);
        }else{
            root.right = insertIntoBST(root.right,val);
        }
        return root;
    }
}
```